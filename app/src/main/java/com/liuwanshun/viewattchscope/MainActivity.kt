package com.liuwanshun.viewattchscope

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.liuwanshun.view.attachWindowScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.test).setOnClickListener {
            val p = it.parent as ViewGroup
            p.removeView(it)
            lifecycleScope.launch {
                delay(200)
                p.addView(it)
            }
        }

    }
}