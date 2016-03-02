package matoski.com.fyberdemooffers.pojo;

import junit.framework.Assert;

import org.junit.Test;

import matoski.com.fyberdemooffers.pojo.offer.SingleOffer;

public class OfferItemTest {

    public static final String TITLE = "Title";
    public static final String TEASER = "Teaser";
    public static final int PAYOUT = 900;
    public static final String HTTP_EXAMPLE_COM_PICTURE_PNG = "http://example.com/picture.png";
    public static final String HTTP_EXAMPLE_COM = "http://example.com";

    @Test
    public void testFromOfferItem() throws Exception {
        SingleOffer offer = new SingleOffer();
        offer.title = TITLE;
        offer.teaser = TEASER;
        offer.link = HTTP_EXAMPLE_COM;
        offer.thumbnail.hires = HTTP_EXAMPLE_COM_PICTURE_PNG;
        offer.payout = PAYOUT;
        OfferItem item = OfferItem.from(offer);
        Assert.assertEquals(item.getTitle(), TITLE);
        Assert.assertEquals(item.getTeaser(), TEASER);
        Assert.assertEquals((int) item.getPayout(), PAYOUT);
        Assert.assertEquals(item.getThumbnail(), HTTP_EXAMPLE_COM_PICTURE_PNG);
        Assert.assertEquals(item.getLink(), HTTP_EXAMPLE_COM);
    }

    @Test
    public void testParametersFromConstructor() throws Exception {
        OfferItem item = new OfferItem(TITLE, TEASER, HTTP_EXAMPLE_COM_PICTURE_PNG, PAYOUT);
        Assert.assertEquals(item.getTitle(), TITLE);
        Assert.assertEquals(item.getTeaser(), TEASER);
        Assert.assertEquals((int) item.getPayout(), PAYOUT);
        Assert.assertEquals(item.getThumbnail(), HTTP_EXAMPLE_COM_PICTURE_PNG);
        Assert.assertTrue(item.getLink() == null);
    }

    @Test
    public void testValues() throws Exception {
        OfferItem item = new OfferItem();
        item.setTitle(TITLE);
        item.setTeaser(TEASER);
        item.setThumbnail(HTTP_EXAMPLE_COM_PICTURE_PNG);
        item.setPayout(PAYOUT);
        item.setLink(HTTP_EXAMPLE_COM);
        Assert.assertEquals(item.getTitle(), TITLE);
        Assert.assertEquals(item.getTeaser(), TEASER);
        Assert.assertEquals((int) item.getPayout(), PAYOUT);
        Assert.assertEquals(item.getThumbnail(), HTTP_EXAMPLE_COM_PICTURE_PNG);
        Assert.assertEquals(item.getLink(), HTTP_EXAMPLE_COM);
    }
}