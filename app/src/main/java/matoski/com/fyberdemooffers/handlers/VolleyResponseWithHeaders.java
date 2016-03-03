package matoski.com.fyberdemooffers.handlers;

import java.util.Map;

public interface VolleyResponseWithHeaders<T> {
    /** Called when a response is received. */
    public void onResponse(T response, Map<String, String> headers);
}
