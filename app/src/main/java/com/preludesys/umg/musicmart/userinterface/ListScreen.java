package com.preludesys.umg.musicmart.userinterface;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.adapter.SalesRecordAdapter;
import com.preludesys.umg.musicmart.listener.PostTaskExecuteListener;
import com.preludesys.umg.musicmart.model.SalesRecord;
import com.preludesys.umg.musicmart.task.LoadMoreListTask;

import java.util.ArrayList;
import java.util.List;

public class ListScreen extends MusicMartActivity {
	EditText txt_search;
	Button t_s;
    final ListScreen listScreen = this;
    final List<SalesRecord> salesRecordItems = new ArrayList<SalesRecord>();
    String[] temp_var1;
	String title;
	String searchString;
    public long offset=0;
    //String deviceId =Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // LoadMore button
        final Button btnLoadMore = new Button(this);
        btnLoadMore.setText("Load More");
        ListView listView = (ListView) findViewById(R.id.txtSongs);
        new LoadMoreListTask(listScreen).execute(offset);

        listView.addFooterView(btnLoadMore);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task

                offset=offset+20;
                new LoadMoreListTask(listScreen).execute(offset);
            }
        });
     }
@Override
    public PostTaskExecuteListener getPostTaskExecutionListener() {
        return new PostTaskExecuteListener<List<SalesRecord>>() {
            public void performOperation(List<SalesRecord> items) {
                try{
                    salesRecordItems.addAll(items);
                    Log.d(this.getClass().toString(),">>>>>>>>>>>>>>>>>> Inside performOperation");
                    SalesRecordAdapter salesRecordAdapter = new SalesRecordAdapter(listScreen, R.layout.item_view, salesRecordItems);
                    ListView listView = (ListView) findViewById(R.id.txtSongs);
                    listView.setAdapter(salesRecordAdapter);
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