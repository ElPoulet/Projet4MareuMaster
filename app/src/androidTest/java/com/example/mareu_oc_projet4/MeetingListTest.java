package com.example.mareu_oc_projet4;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu_oc_projet4.utils.DeleteViewAction;
import com.example.mareu_oc_projet4.view.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static com.example.mareu_oc_projet4.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(JUnit4.class)
public class MeetingListTest {

    private static int ITEMS_COUNT = 2;
    
    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    
    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity,notNullValue());
    }

    public static void setDate(int datePickerLaunchViewId, int year, int monthOfYear, int dayOfMonth) {
        onView(withId(datePickerLaunchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(dayOfMonth,monthOfYear,year));
        onView(withId(android.R.id.button1)).perform(click());
    }
    
    @Test
    public void myReunionList_deleteAction_shouldRemoveItem() {
        
        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT));

        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).perform(actionOnItemAtPosition(1, new DeleteViewAction()));

        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void myReunionList_addAction_shouldAddItem() {

        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT));

        onView(withId(R.id.btnReunion)).perform(click());

        onView(withId(R.id.button_validate)).perform(click());

        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT+1));
    }

    @Test
    public void myReunionListSalleFilter_shouldRemoveItem() {

        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText("Tri par Salle")).perform(click());
        onView(ViewMatchers.withText("Salle 1")).perform(click());
        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT -1));
        
    }

    @Test
    public void myDateReunionFilter_shouldRemoveItem() {
        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText("Tri par Date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 10, 18));
        onView(ViewMatchers.withText("OK")).perform(click());
        onView(allOf(withId(R.id.mRecyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));

    }

}
