package com.bespot.antifraud.sdk.release.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bespot.antifraud.sdk.Action
import com.bespot.antifraud.sdk.Failure
import com.bespot.antifraud.sdk.FraudulentCheckObserver
import com.bespot.antifraud.sdk.FraudulentSessionObserver
import com.bespot.antifraud.sdk.SafeSdk
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivityViewModel: ViewModel() {
    private val _checkResult: MutableState<CheckResult> = mutableStateOf(CheckResult.empty())
    val checkResult: State<CheckResult> = _checkResult

    private val _timestamp: MutableState<String> = mutableStateOf("")
    val timestamp: State<String> = _timestamp

    private val _isSubscribed: MutableState<Boolean> = mutableStateOf(false)
    val isSubsribed: State<Boolean> = _isSubscribed

    private val safeSdk: SafeSdk = SafeSdk

    init {
        safeSdk.logging(true)
    }

    fun subscribe() {
        _isSubscribed.value = true
        safeSdk.subscribe(object : FraudulentSessionObserver {
            override fun perform(action: Action, signature: String) {
                _checkResult.value = CheckResult(action = action, ticket = signature)
                _timestamp.value = getNow()
            }

            override fun onError(error: Failure) {
                _timestamp.value = getNow()
            }
        }
        )
    }

    fun unsubscribe() {
        _isSubscribed.value = false
        safeSdk.unsubscribe()
    }

    fun check() {
        safeSdk.check(object : FraudulentCheckObserver{
            override fun onSuccess(action: Action, signature: String) {
                _checkResult.value = CheckResult(action = action, ticket = signature)
                _timestamp.value = getNow()
            }

            override fun onError(error: Failure) {
                _timestamp.value = getNow()
            }
        }
        )
    }

    fun setUserId(id: String) {
        safeSdk.setUserID(id)
    }

    private fun getNow(): String {
        return SimpleDateFormat(
            "HH:mm:ss",
            Locale.getDefault()
        ).format(Calendar.getInstance().time)
    }

}