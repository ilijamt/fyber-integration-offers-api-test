package matoski.com.fyberdemooffers.pojo.offer;

import java.util.ArrayList;

public class ApiResponse {

    public String code;
    public String message;
    public Integer count;
    public Integer pages;

    public Information information;
    public ArrayList<SingleOffer> offers;

    public ApiResponse() {
        offers = new ArrayList<>();
    }
}
