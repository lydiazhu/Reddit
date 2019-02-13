package com.example.reddit

import android.content.Context
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailedViewTest {


    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun firstTimeLaunchingAppShouldShowLoginPage_takeUserToDetail() {
        onView(withId(R.id.activity_login_user_name)).check(matches(withHint("Enter user name")))
        // enter name
        onView(withId(R.id.activity_login_user_name)).perform(typeText("Lydia"))
        onView(withId(R.id.activity_login_login_button)).check(matches(withText("LOGIN")))
        closeSoftKeyboard()
        onView(withId(R.id.activity_login_login_button)).perform(click())
        onView(withId(R.id.grid_view_toolbar)).check(matches(isDisplayed()))
        val sharedPreferences =
                getInstrumentation().getTargetContext().getSharedPreferences(LoginActivity::class.java.name, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }
}