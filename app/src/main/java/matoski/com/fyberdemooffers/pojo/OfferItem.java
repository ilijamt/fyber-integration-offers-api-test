package matoski.com.fyberdemooffers.pojo;

import matoski.com.fyberdemooffers.pojo.offer.SingleOffer;

public class OfferItem {

    private String title;
    private String teaser;
    private String thumbnail;
    private Integer payout;
    private String link;

    public OfferItem() {
        this(null, null, null, null, null);
    }

    public OfferItem(String title, String teaser, String thumbnail, Integer payout) {
        this(title, teaser, thumbnail, payout, null);
    }

    public OfferItem(String title, String teaser, String thumbnail, Integer payout, String link) {
        this.title = title;
        this.teaser = teaser;
        this.thumbnail = thumbnail;
        this.payout = payout;
        this.link = link;
    }

    public static OfferItem from(SingleOffer offer) {
        OfferItem item = new OfferItem();

        item.setTitle(offer.title);
        item.setTeaser(offer.teaser);
        item.setPayout(offer.payout);
        item.setThumbnail(offer.thumbnail.hires);
        item.setLink(offer.link);

        return item;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getPayout() {
        return payout;
    }

    public void setPayout(Integer payout) {
        this.payout = payout;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
