package com.example.myapplication.view.activity

import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.example.myapplication.view.adapter.AddedTaskListListAdapter
import com.example.myapplication.utility.interFace.CommonInterfaceEpoxy
import com.example.myapplication.R
import com.example.myapplication.viewModel.TaskEntryListViewModel
import com.example.myapplication.baseClass.BaseActivity
import com.example.myapplication.databinding.ActivityTaskEntryListBinding
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskEntryListActivity : BaseActivity<ActivityTaskEntryListBinding>(), View.OnClickListener {

    private lateinit var addedTaskListListAdapter: AddedTaskListListAdapter
    private var TAG: String = TaskEntryListActivity::class.java.simpleName
    private val taskEntryListViewModel by viewModel<TaskEntryListViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_task_entry_list

    override fun initView(viewDataBinding: ViewDataBinding?) {
        this.viewDataBinding = viewDataBinding as ActivityTaskEntryListBinding
        this.viewDataBinding.lifecycleOwner = this
        this.viewDataBinding.clickListener = this
        this.viewDataBinding.viewModel = taskEntryListViewModel

        taskEntryListViewModel.status
            .observe(this, Observer {
                if (it.isNotBlank()) {
                    showToast(it)
                }
            }
            )

        addedTaskListListAdapter =
            AddedTaskListListAdapter(
                taskEntryListViewModel.taskArrayListModel,
                object : CommonInterfaceEpoxy {
                    override fun commonCallback(any: Any, str: Any) {
                        if (any is Int) {
                            if (str is String && str == "delete") {
                                taskEntryListViewModel.taskArrayListModel.removeAt(any)
                                refreshAdapter()
                            }
                        }
                    }
                })
        refreshAdapter()
    }

    private fun refreshAdapter() {
        this.viewDataBinding.addedTaskListListAdapter = addedTaskListListAdapter
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btAddTask -> {
                Log.i(TAG, "onClick: ${Gson().toJson(taskEntryListViewModel.taskListModel)}")

                if (taskEntryListViewModel.onTextValidate(taskEntryListViewModel.taskListModel)) {
                    taskEntryListViewModel.taskListModel.taskTitle = ""
                    taskEntryListViewModel.taskListModel.taskDescription = ""
                    this.viewDataBinding.etTaskTitle.text.clear()
                    this.viewDataBinding.etTaskDescription.text.clear()

                    refreshAdapter()
                }
            }
        }
    }

}