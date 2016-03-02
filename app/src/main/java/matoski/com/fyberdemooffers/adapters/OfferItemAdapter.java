package matoski.com.fyberdemooffers.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import matoski.com.fyberdemooffers.R;
import matoski.com.fyberdemooffers.pojo.OfferItem;
import matoski.com.fyberdemooffers.tools.MyRequestQueue;


public class OfferItemAdapter extends RecyclerView.Adapter<OfferItemAdapter.ViewHolder> {

    final private Context mContext;
    private List<OfferItem> offerItemList;
    final private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            int position = holder.getAdapterPosition();
            OfferItem item = offerItemList.get(position);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(item.getLink()));
            mContext.startActivity(intent);
        }
    };

    public OfferItemAdapter(Context context, List<OfferItem> items) {
        mContext = context;
        offerItemList = items;
    }

    public List<OfferItem> getOfferItemList() {
        return offerItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item_child, null);
        ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(clickListener);
        v.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        OfferItem item = offerItemList.get(position);

        viewHolder.title.setText(item.getTitle());
        viewHolder.teaser.setText(item.getTeaser());
        viewHolder.payout.setText(String.valueOf(item.getPayout()));
        viewHolder.thumbnail.setImageUrl(item.getThumbnail(), MyRequestQueue.getInstance(mContext.getApplicationContext()).getImageLoader());

    }

    @Override
    public int getItemCount() {
        return (null != offerItemList ? offerItemList.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView teaser;
        TextView payout;
        NetworkImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.offer_title);
            teaser = (TextView) itemView.findViewById(R.id.offer_teaser);
            payout = (TextView) itemView.findViewById(R.id.offer_payout);
            thumbnail = (NetworkImageView) itemView.findViewById(R.id.offer_thumbnail);

        }
    }

}
