package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.utility.interFace.CommonInterfaceEpoxy
import com.example.myapplication.R
import com.example.myapplication.model.TaskListModel
import com.example.myapplication.baseClass.BaseAdapter2
import com.example.myapplication.databinding.AdapterAddedTaskListBinding

class AddedTaskListListAdapter constructor(
    val arrayData: ArrayList<TaskListModel>,
    val commonInterface: CommonInterfaceEpoxy
) : BaseAdapter2<String>(arrayData) {

    override fun onBindViewHolderBase(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as AdapterViewHolder).getBinding()
        binding.strTaskTitle = arrayData[position].taskTitle
        binding.strTaskDescription = arrayData[position].taskDescription

        binding.ivDelete.setOnClickListener {
            commonInterface.commonCallback(position, "delete")
        }
    }

    override fun onCreateViewHolderBase(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_added_task_list, parent, false)
        )
    }

    class AdapterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var dataBinding: AdapterAddedTaskListBinding? = null

        init {
            dataBinding =
                DataBindingUtil.bind<ViewDataBinding>(itemView) as AdapterAddedTaskListBinding
        }

        fun getBinding(): AdapterAddedTaskListBinding {
            return dataBinding!!
        }
    }
}