package com.preludesys.umg.musicmart.userinterface.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.SalesRecordDetail;

/**
 * Created by varunsundaramoorthy on 5/7/14.
 */
public class StoreDetailActivity extends Activity {
    SalesRecordDetail salesRecordDetail = new SalesRecordDetail();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_details_popup);
        salesRecordDetail=(SalesRecordDetail) getIntent().getSerializableExtra("salesRecordDetail");
        TextView storeDetails = (TextView) findViewById(R.id.detail_Stores);
        // storeDetails.setTypeface(musicMartActivity.getMusicMartApplication().getTypeFace());
        TextView itemItunes = (TextView) findViewById(R.id.item_iTunes);
        //itemItunes.setTypeface(musicMartActivity.getMusicMartApplication().getTypeFace());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
