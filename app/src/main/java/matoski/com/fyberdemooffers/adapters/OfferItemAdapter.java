package matoski.com.fyberdemooffers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import matoski.com.fyberdemooffers.R;
import matoski.com.fyberdemooffers.pojo.OfferItem;
import matoski.com.fyberdemooffers.tools.MyRequestQueue;


public class OfferItemAdapter extends ArrayAdapter<OfferItem> {


    public OfferItemAdapter(Context context, ArrayList<OfferItem> items) {
        super(context, R.layout.offer_item_child, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OfferItem item = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.offer_item_child, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.offer_title);
            viewHolder.teaser = (TextView) convertView.findViewById(R.id.offer_teaser);
            viewHolder.payout = (TextView) convertView.findViewById(R.id.offer_payout);
            viewHolder.thumbnail = (NetworkImageView) convertView.findViewById(R.id.offer_thumbnail);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(item.getTitle());
        viewHolder.teaser.setText(item.getTeaser());
        viewHolder.payout.setText(String.valueOf(item.getPayout()));
        viewHolder.thumbnail.setImageUrl(item.getThumbnail(), MyRequestQueue.getInstance(parent.getContext()).getImageLoader());

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView teaser;
        TextView payout;
        NetworkImageView thumbnail;
    }
}
