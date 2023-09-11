package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.TaskListModel
import com.google.gson.Gson

class TaskEntryListViewModel : ViewModel() {

    var TAG: String = TaskEntryListViewModel::class.java.simpleName
    var taskListModel: TaskListModel = TaskListModel()
    var status = MutableLiveData<String>()
    var taskArrayListModel: ArrayList<TaskListModel> = ArrayList()

    init {
        status.value = ""
    }

    fun onTextValidate(paramModel: TaskListModel): Boolean {

        return if (paramModel.isTaskTitleValid()) {
            status.value = ""
            if (paramModel.isTaskDescriptionValid()) {
                status.value = ""
                taskArrayListModel.add(
                    TaskListModel(
                        paramModel.taskDescription,
                        paramModel.taskTitle
                    )
                )
                Log.i(
                    TAG, "onTextValidate: ${
                        Gson().toJson(
                            taskArrayListModel
                        )
                    }"
                )
                true
            } else {
                status.value = "Enter Task Description*"
                false
            }
        } else {
            status.value = "Enter Task Title*"
            false
        }
    }

}