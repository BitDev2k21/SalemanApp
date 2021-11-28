package com.saleman.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import androidx.core.content.ContextCompat.startActivity
import com.saleman.R
import java.text.SimpleDateFormat
import java.util.*


object ShareUtils {


    fun shareORder(context: Context){
        try {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            context.startActivity(Intent.createChooser(shareIntent,context.getString(R.string.share_order)))
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}