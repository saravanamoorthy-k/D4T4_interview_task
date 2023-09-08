package com.example.myapplication.baseClass

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.util.regex.Matcher
import java.util.regex.Pattern

abstract class BaseViewModel : ViewModel() {

    protected var disable: CompositeDisposable = CompositeDisposable()
    protected var TAG: String = BaseViewModel::class.java.simpleName

    init {
        disable = CompositeDisposable()
    }

    protected fun isEmpty(value: String): Boolean = value.isNotBlank()

    protected fun isDigitsOnly(value: String): Boolean = TextUtils.isDigitsOnly(value)

    protected fun isValidEmailAddress(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    protected fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val pattern: Pattern
        val phoneNumberPatten = "^([6-9]{1})([0-9]{9})$"
        pattern = Pattern.compile(phoneNumberPatten)
        val matcher: Matcher = pattern.matcher(phoneNumber)
        return matcher.matches()
    }

    override fun onCleared() {
        super.onCleared()
        try {
            disable.clear()
        } catch (e: Exception) {
            Log.e(TAG, "onCleared: " + e.message)
        }
    }
}