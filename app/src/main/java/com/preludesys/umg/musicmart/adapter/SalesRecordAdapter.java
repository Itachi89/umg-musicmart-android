package com.preludesys.umg.musicmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.SalesRecord;

import java.util.List;


public class SalesRecordAdapter extends  ArrayAdapter<SalesRecord> {
	Context context;
	int layoutResourceId;
	List<SalesRecord> myList;

	public SalesRecordAdapter(Context context, int layoutResourceId, List<SalesRecord> myList) {
		super(context, layoutResourceId, myList);
		Log.d(this.getClass().toString(),">>>>>> Inside SalesRecordAdapter: ");
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.myList = myList;
	}

    public SalesRecordAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);
        Log.d(this.getClass().toString(),">>>>>> Inside SalesRecordAdapter: ");
        this.layoutResourceId = layoutResourceId;
        this.context = context;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		MusicMartViewHolder holder = null;
        Log.d(this.getClass().toString(), ">>>>>>>>>>>> Inside Get View");
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new MusicMartViewHolder();
			holder.image = (ImageView) row.findViewById(R.id.item_icon);
			holder.titleText = (TextView) row.findViewById(R.id.item_Title);
			holder.artistText = (TextView) row.findViewById(R.id.item_Artistid);
			holder.lwtdText = (TextView) row.findViewById(R.id.item_lwtd);
			holder.wtdText = (TextView) row.findViewById(R.id.item_wtd);
            holder.rtdText = (TextView) row.findViewById(R.id.item_rtd);
			row.setTag(holder);
		} else {
			holder = (MusicMartViewHolder) row.getTag();
		}

        Log.d(this.getClass().toString(), ">>>>> ############################## View Position: " +  position);
		SalesRecord currentSong = myList.get(position);
		
		
		//String imagePath = ApplicationProperties.getBaseUrl() + currentSong.getImageUrl();
		//Log.d(">>>>> Image Path: " +  imagePath);
		//UrlImageViewHelper.setUrlDrawable(holder.image, imagePath, null, UrlImageViewHelper.CACHE_DURATION_ONE_DAY);
        holder.image.setImageResource(R.drawable.bug);
        holder.titleText.setText(currentSong.getTitle());
		holder.artistText.setText(currentSong.getArtistId());
		holder.lwtdText.setText(currentSong.getLwtd());
		holder.wtdText.setText(currentSong.getWtd());
		holder.rtdText.setText(currentSong.getRtd());
		return row;
	}

	static class MusicMartViewHolder {
		ImageView image;
		TextView titleText;
		TextView artistText;
		TextView lwtdText;
		TextView wtdText;
		TextView rtdText;
	}
}
