package matoski.com.fyberdemooffers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

import matoski.com.fyberdemooffers.adapters.OfferItemAdapter;
import matoski.com.fyberdemooffers.pojo.OfferItem;
import matoski.com.fyberdemooffers.pojo.OfferParameters;
import matoski.com.fyberdemooffers.pojo.offer.ApiResponse;
import matoski.com.fyberdemooffers.pojo.offer.SingleOffer;
import matoski.com.fyberdemooffers.tools.MyRequestQueue;

public class ListOffersActivity extends AppCompatActivity {

    final public static String LOG_TAG = "ListOffersActivity";
    private FloatingActionButton fabReload;
    private ApiResponse offers = null;
    private OfferParameters parameters = new OfferParameters();
    private OfferItemAdapter adapter;
    final private AdapterView.OnItemClickListener listItemOfferClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            OfferItem item = adapter.getItem(position);

            if (!(item.getLink() == null)) {
                String url = item.getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }


        }
    };
    private ListView listViewOffers;
    private View rootView = null;
    final private View.OnClickListener listenerReload = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadOfferData();
        }
    };

    private void updateAdapter() {

        adapter.clear();

        if (offers == null) {

            Snackbar snackbar = Snackbar
                    .make(rootView, R.string.loading_offers_no, Snackbar.LENGTH_SHORT);
            snackbar.show();

            return;
        }

        final ArrayList<OfferItem> offerItemArrayList = new ArrayList<>();
        for (SingleOffer offer : offers.offers) {
            adapter.add(OfferItem.from(offer));
        }

        adapter.addAll(offerItemArrayList);

        if (adapter.getCount() == 0) {
            Snackbar snackbar = Snackbar
                    .make(rootView, R.string.loading_offers_no, Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_offers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rootView = findViewById(android.R.id.content);
        adapter = new OfferItemAdapter(this, new ArrayList<OfferItem>());
        listViewOffers = (ListView) findViewById(R.id.listview_offers);
        listViewOffers.setNestedScrollingEnabled(true);
        listViewOffers.setAdapter(adapter);
        listViewOffers.setOnItemClickListener(listItemOfferClick);

        fabReload = (FloatingActionButton) findViewById(R.id.fab_refresh);
        fabReload.setOnClickListener(listenerReload);

        parameters.appId = Integer.valueOf(getIntent().getStringExtra("appId"));
        parameters.uid = getIntent().getStringExtra("uid");
        parameters.apiKey = getIntent().getStringExtra("apiKey");

        final MyApplication app = (MyApplication) getApplicationContext();

        parameters.googleAdId = app.googleAdId;
        parameters.googleAdIdLimitedTrackingEnabled = app.googleAdIdLimitedTrackingEnabled;

        if (getIntent().hasExtra("pub0")) {
            parameters.pub.add(getIntent().getStringExtra("pub0"));
        }

        updateAdapter();
        loadOfferData();

    }

    private void loadOfferData() {
        Snackbar snackbar = Snackbar
                .make(rootView, R.string.loading_offers, Snackbar.LENGTH_SHORT);
        snackbar.show();

        final String url = parameters.getUrl();
        Log.d(LOG_TAG, url);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        offers = gson.fromJson(response, ApiResponse.class);
                        updateAdapter();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        offers = null;
                        Snackbar snackbar = Snackbar
                                .make(rootView, R.string.loading_offers_error, Snackbar.LENGTH_SHORT);
                        snackbar.show();

                    }
                });

        MyRequestQueue.getInstance(getApplicationContext()).addToRequestQueue(request);

    }
}
