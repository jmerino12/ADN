package com.example.adn

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.adn.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @get:Rule
    var mainActivityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun saveMotorcycleAndPay_fillTextInputAndChooseRadioButtonMotorcycle_success() {
        clickButton(R.id.rbtnMotorcycle)

        writeText("BBC817", R.id.etPlaca)
        writeText("400", R.id.etCilindraje)

        clickButton(R.id.addVehicle)

        clickItemInViewHolder(R.id.rvVehicles, "BBC817", R.id.btnExit)

        clickButtonDialog("PAGAR")
    }


    @Test
    fun saveCarAndPayment_fillTextInputAndChooseRadioButtonCar_success() {
        clickButton(R.id.rbtnCar)
        writeText("PHT768", R.id.etPlaca)
        clickButton(R.id.addVehicle)
        clickItemInViewHolder(R.id.rvVehicles, "PHT768", R.id.btnExit)
        clickButtonDialog("PAGAR")
    }


    private fun clickButton(id: Int) {
        onView(withId(id)).perform(click())
    }

    private fun writeText(text: String, id: Int) {
        onView(withId(id)).perform(scrollTo(), typeText(text))
            .perform(closeSoftKeyboard())
    }

    private fun clickItemInViewHolder(recyclerView: Int, viewHolderContent: String, id: Int) {
        onView(withId(recyclerView))
            .perform(
                actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText(viewHolderContent)),
                    MyViewAction.clickChildViewWithId(id)
                )
            )
    }

    private fun clickButtonDialog(text: String) {
        onView(withText(text)).inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())
    }

}