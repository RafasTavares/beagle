package br.com.zup.beagle.automatedTests.cucumber.steps

import androidx.test.rule.ActivityTestRule
import br.com.zup.beagle.automatedTests.activity.MainActivity
import br.com.zup.beagle.automatedTests.cucumber.elements.*
import br.com.zup.beagle.automatedTests.cucumber.robots.ScreenRobot
import br.com.zup.beagle.automatedTests.utils.ActivityFinisher
import br.com.zup.beagle.automatedTests.utils.TestUtils
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Rule

const val TEXT_INPUT_SCREEN_BFF_URL = "http://10.0.2.2:8080/textinput"

class TextInputScreen {
    @Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before("@textInput")
    fun setup() {
        TestUtils.startActivity(activityTestRule, TEXT_INPUT_SCREEN_BFF_URL)
    }

    @Given("^the Beagle application did launch with the textInput on screen$")
    fun checkBaseScreen() {
        ScreenRobot()
            .checkViewContainsText("Beagle Text Input", true)
    }

    @Then("^I must check if the textInput value (.*) appears on the screen$")
    fun checkTextInput(string:String) {
        ScreenRobot()
            .checkViewContainsText(string, true)
    }

    @Then("^I must check if the textInput placeholder (.*) appears on the screen$")
    fun checkTextInputPlaceholder(string:String) {
        ScreenRobot()
            .checkViewContainsHint(string, true)
    }

    @After("@textInput")
    fun tearDown() {
        ActivityFinisher.finishOpenActivities()
    }
}
