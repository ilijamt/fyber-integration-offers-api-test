package matoski.com.fyberdemooffers;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> ActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void defaultValuesTest() {
        onView(withId(R.id.app_id)).check(matches(notNullValue()));
        onView(withId(R.id.app_uid)).check(matches(notNullValue()));
        onView(withId(R.id.app_apiKey)).check(matches(notNullValue()));
        onView(withId(R.id.app_pub0)).check(matches(notNullValue()));
        onView(withId(R.id.app_id)).check(matches(withText(Constants.DEFAULT_APP_ID.toString())));
        onView(withId(R.id.app_uid)).check(matches(withText(Constants.DEFAULT_UID)));
        onView(withId(R.id.app_apiKey)).check(matches(withText(Constants.DEFAULT_API_KEY)));
    }

    @Test
    public void validateStartOfOffersActivity() {
        onView(withId(R.id.button_load)).check(matches(notNullValue()));
        onView(withId(R.id.button_load)).perform(click());
        onView(withId(R.id.recycler_view)).check(matches(notNullValue()));
        pressBack();
        onView(withId(R.id.button_load)).check(matches(notNullValue()));
    }

}