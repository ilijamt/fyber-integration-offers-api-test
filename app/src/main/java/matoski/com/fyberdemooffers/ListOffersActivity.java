package matoski.com.fyberdemooffers;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.common.base.MoreObjects;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import matoski.com.fyberdemooffers.adapters.OfferItemAdapter;
import matoski.com.fyberdemooffers.pojo.OfferItem;
import matoski.com.fyberdemooffers.pojo.OfferParameters;
import matoski.com.fyberdemooffers.pojo.offer.ApiResponse;
import matoski.com.fyberdemooffers.pojo.offer.SingleOffer;
import matoski.com.fyberdemooffers.tools.MyRequestQueue;

public class ListOffersActivity extends AppCompatActivity {

    final public static String LOG_TAG = "ListOffersActivity";

    private ApiResponse offers = null;
    private OfferParameters parameters = new OfferParameters();
    private View rootView = null;
    private RecyclerView mRecyclerView;
    private OfferItemAdapter adapter;
    private Response.Listener<String> mRequestListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Gson gson = new Gson();
            offers = gson.fromJson(response, ApiResponse.class);
            updateAdapter();
        }
    };
    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            offers = null;
            Snackbar snackbar = Snackbar
                    .make(rootView, R.string.loading_offers_error, Snackbar.LENGTH_SHORT);
            snackbar.show();

        }
    };

    private void updateAdapter() {

        if (offers == null) {
            return;
        }

        final List<OfferItem> offerItemList = new ArrayList<>();

        for (SingleOffer offer : offers.offers) {
            offerItemList.add(OfferItem.from(offer));
        }

        if ( offerItemList.size() == 0 ) {
            Snackbar snackbar = Snackbar
                    .make(rootView, R.string.loading_offers_no, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        adapter = new OfferItemAdapter(ListOffersActivity.this, offerItemList);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MyApplication app = (MyApplication) getApplicationContext();

        setContentView(R.layout.activity_list_offers);
        rootView = findViewById(android.R.id.content);

        parameters.appId = Integer.valueOf(MoreObjects.firstNonNull(getIntent().getStringExtra("appId"), String.valueOf(Constants.DEFAULT_APP_ID)));
        parameters.uid = MoreObjects.firstNonNull(getIntent().getStringExtra("uid"), Constants.DEFAULT_UID);
        parameters.apiKey = MoreObjects.firstNonNull(getIntent().getStringExtra("apiKey"), Constants.DEFAULT_API_KEY);
        parameters.googleAdId = app.googleAdId;
        parameters.googleAdIdLimitedTrackingEnabled = app.googleAdIdLimitedTrackingEnabled;
        if (getIntent().hasExtra("pub0")) {
            parameters.pub.add(getIntent().getStringExtra("pub0"));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadOfferData();

    }

    private void startRequest() {
        final String url = parameters.getUrl();
        StringRequest request = new StringRequest(Request.Method.GET, url, mRequestListener, mErrorListener);
        MyRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void loadOfferData() {
        Snackbar snackbar = Snackbar
                .make(rootView, R.string.loading_offers, Snackbar.LENGTH_SHORT);
        snackbar.show();
        startRequest();

    }
}
