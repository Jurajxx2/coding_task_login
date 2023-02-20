package com.juraj.common.utils

fun String.isPasswordValid(): Boolean {
    var charFlag = false
    var numberFlag = false
    for (ch in this) {
        if (Character.isLetter(ch)) {
            charFlag = true
        } else if (Character.isDigit(ch)) {
            numberFlag = true
        }
        if (charFlag && numberFlag && this.length >= 8) return true
    }
    return false
}