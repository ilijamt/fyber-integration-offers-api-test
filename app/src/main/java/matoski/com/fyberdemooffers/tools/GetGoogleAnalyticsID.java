package matoski.com.fyberdemooffers.tools;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

import matoski.com.fyberdemooffers.handlers.AsyncTaskResponseHandler;

public class GetGoogleAnalyticsID extends AsyncTask<String, Void, String> {

    public static String LOG_TAG = "GetGoogleAnalyticsID";

    private Context mContext;
    private AsyncTaskResponseHandler mHandler;

    public GetGoogleAnalyticsID(Context context, AsyncTaskResponseHandler<String> handler) {
        mContext = context;
        mHandler = handler;
    }

    @Override
    protected String doInBackground(String... strings) {
        AdvertisingIdClient.Info adInfo = null;
        try {
            adInfo = AdvertisingIdClient.getAdvertisingIdInfo(mContext.getApplicationContext());
            if (adInfo.isLimitAdTrackingEnabled()) // check if user has opted out of tracking
                return null;
        } catch (IOException e) {
            Log.e(LOG_TAG, e.toString());
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.e(LOG_TAG, e.toString());
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            Log.e(LOG_TAG, e.toString());
            e.printStackTrace();
        }
        return adInfo.getId();
    }

    @Override
    protected void onPostExecute(String s) {
        mHandler.onPostExecute(s);
    }
}
