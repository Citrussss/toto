package com.example.anko

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

/**
 * Rabies
 * @author USER
 * Date:   2019-01-18
 * Time:   14:06
 */
class AnkoActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyActivityUI().setContentView(this)
    }
}

class MyActivityUI : AnkoComponent<AnkoActivity> {
    override fun createView(ui: AnkoContext<AnkoActivity>) = with(ui) {
        horizontalLayout() {
            padding = dip(30)
            var editTextS = editText {
                hint = "Name"
                textSize = 24f
//                visibility
            }
            var editTextF = editText {
                hint = "Password"
                textSize = 24f
            }.lparams {
            }
            button("Login") {
                textSize = 26f
            }.lparams {
            }
        }
    }

    inline fun ViewManager.horizontalLayout(init: LinearLayout.() -> Unit): LinearLayout =
            ankoView({
                val linearLayout = LinearLayout(it)
                linearLayout.orientation = LinearLayout.VERTICAL
                linearLayout
            }, theme = 0, init = init)

    inline fun Activity.horizontalLayout(init: LinearLayout.() -> Unit): LinearLayout =
            ankoView({ LinearLayout(it) }, theme = 0, init = init)

    inline fun <T : View> T.lparams(
            width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            init: LinearLayout.LayoutParams.() -> Unit
    ): T {
        val layoutParams = LinearLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }
}
