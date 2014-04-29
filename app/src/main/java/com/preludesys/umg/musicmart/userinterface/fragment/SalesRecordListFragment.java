package com.preludesys.umg.musicmart.userinterface.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.adapter.SalesRecordAdapter;
import com.preludesys.umg.musicmart.listener.CallBackListener;
import com.preludesys.umg.musicmart.model.SalesRecord;
import com.preludesys.umg.musicmart.task.SalesRecordListAsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by varunsundaramoorthy on 4/28/14.
 */
public class SalesRecordListFragment extends MusicMartFragment implements CallBackListener<List<SalesRecord>> {
    final List<SalesRecord> salesRecordItems = new ArrayList<SalesRecord>();
    public long offset=0;
    int newPosition =0;
    int increment = 20;
    private View rootView;
    final SalesRecordListFragment salesRecordListFragment = this;
    FragmentManager fragmentManager;
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
                salesRecordListFragment.loadListValues(offset);
            }
        });
       loadListValues(0L);
       return rootView;
    }

    void loadListValues(Long values){
        Log.d(this.getClass().toString(), ">>>>>> Loading List Values");
        SalesRecordListAsyncTask task = new SalesRecordListAsyncTask();
        MusicMartTaskFragment<Long, List<SalesRecord>> salesRecordMusicMartTaskFragment = (MusicMartTaskFragment<Long, List<SalesRecord>>) fragmentManager.findFragmentByTag(TASK_FRAGMENT_TAG);
        if (null == salesRecordMusicMartTaskFragment){
            salesRecordMusicMartTaskFragment =
                    new MusicMartTaskFragment<Long, List<SalesRecord>>(salesRecordListFragment, task, salesRecordListFragment.getId());
        }
        salesRecordMusicMartTaskFragment.setValues(new Long[]{values});
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
                        R.layout.item_view, salesRecordItems, getMusicMartActivity().getMusicMartApplication().getTypeFace());
            listView.setAdapter(salesRecordAdapter);
            newPosition = currentPosition + increment;
            //newPosition=newPosition+20;
            listView.setSelectionFromTop(currentPosition,0);
        }
        catch (Exception e) {
            Log.d(this.getClass().toString(), "Error in Home Listener : " + e);
        }
    }


}
