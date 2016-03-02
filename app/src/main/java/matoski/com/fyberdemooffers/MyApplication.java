package matoski.com.fyberdemooffers;

import android.app.Application;

import matoski.com.fyberdemooffers.handlers.AsyncTaskResponseHandler;
import matoski.com.fyberdemooffers.tools.GetGoogleAnalyticsID;

public class MyApplication extends Application {

    public Boolean googleAdIdLimitedTrackingEnabled = false;
    public String googleAdId = null;

    @Override
    public void onCreate() {
        super.onCreate();

        GetGoogleAnalyticsID gaid = new GetGoogleAnalyticsID(getApplicationContext(), new AsyncTaskResponseHandler<String>() {
            @Override
            public void onPostExecute(String response) {
                googleAdId = response;
                googleAdIdLimitedTrackingEnabled = response != null;
            }
        });

        gaid.execute();
    }
}
