package matoski.com.fyberdemooffers.pojo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class OfferParametersNoGATest {

    final static String RESOURCE_URI = "appid=2070&format=json&google_ad_id=&google_ad_id_limited_tracking_enabled=false&ip=109.235.143.113&locale=de&offer_types=112&os_version=4.1.1&page=1&timestamp=1456852187&uid=spiderman";
    final static String HASH_KEY = "9ca457ba97bf615deedef62b2327a55d305ca38b";
    final static String FULL_RESOURCE_URI = RESOURCE_URI.concat("&hashkey=").concat(HASH_KEY);
    final static String RESOURCE_URL = OfferParameters.OFFER_BASE_URI.concat("?").concat(FULL_RESOURCE_URI);
    private OfferParameters offerParameters;

    @Test
    public void testResourceUrl() throws Exception {
        Assert.assertEquals(RESOURCE_URL, offerParameters.getUrl());
    }

    @Before
    public void setUp() throws Exception {
        offerParameters = new OfferParameters();
        offerParameters.osVersion = "4.1.1";
        offerParameters.offerTypes = "112";
        offerParameters.googleAdIdLimitedTrackingEnabled = false;
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