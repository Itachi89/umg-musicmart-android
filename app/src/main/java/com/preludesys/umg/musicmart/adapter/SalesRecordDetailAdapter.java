package com.preludesys.umg.musicmart.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.SalesRecordDetail;
import com.preludesys.umg.musicmart.util.PartnerImage;

import java.util.List;

/**
 * Created by varunsundaramoorthy on 5/2/14.
 */
public class SalesRecordDetailAdapter extends ArrayAdapter<SalesRecordDetail> {
    Context context;
    int layoutResourceId;
    List<SalesRecordDetail> myList;
    double x;
    double y;
    private Typeface typeface;

    public SalesRecordDetailAdapter(Context context,int layoutResourceId, List<SalesRecordDetail> myList, Typeface typeface){
        super(context,layoutResourceId, myList);
        this.context=context;
        this.layoutResourceId=layoutResourceId;
        this.typeface=typeface;
        this.myList=myList;
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
            holder.image = (ImageView) row.findViewById(R.id.detail_image);
            holder.lwtdText = (TextView) row.findViewById(R.id.item_lwtd1);
            holder.wtdText = (TextView) row.findViewById(R.id.item_wtd1);
            holder.rtdText = (TextView) row.findViewById(R.id.item_rtd1);
            holder.keylwtd =(TextView) row.findViewById(R.id.key_lwtd1);
            holder.keywtd =(TextView) row.findViewById(R.id.key_wtd1);
            holder.keyrtd =(TextView) row.findViewById(R.id.key_rtd1);
            holder.imageViewtext=(TextView) row.findViewById(R.id.myImageViewText);

            setTypeface(holder);
            row.setTag(holder);
        } else {
            holder = (MusicMartViewHolder) row.getTag();
        }
        Log.d(this.getClass().toString(), ">>>>> ############################## View Position: " + position);
        SalesRecordDetail currentSong = myList.get(position);
        x = (double) (Integer.parseInt(currentSong.getWtd()));
        y = (double) (Integer.parseInt(currentSong.getLwtd()));
        int var = (int) Math.round(((x - y) / y) * 100);

        holder.lwtdText.setText(currentSong.getLwtd());
        holder.wtdText.setText(currentSong.getWtd());
        holder.imageViewtext.setText(currentSong.getPartner());
        holder.image.setImageResource(PartnerImage.partnerImage(currentSong));


        if(var<0){
            holder.rtdText.setText(var + "%");
            holder.rtdText.setTextColor(Color.parseColor("#BB000A"));
        }else if (var>0){
            holder.rtdText.setText(var + "%");
            holder.rtdText.setTextColor(Color.parseColor("#21A516"));
        }else{
            holder.rtdText.setText("NR");
            holder.rtdText.setTextColor(Color.parseColor("#E29D3C"));
        }
        return row;
    }

    protected void setTypeface(MusicMartViewHolder holder){
        Log.d(this.getClass().toString(), ">>>>> ############################## Setting Typeface: " + holder.lwtdText);

        holder.lwtdText.setTypeface(typeface);
        holder.rtdText.setTypeface(typeface);
        holder.keylwtd.setTypeface(typeface);
        holder.keywtd.setTypeface(typeface);
        holder.keyrtd.setTypeface(typeface);
        holder.imageViewtext.setTypeface(typeface);
    }

    static class MusicMartViewHolder {
        ImageView image;
        TextView lwtdText;
        TextView wtdText;
        TextView rtdText;
        TextView keylwtd;
        TextView keywtd;
        TextView keyrtd;
        TextView imageViewtext;

    }

}
