package com.sopt.now.util.extension

import android.content.Intent
import android.os.Build
import com.sopt.now.data.User

fun <T> Intent.getParcelable(name: String, clazz: Class<T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, clazz)
    } else {
        getParcelableExtra(name)
    }
