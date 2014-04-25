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
import com.preludesys.umg.musicmart.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;


public class SalesRecordAdapter extends  ArrayAdapter<SalesRecord> {
	Context context;
	int layoutResourceId;
	List<SalesRecord> myList;
    double x;
    double y;


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
            Log.d(this.getClass().toString(), ">>>>> ############################## View Position: " + position);
            SalesRecord currentSong = myList.get(position);
            x = (double) (Integer.parseInt(currentSong.getWtd()));
            y = (double) (Integer.parseInt(currentSong.getLwtd()));
            int var = (int) Math.round(((x - y) / y) * 100);

            if(currentSong.getImageUrl()==null){
                holder.image.setImageResource(R.drawable.music2x);
            }
            else{

            String imagePath = currentSong.getImageUrl();
            imagePath = (imagePath.contains("mzstatic.com") ? imagePath : imagePath.toLowerCase());
            Log.d(this.getClass().toString(), ">>>>> Image Path: " + imagePath);
            UrlImageViewHelper.setUrlDrawable(holder.image, imagePath, null, UrlImageViewHelper.CACHE_DURATION_ONE_DAY);
            }
            holder.titleText.setText(currentSong.getTitle());
            holder.artistText.setText(currentSong.getArtistId());
            holder.lwtdText.setText(currentSong.getLwtd());
            holder.wtdText.setText(currentSong.getWtd());
            holder.rtdText.setText(var + "%");

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
