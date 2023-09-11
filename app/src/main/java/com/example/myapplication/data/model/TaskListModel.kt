package com.example.myapplication.data.model

data class TaskListModel(
    var taskDescription: String = "",
    var taskTitle: String = ""
){

    private fun isEmpty(value: String): Boolean = value.isNotBlank()

    fun isTaskDescriptionValid() = isEmpty(taskDescription)
    fun isTaskTitleValid() = isEmpty(taskTitle)
}
