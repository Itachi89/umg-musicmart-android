package com.preludesys.umg.musicmart.userinterface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.adapter.SalesRecordAdapter;
import com.preludesys.umg.musicmart.listener.CallBackListener;
import com.preludesys.umg.musicmart.model.SalesRecord;
import com.preludesys.umg.musicmart.task.SalesRecordListAsyncTask;
import com.preludesys.umg.musicmart.userinterface.activity.SalesRecordDetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varunsundaramoorthy on 4/28/14.
 */
public class SalesRecordListFragment extends MusicMartFragment implements CallBackListener<List<SalesRecord>> {
    final List<SalesRecord> salesRecordItems = new ArrayList<SalesRecord>();
    public long offset=0;
    int newPosition =0;
    int increment = 20;
    private String listCategory = "songs";
    private View rootView;
    final SalesRecordListFragment salesRecordListFragment = this;
    FragmentManager fragmentManager;

    public String getListCategory() {
        return listCategory;
    }

    public void setListCategory(String listCategory) {
        this.listCategory = listCategory;
    }

    static final String TASK_FRAGMENT_TAG = "task";
    static final int TASK_FRAGMENT = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(this.getClass().toString(), ">>>>>> Inside onCreateView");
        rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        // LoadMore button
        final Button btnLoadMore = new Button(getActivity());
        btnLoadMore.setText("Load More");
        ListView listView = (ListView) rootView.findViewById(R.id.txtSongs);
        Log.d(this.getClass().toString(), ">>>>>> Inside listView: " + listView);
        listView.addFooterView(btnLoadMore);
        fragmentManager = getFragmentManager();
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                offset=offset+20;
                salesRecordListFragment.loadListValues(offset, salesRecordListFragment.listCategory);
            }
        });
       loadListValues(0L, listCategory);
        registerClickCallback(listView);
        return rootView;
    }

    protected void loadListValues(Long values, String category){
        Log.d(this.getClass().toString(), ">>>>>> Loading List Values");
        SalesRecordListAsyncTask task = new SalesRecordListAsyncTask();
        MusicMartTaskFragment<Map<String, Object>, List<SalesRecord>> salesRecordMusicMartTaskFragment = (MusicMartTaskFragment<Map<String, Object>, List<SalesRecord>>) fragmentManager.findFragmentByTag(TASK_FRAGMENT_TAG);
        if (null == salesRecordMusicMartTaskFragment){
            salesRecordMusicMartTaskFragment =
                    new MusicMartTaskFragment<Map<String, Object>, List<SalesRecord>>(salesRecordListFragment, task, salesRecordListFragment.getId());
        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put(SalesRecordListAsyncTask.OFFSET, values);
        parameterMap.put(SalesRecordListAsyncTask.CATEGORY, category);


        salesRecordMusicMartTaskFragment.setValues(new Map[]{parameterMap});
        Log.d(this.getClass().toString(), ">>>>>> Showing salesRecordMusicMartTaskFragment");
        salesRecordMusicMartTaskFragment.show(fragmentManager, TASK_FRAGMENT_TAG);
    }

    public void callBack(List<SalesRecord> result) {
        try{
            Log.d(this.getClass().toString(), ">>>>>>>>>>>>>>>>>> Inside callBack");
            salesRecordItems.addAll(result);
            ListView listView = (ListView) rootView.findViewById(R.id.txtSongs);
            int currentPosition = listView.getLastVisiblePosition();
            Log.d(this.getClass().toString(),">>>>>>>>>>>>>>>>>> currentPosition: " + currentPosition);
            SalesRecordAdapter salesRecordAdapter = new SalesRecordAdapter(getActivity(),
                        R.layout.item_view_grid, salesRecordItems, getMusicMartActivity().getMusicMartApplication().getTypeFace());
            listView.setAdapter(salesRecordAdapter);
            newPosition = currentPosition + increment;
            //newPosition=newPosition+20;
            listView.setSelectionFromTop(currentPosition,0);
        }
        catch (Exception e) {
            Log.d(this.getClass().toString(), "Error in Home Listener : " + e);
        }
    }
    public void registerClickCallback(ListView listView) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                SalesRecord salesRecord = salesRecordItems.get(position);
                String title =salesRecord.getTitle();
                Log.d(this.getClass().toString(),">>**title :" +title);
                String artist=salesRecord.getArtistId();
                Log.d(this.getClass().toString(),">>**artist :" +artist);
                String label=salesRecord.getLabel();
                Log.d(this.getClass().toString(),">>**label :" +label);
                Integer salesRecordId=salesRecord.getSalesRecordId();
                Log.d(this.getClass().toString(),">>**id :" +salesRecordId);


                Intent detailIntent = new Intent(getActivity().getApplicationContext(),SalesRecordDetailActivity.class);
                detailIntent.putExtra("title",title);
                detailIntent.putExtra("artist",artist);
                detailIntent.putExtra("label",label);
                detailIntent.putExtra("salesRecord", salesRecord);
               // detailIntent.putExtra("id",salesRecordId);
                startActivity(detailIntent);

            }
        });
    }

}
