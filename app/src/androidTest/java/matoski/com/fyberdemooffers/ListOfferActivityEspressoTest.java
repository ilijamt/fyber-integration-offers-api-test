package matoski.com.fyberdemooffers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ListOfferActivityEspressoTest {

    @Rule
    public ActivityTestRule<ListOffersActivity> mActivityRule =
            new ActivityTestRule<>(ListOffersActivity.class, true, false);

    public Intent getActivityIntent() {
        Context targetContext = InstrumentationRegistry.getInstrumentation()
                .getTargetContext();
        Intent intent = new Intent(targetContext, ListOffersActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("appId", String.valueOf(Constants.DEFAULT_APP_ID));
        bundle.putString("uid", Constants.DEFAULT_UID);
        bundle.putString("apiKey", Constants.DEFAULT_API_KEY);
        bundle.putString("pub0", "pub0");
        intent.putExtras(bundle);
        return intent;
    }

    @Test
    public void testStart() {
        ListOffersActivity activity = mActivityRule.launchActivity(getActivityIntent());
        onView(withId(R.id.recycler_view)).check(matches(notNullValue()));
    }
}