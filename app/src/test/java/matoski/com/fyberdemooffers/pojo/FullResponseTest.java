package matoski.com.fyberdemooffers.pojo;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;

import java.io.File;

import matoski.com.fyberdemooffers.pojo.offer.ApiResponse;
import matoski.com.fyberdemooffers.pojo.offer.OfferType;
import matoski.com.fyberdemooffers.pojo.offer.SingleOffer;

public class FullResponseTest {

    @Test
    public void testJsonResponseValidDataLarge() throws Exception {

        Gson gson = new Gson();
        String jsonData = Files.toString(new File("../resources/offers-30.json"), Charsets.UTF_8);

        ApiResponse response = gson.fromJson(jsonData, ApiResponse.class);
        Assert.assertEquals(response.message, "OK");
        Assert.assertEquals(response.code, "OK");

        Assert.assertEquals((int) response.count, 30);
        Assert.assertEquals((int) response.pages, 4);

        Assert.assertTrue(response.offers.size() == 30);

    }

    @Test
    public void testJsonResponseValidDataSmall() throws Exception {
        Gson gson = new Gson();
        String jsonData = Files.toString(new File("../resources/offers-1.json"), Charsets.UTF_8);

        ApiResponse response = gson.fromJson(jsonData, ApiResponse.class);

        Assert.assertEquals(response.message, "OK");
        Assert.assertEquals(response.code, "OK");
        Assert.assertEquals((int) response.count, 1);
        Assert.assertEquals((int) response.pages, 1);

        Assert.assertEquals(response.information.app_name, "SP Test App");
        Assert.assertEquals(response.information.appid, "157");
        Assert.assertEquals(response.information.country, "US");
        Assert.assertEquals(response.information.language, "EN");
        Assert.assertEquals(response.information.support_url, "http://iframe.fyber.com/mobile/DE/157/my_offers");
        Assert.assertEquals(response.information.virtual_currency, "Coins");

        Assert.assertTrue(response.offers.size() == 1);

        SingleOffer offer = response.offers.get(0);

        Assert.assertEquals(offer.title, "Tap  Fish");
        Assert.assertEquals(offer.offer_id, "13554");
        Assert.assertEquals(offer.teaser, "Download and START");
        Assert.assertEquals(offer.required_actions, "Download and START");
        Assert.assertEquals(offer.link, "http://iframe.fyber.com/mbrowser?appid=157&lpid=11387&uid=player1");

        Assert.assertEquals(offer.thumbnail.hires, "http://cdn.fyber.com/assets/1808/icon175x175-2_square_175.png");
        Assert.assertEquals(offer.thumbnail.lowres, "http://cdn.fyber.com/assets/1808/icon175x175-2_square_60.png");

        Assert.assertEquals((int) offer.payout, 90);
        Assert.assertEquals((int) offer.time_to_payout.amount, 1800);
        Assert.assertEquals(offer.time_to_payout.readable, "30 minutes");

        Assert.assertTrue(offer.offer_types.size() == 2);

        OfferType offerType = offer.offer_types.get(0);

        Assert.assertEquals(offerType.offer_type_id, "101");
        Assert.assertEquals(offerType.readable, "Download");
    }


}