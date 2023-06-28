package com.liuwanshun.viewattchscope

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import com.liuwanshun.view.attachWindowScope
import kotlinx.coroutines.launch

private const val TAG = "AAAFrameLayout"

class AAAFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val handler = Handler(Looper.getMainLooper())
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.e(TAG, "onAttachedToWindow: 1")
        attachWindowScope.launch {
            Log.e(TAG, "$attachWindowScope   onAttachedToWindow: ")
        }
        Log.e(TAG, "onAttachedToWindow: 2")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.e(TAG, "onDetachedFromWindow: 1")
        attachWindowScope.launch {
            Log.e(TAG, "$attachWindowScope  onDetachedFromWindow: ")
        }
        Log.e(TAG, "onDetachedFromWindow: 2")

        handler.post {
            attachWindowScope.launch {
                Log.e(TAG, "post $attachWindowScope post  onDetachedFromWindow: ")
            }
        }
    }
}