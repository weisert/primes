package ru.weisert.primes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class InputInstrumentedTest {

    @Rule
    public ActivityTestRule<PrimesActivity> mActivityRule =
            new ActivityTestRule<>(PrimesActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("ru.weisert.primes", appContext.getPackageName());
    }

    @Test
    public void inputEmptyStringWithPressDown() {
        onView(withId(R.id.editText)).perform(clearText());
        onView(withId(R.id.imageButtonDown)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("2")));
    }

    @Test
    public void inputEmptyStringWithPressUp() {
        onView(withId(R.id.editText)).perform(clearText());
        onView(withId(R.id.imageButtonUp)).perform(click());
        onView(withId(R.id.editText)).check(matches(withText("3")));
    }
}
