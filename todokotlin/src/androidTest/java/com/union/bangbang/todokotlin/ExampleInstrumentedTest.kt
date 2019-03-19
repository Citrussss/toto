package com.union.bangbang.todokotlin

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.union.bangbang.todokotlin.base.utils.Configuration

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.union.bangbang.todokotlin", appContext.packageName)
    }
}
