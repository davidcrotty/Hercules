package net.davidcrotty.hercules;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import net.davidcrotty.hercules.domain.DataSource;
import net.davidcrotty.hercules.model.Set;
import net.davidcrotty.progressview.SetState;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by David Crotty on 18/09/2017.
 * <p>
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
@RunWith(AndroidJUnit4.class)
public class ExerciseActivityTest {

    @Rule
    public ActivityTestRule<ExerciseActivity> activityRule =
            new ActivityTestRule<>(ExerciseActivity.class);

    @Test
    public void test_did_launch() {
        activityRule.launchActivity(new Intent());
    }

    @Test
    public void test_did_configure() {
        //Given a valid intent
        Intent intent = getValidIntent();

        //when
        activityRule.launchActivity(intent);

        //Should
        onView(withId(R.id.exercise_title))
                .check(matches(withText("Bicep curls")));
        onView(withId(R.id.remaining_reps))
                .check(matches(withText("5")));
        onView(withId(R.id.time_remaining_text))
                .check(matches(withText("00:30")));
        onView(withId(R.id.next_up_text))
                .check(matches(withText("Next: 5 Shoulder press")));
    }

    @Test
    public void test_did_configure_negative() {
        //Given invalid intent
        Intent intent = new Intent();

        //when
        activityRule.launchActivity(intent);

        //Should
        onView(withId(R.id.exercise_title))
                .check(matches(withText("")));
        onView(withId(R.id.remaining_reps))
                .check(matches(withText("")));
        onView(withId(R.id.time_remaining_text))
                .check(matches(withText("")));
        onView(withId(R.id.next_up_text))
                .check(matches(withText("")));
    }

    @Test
    public void test_when_skipping_set() {
        //Given valid intent
        Intent intent = getValidIntent();

        //when
        activityRule.launchActivity(intent);
        onView(withId(R.id.next_set)).perform(click());

        //Should
        onView(withId(R.id.exercise_title))
                .check(matches(withText("Shoulder press")));
        onView(withId(R.id.remaining_reps))
                .check(matches(withText("5")));
        onView(withId(R.id.time_remaining_text))
                .check(matches(withText("00:30")));
        onView(withId(R.id.next_up_text))
                .check(matches(withText("Next: Done!")));
    }

    private Intent getValidIntent() {
        Intent intent = new Intent();
        final Set bicepSet = new Set("Bicep curls", 5, 30, SetState.PENDING);
        final Set shoulderSet = new Set("Shoulder press", 5, 30, SetState.PENDING);
        ArrayList setList = new ArrayList() {{ add(bicepSet); add(shoulderSet); }};
        intent.putParcelableArrayListExtra(ExerciseActivity.Companion.getSET_KEY(), setList);
        return intent;
    }
}
