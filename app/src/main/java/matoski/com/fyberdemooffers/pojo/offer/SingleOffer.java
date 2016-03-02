package matoski.com.fyberdemooffers.pojo.offer;

import java.util.ArrayList;

public class SingleOffer {

    public String title;
    public String offer_id;
    public String teaser;
    public String required_actions;
    public String link;
    public ArrayList<OfferType> offer_types;
    public Thumbnail thumbnail;
    public Integer payout;
    public TimeToPayout time_to_payout;

    public SingleOffer() {
        offer_types = new ArrayList<>();
        thumbnail = new Thumbnail();
        time_to_payout = new TimeToPayout();
    }
}
