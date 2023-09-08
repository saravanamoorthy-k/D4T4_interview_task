package com.example.myapplication.baseClass

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseAdapter2<T>(val dataList: ArrayList<*>, var isCheck: Boolean = false) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onCreateViewHolderBase(parent, viewType)
    }

    abstract fun onCreateViewHolderBase(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        this.onBindViewHolderBase(holder, position)
    }

    abstract fun onBindViewHolderBase(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemCount(): Int {
        var value = 0
        if (isCheck) {
            return 5
        }
        if (dataList.size > 0) {
            value = dataList.size
        } else {
            value = 0
        }
        return value
    }

    operator fun get(position: Int): Any {
        return dataList[position]
    }

    open fun parseDateToddMMyyyy(time: String?): String? {
        val inputPattern = "yyyy-MM-dd HH:mm:ss"
        val outputPattern = "dd-MMM-yyyy"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return str
    }

}