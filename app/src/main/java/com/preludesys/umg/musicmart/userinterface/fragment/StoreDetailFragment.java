package com.preludesys.umg.musicmart.userinterface.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.SalesRecordDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by varunsundaramoorthy on 5/1/14.
 */
public class StoreDetailFragment extends DialogFragment {
    SalesRecordDetail salesRecordDetail = new SalesRecordDetail();
    List<SalesRecordDetail> detailItems = new ArrayList<SalesRecordDetail>();


    public StoreDetailFragment(SalesRecordDetail salesRecordDetail,List<SalesRecordDetail> detailItems){
        this.salesRecordDetail=salesRecordDetail;
        this.detailItems=detailItems;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewer = inflater.inflate(R.layout.view_pager, container,false);
        ViewPager viewPager = (ViewPager) viewer.findViewById(R.id.pager);
        List<Fragment> fragments = getFragments(detailItems.size(),detailItems);
        Log.d(this.getClass().toString(),".........fragments" +fragments.isEmpty());
        ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(screenSlidePagerAdapter);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        return viewer;
    }

    private List<Fragment> getFragments(int count,List<SalesRecordDetail> detailItem){
        List<SalesRecordDetail>detailItems;
        detailItems=detailItem;
        List<Fragment> fList = new ArrayList<Fragment>();
        for(int i=0; i<count;i++){
            fList.add(TestFragment.newInstance(detailItems.get(i)));
        }
        return fList;
    }
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragments;
        public ScreenSlidePagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }


        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
