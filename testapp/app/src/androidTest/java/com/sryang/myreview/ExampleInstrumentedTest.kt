package com.sryang.myreview

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sryang.torang_core.data.model.LoggedInUserData
import com.example.torangrepository.FeedRepositoryImpl
import com.example.torangrepository.LoginRepositoryImpl
import kotlinx.coroutines.runBlocking

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
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.sryang.myreviewtestapp", appContext.packageName)
    }

    @Test
    fun test() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val feedRepository = FeedRepositoryImpl(appContext)

        runBlocking {
            feedRepository.loadFeed()
        }
    }

    @Test
    fun login() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val loginrepository = LoginRepositoryImpl(appContext)

        runBlocking {
            val loggedInUserData = LoggedInUserData(userId = 4)
            loginrepository.setLoggedInUser(loggedInUserData)
        }
    }
}