package com.preludesys.umg.musicmart.userinterface.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.adapter.SalesRecordAdapter;
import com.preludesys.umg.musicmart.model.SalesRecord;
import com.preludesys.umg.musicmart.task.AsyncTaskCallBackListener;
import com.preludesys.umg.musicmart.task.SalesRecordListTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by varunsundaramoorthy on 4/28/14.
 */
public class SalesRecordListFragment extends MusicMartFragment<SalesRecord> implements AsyncTaskCallBackListener<List<SalesRecord>> {
    final List<SalesRecord> salesRecordItems = new ArrayList<SalesRecord>();
    public long offset=0;
    int newPosition =0;
    int increment = 20;
    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_songs, container, false);
        // LoadMore button
        final Button btnLoadMore = new Button(getActivity());
        btnLoadMore.setText("Load More");
        ListView listView = (ListView) rootView.findViewById(R.id.txtSongs);
        new MusicMartTaskFragment(this, new SalesRecordListTask() {
        });

        listView.addFooterView(btnLoadMore);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                offset=offset+20;
               // new SalesRecordListTask(getMusicMartActivity()).execute(offset);
            }
        });
        return rootView;
    }


    @Override
    public void onTaskComplete(List<SalesRecord> result) {
        try{
            Log.d(this.getClass().toString(), ">>>>>>>>>>>>>>>>>> Inside performOperation");
            salesRecordItems.addAll(result);
            ListView listView = (ListView) rootView.findViewById(R.id.txtSongs);
            int currentPosition = listView.getLastVisiblePosition();
            Log.d(this.getClass().toString(),">>>>>>>>>>>>>>>>>> currentPosition: " + currentPosition);
            SalesRecordAdapter salesRecordAdapter = new SalesRecordAdapter(getActivity(), R.layout.item_view, salesRecordItems, getMusicMartActivity().getMusicMartApplication().getTypeFace());
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
