package com.preludesys.umg.musicmart.userinterface.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.SalesRecord;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends MusicMartActivity {
    private String CONTENT_VIEW_ID = "LIST_SCREEN";
    final ListActivity listScreen = this;
    final List<SalesRecord> salesRecordItems = new ArrayList<SalesRecord>();
    public long offset=0;
    int newPosition =0;
    int increment = 20;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().toString(), ">>>>>> Inside ListActivity onCreate");
        setContentView(R.layout.fragment_main);
        /*
        if (savedInstanceState == null) {
            Log.d(this.getClass().toString(), ">>>>>> Inside Fragment Setup");
            Fragment newFragment = new SalesRecordListFragment();
            Log.d(this.getClass().toString(), ">>>>>> Fragment Instantiated");
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(newFragment, CONTENT_VIEW_ID).commit();
            Log.d(this.getClass().toString(), ">>>>>> Fragment Commited");
        }
        */
     }


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