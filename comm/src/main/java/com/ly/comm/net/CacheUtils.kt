package com.ly.comm.net

import android.content.Context
import android.os.Environment

object CacheUtils {

    fun getCacheDir(context: Context): String {
        return if (context.externalCacheDir != null && existSdCard()) {
            context.externalCacheDir!!.toString()
        } else {
            context.cacheDir.toString()
        }
    }

    private fun existSdCard(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }
}