package com.example.myapplication.view.activity

import android.content.Intent
import android.util.Log
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R
import com.example.myapplication.baseClass.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<ActivityMainBinding>() {

    var TAG: String = "MainActivity"

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView(viewDataBinding: ViewDataBinding?) {

        disposable.add(
            Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { t ->
                    run {
                        Log.e(TAG, "onCreate: " + t.message)
                    }
                }
                .subscribe { aLong ->
                    startActivity(Intent(this, TaskEntryListActivity::class.java))
                })
    }
}