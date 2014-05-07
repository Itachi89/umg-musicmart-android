package com.preludesys.umg.musicmart.adapter;

/**
 * Created by varunsundaramoorthy on 4/28/14.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.NavDrawerItem;

import java.util.ArrayList;



public class SlidingMenuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private Typeface typeface;


    public SlidingMenuAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems,Typeface typeface){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        this.typeface= typeface;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row= convertView;
        SlidingMenuViewHolder holder = null;

        if (row == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = mInflater.inflate(R.layout.drawer_list_item, null);
            holder= new SlidingMenuViewHolder();

            holder.imgIcon = (ImageView) row.findViewById(R.id.icon);
            holder.txtTitle = (TextView) row.findViewById(R.id.title);
            holder.txtCount = (TextView) row.findViewById(R.id.counter);
            setTypeface(holder);
            row.setTag(holder);
        }else{
            holder = (SlidingMenuViewHolder) row.getTag();
        }

        NavDrawerItem currentItem = navDrawerItems.get(position);
        holder.imgIcon.setImageResource(currentItem.getIcon());
        holder.txtTitle.setText(currentItem.getTitle());

        // displaying count
        // check whether it set visible or not
        if(currentItem.getCounterVisibility()){
            holder.txtCount.setText(currentItem.getCount());
        }else{
            // hide the counter view
            holder.txtCount.setVisibility(View.GONE);
        }

        return row;
    }
    protected void setTypeface(SlidingMenuViewHolder holder){
        Log.d(this.getClass().toString(), ">>>>> ############################## Setting Typeface: " + holder.txtTitle);

        holder.txtTitle.setTypeface(typeface);
    }
    static class SlidingMenuViewHolder{
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtCount;
    }

}
