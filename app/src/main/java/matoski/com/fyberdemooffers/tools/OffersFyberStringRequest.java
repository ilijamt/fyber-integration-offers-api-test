package matoski.com.fyberdemooffers.tools;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import matoski.com.fyberdemooffers.handlers.VolleyResponseWithHeaders;

public class OffersFyberStringRequest extends StringRequest {

    private final VolleyResponseWithHeaders<String> mResponseHeadersListener;

    protected Map<String, String> responseHeaders;

    public OffersFyberStringRequest(String url, VolleyResponseWithHeaders responseValidation, Response.ErrorListener errorListener) {
        super(url, null, errorListener);
        mResponseHeadersListener = responseValidation;
    }

    @Override
    protected void deliverResponse(String response) {
        mResponseHeadersListener.onResponse(response, responseHeaders);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        responseHeaders = response.headers;
        return super.parseNetworkResponse(response);
    }

}
