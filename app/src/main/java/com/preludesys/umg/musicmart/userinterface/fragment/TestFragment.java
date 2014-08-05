package com.preludesys.umg.musicmart.userinterface.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.preludesys.umg.musicmart.MusicMartApplication;
import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.SalesRecordDetail;
import com.preludesys.umg.musicmart.util.FontHelper;
import com.preludesys.umg.musicmart.util.NullPointerCheck;
import com.preludesys.umg.musicmart.util.PartnerImage;

/**
 * Created by varunsundaramoorthy on 5/14/14.
 */
public class TestFragment extends Fragment {
    SalesRecordDetail salesRecordDetail = new SalesRecordDetail();
    double x;
    double y;

    public static TestFragment newInstance(SalesRecordDetail sales){
        TestFragment test = new TestFragment();
        test.salesRecordDetail=sales;
        NullPointerCheck npc= new NullPointerCheck();
        npc.nullPointerCheck(test.salesRecordDetail);
        return test;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.store_details_popup, container,false);

        TextView wtd = (TextView) v.findViewById(R.id.store_wtd);
        TextView lwtd = (TextView) v.findViewById(R.id.store_lwtd);
        TextView variance = (TextView) v.findViewById(R.id.store_rtd);
        TextView lastUpdate = (TextView) v.findViewById(R.id.store_update);
        TextView release = (TextView) v.findViewById(R.id.store_release);
        TextView country = (TextView) v.findViewById(R.id.store_country);
        TextView lastWeek = (TextView) v.findViewById(R.id.store_week);
        TextView rank = (TextView) v.findViewById(R.id.store_rank);
        TextView date = (TextView) v.findViewById(R.id.store_date);
        TextView time = (TextView) v.findViewById(R.id.store_time);
        TextView phr = (TextView) v.findViewById(R.id.store_phr);
        TextView date1 = (TextView) v.findViewById(R.id.store_date1);
        TextView time1 = (TextView) v.findViewById(R.id.store_time1);
        TextView keyWtd =(TextView) v.findViewById(R.id.key_wtd);
        TextView keyLwtd =(TextView) v.findViewById(R.id.key_lwtd);
        TextView keyRtd =(TextView) v.findViewById(R.id.key_rtd);
        TextView keyCountry =(TextView) v.findViewById(R.id.key_country);
        TextView keyRelease =(TextView) v.findViewById(R.id.key_release);
        TextView keyUpdate =(TextView) v.findViewById(R.id.key_update);
        TextView keyWeek =(TextView) v.findViewById(R.id.key_week);
        TextView keyRank =(TextView) v.findViewById(R.id.key_rank);
        TextView keyPhr =(TextView) v.findViewById(R.id.key_phr);
        TextView itemItunes =(TextView) v.findViewById(R.id.item_iTunes);
        TextView storeDetail =(TextView) v.findViewById(R.id.detail_Stores);
        ImageView close =(ImageView) v.findViewById(R.id.detail_close);
        ImageView partnerImage =(ImageView) v.findViewById(R.id.detail_image);

        wtd.setText(salesRecordDetail.getWtd());
        lwtd.setText(salesRecordDetail.getLwtd());
        variance.setText(variance(salesRecordDetail.getWtd(),salesRecordDetail.getLwtd())+"%");
        lastUpdate.setText(salesRecordDetail.getDate());
        release.setText(salesRecordDetail.getRtd());
        country.setText(salesRecordDetail.getCountry());
        lastWeek.setText(salesRecordDetail.getLw());
        rank.setText(salesRecordDetail.getItunesRank());
        date.setText(salesRecordDetail.getDate());
        time.setText(salesRecordDetail.getUpdatedDate());
        phr.setText(salesRecordDetail.getItunesPreviousRank());
        date1.setText(salesRecordDetail.getDate());
        time1.setText(salesRecordDetail.getUpdatedDate());
        itemItunes.setText(salesRecordDetail.getPartner());
        partnerImage.setImageResource(PartnerImage.partnerImage(salesRecordDetail));

        lwtd.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        variance.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        lastUpdate.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        release.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        country.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        date.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        rank.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        lastWeek.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        time.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        wtd.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        phr.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        date1.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        time1.setTypeface( ((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyCountry.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyLwtd.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyPhr.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyRank.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyRelease.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyRtd.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyUpdate.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyWeek.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        keyWtd.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        itemItunes.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());
        storeDetail.setTypeface(((MusicMartApplication) getActivity().getApplication()).getTypeFace());

        FontHelper.sePaintFlags(itemItunes);
        FontHelper.sePaintFlags(keyCountry);
        FontHelper.sePaintFlags(keyLwtd);
        FontHelper.sePaintFlags(keyPhr);
        FontHelper.sePaintFlags(keyRank);
        FontHelper.sePaintFlags(keyRelease);
        FontHelper.sePaintFlags(keyRtd);
        FontHelper.sePaintFlags(keyUpdate);
        FontHelper.sePaintFlags(keyWeek);
        FontHelper.sePaintFlags(keyWtd);
        FontHelper.sePaintFlags(release);
        FontHelper.sePaintFlags(lastUpdate);
        FontHelper.sePaintFlags(country);
        FontHelper.sePaintFlags(date);
        FontHelper.sePaintFlags(date1);
        FontHelper.sePaintFlags(lastWeek);
        FontHelper.sePaintFlags(storeDetail);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TestFragment.this.dismiss();
            }
        });




        return v;
    }

    public int variance(String a,String b){
        x = (double) (Integer.parseInt(a));
        y = (double) (Integer.parseInt(b));
        int var = (int) Math.round(((x - y) / y) * 100);

        return var;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
