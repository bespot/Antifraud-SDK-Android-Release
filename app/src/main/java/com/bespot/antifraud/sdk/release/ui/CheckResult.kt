package com.bespot.antifraud.sdk.release.ui

import com.bespot.antifraud.sdk.Action
import com.bespot.antifraud.sdk.common.empty

data class CheckResult(
    val action: Action,
    val ticket: String
) {
    companion object {
        fun empty() = CheckResult(
            action = Action.SAFE,
            ticket = String.empty()
        )
    }
}
