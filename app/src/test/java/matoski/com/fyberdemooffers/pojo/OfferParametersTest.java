package matoski.com.fyberdemooffers.pojo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class OfferParametersTest {

    final static String RESOURCE_URI = "appid=2070&format=json&google_ad_id=38400000-8cf0-11bd-b23e-10b96e40000d&google_ad_id_limited_tracking_enabled=true&ip=109.235.143.113&locale=de&offer_types=112&os_version=4.1.1&page=1&timestamp=1456852187&uid=spiderman";
    final static String HASH_KEY = "117e5c30fef1630fb91f2af949cf23258b806593";
    final static String FULL_RESOURCE_URI = RESOURCE_URI.concat("&hashkey=").concat(HASH_KEY);
    final static String RESOURCE_URL = OfferParameters.OFFER_BASE_URI.concat("?").concat(FULL_RESOURCE_URI);

    @Test
    public void testResourceUrl() throws Exception {
        Assert.assertEquals(RESOURCE_URL, offerParameters.getUrl());
    }

    private OfferParameters offerParameters;

    @Before
    public void setUp() throws Exception {
        offerParameters = new OfferParameters();
        offerParameters.osVersion = "4.1.1";
        offerParameters.offerTypes = "112";
        offerParameters.googleAdId = "38400000-8cf0-11bd-b23e-10b96e40000d";
        offerParameters.googleAdIdLimitedTrackingEnabled = true;
        offerParameters.timestamp = 1456852187L;
    }

    @Test
    public void testGetResourceUri() throws Exception {
        Assert.assertEquals(RESOURCE_URI, offerParameters.getResourceUri());
    }

    @Test
    public void testGetHashKey() throws Exception {
        Assert.assertEquals(HASH_KEY, offerParameters.getHashKey());
    }

    @Test
    public void testGetResourceUriWithApiKey() throws Exception {
        Assert.assertEquals(FULL_RESOURCE_URI, offerParameters.getResourceUriWithApiKey());
    }
}