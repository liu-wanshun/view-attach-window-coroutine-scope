package com.liuwanshun.view

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.doOnDetach
import com.liuwanshun.viewattachscope.R
import kotlinx.coroutines.*


/**
 * 与View attachToWindow绑定，
 *
 * 仅在`attachToWindow` ~ `detachedFromWindow`范围期间有效，
 *
 * 在onViewDetachedFromWindow后cancel。
 */
val View.attachWindowScope: CoroutineScope
    get() {
        synchronized(this) {
            val scope: CoroutineScope? = this.getTag(R.id.view_attach_window_coroutine_scope) as? CoroutineScope

            if (scope != null) {
                return scope
            }
            return CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).also { coroutineScope ->
                coroutineScope.launch(Dispatchers.Main.immediate) {
                    if (ViewCompat.isAttachedToWindow(this@attachWindowScope)) {
                        setTag(R.id.view_attach_window_coroutine_scope, coroutineScope)
                        doOnDetach {
                            coroutineScope.cancel("detached from window")
                            setTag(R.id.view_attach_window_coroutine_scope, null)
                        }
                    } else {
                        coroutineScope.cancel("already detached from window")
                    }
                }

            }
        }
    }

