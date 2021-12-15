package com.example.adn


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CloseKeyboardAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.adn.ui.MainActivity
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeoutException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @get:Rule
    var mainActivityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun saveMotorcycleAndPay_fillTextInputAndChooseRadioButtonMotorcycle_success() {
        onView(withId(R.id.rbtnMotorcycle)).perform(click())

        onView(withId(R.id.etPlaca)).perform(scrollTo(), typeText("BBC817"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.etCilindraje)).perform(typeText("400"))
            .perform(closeSoftKeyboard())
        onView(withId(R.id.rbtnMotorcycle)).perform(click())

        //Then
        onView(withId(R.id.addVehicle)).perform(click())

        onView(withId(R.id.rbtnMotorcycle)).perform(click())

        onView(withId(R.id.rvVehicles))
            .perform(
                actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("BBC817")),
                    MyViewAction.clickChildViewWithId(R.id.btnExit)
                )
            )

        onView(withText("PAGAR")).inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())
    }

    @Test
    fun saveCarAndPayment_fillTextInputAndChooseRadioButtonCar_success() {
        onView(withId(R.id.rbtnCar)).perform(click())
        // When
        onView(withId(R.id.etPlaca)).perform(typeText("PHT768"))
            .perform(CloseKeyboardAction())

        //Then
        onView(withId(R.id.addVehicle)).perform(click())

        onView(withId(R.id.rbtnCar)).perform(click())


        onView(withId(R.id.rvVehicles))
            .perform(
                actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("PHT768")),
                    MyViewAction.clickChildViewWithId(R.id.btnExit)
                )
            )

        onView(withText("PAGAR")).inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())
    }

    private fun waitForView(viewId: Int, timeout: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for a specific view with id $viewId; during $timeout millis."
            }

            override fun perform(uiController: UiController, rootView: View) {
                uiController.loopMainThreadUntilIdle()
                val startTime = System.currentTimeMillis()
                val endTime = startTime + timeout
                val viewMatcher = withId(viewId)

                do {

                    for (child in TreeIterables.breadthFirstViewTraversal(rootView)) {

                        if (viewMatcher.matches(child)) {
                            return
                        }
                    }

                    uiController.loopMainThreadForAtLeast(100)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                    .withCause(TimeoutException())
                    .withActionDescription(this.description)
                    .withViewDescription(HumanReadables.describe(rootView))
                    .build()
            }
        }
    }


}