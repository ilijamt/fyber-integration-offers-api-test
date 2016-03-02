package matoski.com.fyberdemooffers.pojo;

import android.os.Build;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import matoski.com.fyberdemooffers.Constants;

public class OfferParameters {

    final public static String OFFER_BASE_URI = "http://api.fyber.com/feed/v1/offers.json";

    public String osVersion = Build.VERSION.RELEASE;
    public String format = "json";
    public Integer appId = Constants.DEFAULT_APP_ID;
    public String apiKey = Constants.DEFAULT_API_KEY;
    public String uid = Constants.DEFAULT_UID;
    public String locale = Constants.DEFAULT_LOCALE;
    public String googleAdId;
    public Boolean googleAdIdLimitedTrackingEnabled = false;
    public Long timestamp;
    public String ip = Constants.DEFAULT_IP;
    public ArrayList<String> pub;
    public Integer page = 1;
    public String offerTypes;
    public Long psTime;
    public String device;

    public OfferParameters() {
        pub = new ArrayList<>();
    }

    protected Map<String, String> buildParamMap() {
        final Map<String, String> map = new TreeMap<String, String>();

        // add required parameters
        map.put("format", Preconditions.checkNotNull(format));
        map.put("appid", Preconditions.checkNotNull(appId).toString());
        map.put("uid", Preconditions.checkNotNull(uid));
        map.put("locale", Preconditions.checkNotNull(locale));
        map.put("os_version", Preconditions.checkNotNull(osVersion));
        map.put("timestamp", timestamp == null ? Long.toString(new Date().getTime() / 1000) : timestamp.toString());
        map.put("google_ad_id", googleAdId == null ? "" : googleAdIdLimitedTrackingEnabled ? googleAdId : "");
        map.put("google_ad_id_limited_tracking_enabled", googleAdIdLimitedTrackingEnabled ? "true" : "false");

        // add optional parameters
        map.put("ip", ip);

        if (!(page == null))
            map.put("page", page.toString());

        if (!(offerTypes == null))
            map.put("offer_types", offerTypes);

        if (!(psTime == null))
            map.put("ps_time", psTime.toString());

        if (!(device == null))
            map.put("device", device);

        // generate the pub parameters
        for (int i = 0; i < pub.size(); i++) {
            map.put("pub".concat(Integer.toString(i)), pub.get(i));
        }

        return map;
    }

    public String getUrl() {
        return OFFER_BASE_URI.concat("?").concat(getResourceUriWithApiKey());
    }

    public String getResourceUri() {
        return Joiner.on("&").withKeyValueSeparator("=").join(buildParamMap());
    }

    public String getHashKey() {
        final HashCode hashCode = Hashing.sha1().hashString(getResourceUri().concat("&").concat(apiKey), Charset.defaultCharset());
        return hashCode.toString().toLowerCase();
    }

    public String getResourceUriWithApiKey() {
        return getResourceUri().concat("&hashkey=").concat(getHashKey());
    }

}