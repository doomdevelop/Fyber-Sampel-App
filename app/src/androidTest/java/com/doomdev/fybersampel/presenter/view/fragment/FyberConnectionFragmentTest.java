package com.doomdev.fybersampel.presenter.view.fragment;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.doomdev.fybersampel.R;
import com.doomdev.fybersampel.presenter.util.FyberParameterHelper;
import com.doomdev.fybersampel.presenter.view.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.fail;

/**
 * Created by and on 23.01.16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class FyberConnectionFragmentTest {
    private FyberParameterHelper fyberParameterHelper;
    private Map<String, String> params;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        fyberParameterHelper = new FyberParameterHelper();
        params = fyberParameterHelper.prepareAndGetParams();
        Espresso.registerIdlingResources(
                mMainActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                mMainActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void testCorrectCompoennetsStateByEmptyFields() throws Exception {
        onView(withId(R.id.btn_connect)).check(matches(not(isEnabled())));
        onView(withId(R.id.progres_bar_api_call)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testCorrectCompoennetsStateByFieldsNotEmpty() throws Exception {
        onView(withId(R.id.edit_text_api_key)).perform(typeText(FyberParameterHelper.API_KEY));
        onView(withId(R.id.edit_text_uid)).perform(typeText(TestParams.UID.getValue()));
        onView(withId(R.id.edit_text_pub0)).perform(typeText(TestParams.PUB0.getValue()));
        onView(withId(R.id.edit_text_appid)).perform(typeText(TestParams.APPID.getValue()));
        onView(withId(R.id.btn_connect)).check(matches(isEnabled()));
        onView(withId(R.id.progres_bar_api_call)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testCorrectCompoennetsStateByButtonPress() throws Exception {
        onView(withId(R.id.edit_text_api_key)).perform(typeText(FyberParameterHelper.API_KEY));
        onView(withId(R.id.edit_text_uid)).perform(typeText(TestParams.UID.getValue()));
        onView(withId(R.id.edit_text_pub0)).perform(typeText(TestParams.PUB0.getValue()));
        onView(withId(R.id.edit_text_appid)).perform(typeText(TestParams.APPID.getValue()));
        onView(withId(R.id.btn_connect)).check(matches(isEnabled()));
        onView(withId(R.id.progres_bar_api_call)).check(matches(not(isDisplayed())));
        Espresso.pressBack();//remove keyboard
        onView(withId(R.id.btn_connect)).perform(click());
        onView(withId(R.id.list)).check(matches(isDisplayed()));
    }

    @Test
    public void testCallWithIncorectApiKeyGenerateError() throws Exception {
        //incorrect
        onView(withId(R.id.edit_text_api_key)).perform(typeText(FyberParameterHelper.API_KEY_FALSE));
        onView(withId(R.id.edit_text_uid)).perform(typeText(TestParams.UID.getValue()));
        onView(withId(R.id.edit_text_pub0)).perform(typeText(TestParams.PUB0.getValue()));
        onView(withId(R.id.edit_text_appid)).perform(typeText(TestParams.APPID.getValue()));
        onView(withId(R.id.btn_connect)).check(matches(isEnabled()));
        onView(withId(R.id.progres_bar_api_call)).check(matches(not(isDisplayed())));
        Espresso.pressBack();//remove keyboard
        onView(withId(R.id.btn_connect)).perform(click());
        onView(withId(R.id.text_view_error_msg)).check(matches(isDisplayed()));

    }
    public enum TestParams {

        FORMAT("format", "json"),
        APPID("appid", "2070"),
        UID("uid", "spiderman"),
        LOCALE("locale", "de"),
        IP("ip", "109.235.143.113"),
        OFFER_TYPES("offer_types", "112"),
        TIMESTAMP("timestamp", ""),
        PUB0("pub0", "campaign2"),
        DEVICE_ID("device_id", "");

        private final String key;
        private final String value;

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        TestParams(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
