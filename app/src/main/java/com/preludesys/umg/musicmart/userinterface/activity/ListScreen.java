package com.preludesys.umg.musicmart.userinterface.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.adapter.SalesRecordAdapter;
import com.preludesys.umg.musicmart.listener.PostTaskExecuteListener;
import com.preludesys.umg.musicmart.model.SalesRecord;

import java.util.ArrayList;
import java.util.List;

public class ListScreen extends MusicMartActivity {
    final ListScreen listScreen = this;
    final List<SalesRecord> salesRecordItems = new ArrayList<SalesRecord>();
    public long offset=0;
    int newPosition =0;
    int increment = 20;

    //String deviceId =Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_songs);
     }


    @Override
    public PostTaskExecuteListener getPostTaskExecutionListener() {
        return new PostTaskExecuteListener<List<SalesRecord>>() {
            public void performOperation(List<SalesRecord> items) {
                try{
                    Log.d(this.getClass().toString(),">>>>>>>>>>>>>>>>>> Inside performOperation");
                    salesRecordItems.addAll(items);
                    ListView listView = (ListView) findViewById(R.id.txtSongs);
                    int currentPosition = listView.getLastVisiblePosition();
                    Log.d(this.getClass().toString(),">>>>>>>>>>>>>>>>>> currentPosition: " + currentPosition);
                    SalesRecordAdapter salesRecordAdapter = new SalesRecordAdapter(listScreen, R.layout.item_view, salesRecordItems, getMusicMartApplication().getTypeFace());
                    listView.setAdapter(salesRecordAdapter);
                    newPosition = currentPosition + increment;
                    //newPosition=newPosition+20;
                    listView.setSelectionFromTop(currentPosition,0);
                }
                catch (Exception e) {
                    Log.d(this.getClass().toString(), "Error in Home Listener : " + e);
                }
            }
        };
    }


   // }



    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu., menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem SalesRecord) {

		switch (SalesRecord.getItemId()) {

		case R.id.home:
			//back();
			return true;


		default:
			return super.onOptionsItemSelected(SalesRecord);
		}
	}


	@Override
	public void onBackPressed() {
	
	}
}