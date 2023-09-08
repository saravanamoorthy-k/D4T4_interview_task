package com.example.myapplication.baseClass

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    lateinit var disposable: CompositeDisposable
    lateinit var activity: Activity
    lateinit var viewDataBinding: T

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        disposable = CompositeDisposable()
        activity = this
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.lifecycleOwner = this
        initView(viewDataBinding)
    }

    protected abstract fun initView(viewDataBinding: ViewDataBinding?)

    override fun onDestroy() {
        super.onDestroy()
        if (disposable.isDisposed) {
            disposable.clear()
        }
    }

    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun setIntent(cObjection: Class<*>, isFrom: Int) {
        startActivity(Intent(this, cObjection))
        when (isFrom) {
            1 -> {
                //just no need to finish
            }

            2 -> {
                //just finishing the single activity
                finish()
            }

            3 -> {
                //finishing all previous activity
                finishAffinity()
            }
        }
    }

}