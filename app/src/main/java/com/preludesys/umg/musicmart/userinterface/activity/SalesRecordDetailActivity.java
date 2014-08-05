package com.preludesys.umg.musicmart.userinterface.activity;

import android.app.ActionBar;
import android.support.v4.app.DialogFragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.adapter.SalesRecordDetailAdapter;
import com.preludesys.umg.musicmart.listener.CallBackListener;
import com.preludesys.umg.musicmart.model.SalesRecord;
import com.preludesys.umg.musicmart.model.SalesRecordDetail;
import com.preludesys.umg.musicmart.task.SalesRecordDetailAsyncTask;
import com.preludesys.umg.musicmart.urlimageviewhelper.UrlImageViewHelper;
import com.preludesys.umg.musicmart.userinterface.fragment.MusicMartTaskFragment;
import com.preludesys.umg.musicmart.userinterface.fragment.StoreDetailFragment;
import com.preludesys.umg.musicmart.util.FontHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varunsundaramoorthy on 5/1/14.
 */
public class SalesRecordDetailActivity extends MusicMartActivity implements CallBackListener<List<SalesRecordDetail>> {
    final SalesRecordDetailActivity salesRecordDetailActivity =this;
    SalesRecord salesRecord = new SalesRecord();
    final List<SalesRecordDetail> detailItems = new ArrayList<SalesRecordDetail>();
    FragmentManager fragmentManager;
    static final String TASK_FRAGMENT_TAG = "task";
    static final int TASK_FRAGMENT_ID = 111000111;
    final MediaPlayer mp = new MediaPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_record_detail);


        salesRecord=(SalesRecord) getIntent().getSerializableExtra("salesRecord");

        TextView songView = (TextView) findViewById(R.id.detail_Title);
        songView.setText(salesRecord.getTitle());
        songView.setTypeface(getMusicMartApplication().getTypeFace());
        TextView artistView =(TextView) findViewById(R.id.detail_ArtistId);
        artistView.setText(salesRecord.getArtistId());
        artistView.setTypeface(getMusicMartApplication().getTypeFace());
        TextView labelView =(TextView) findViewById(R.id.detail_label);
        labelView.setText(" Label : "+salesRecord.getLabel());
        labelView.setTypeface(getMusicMartApplication().getTypeFace());

        String previewUrl=salesRecord.getPreviewUrl();
        try {
            mediaPlayer(previewUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }


        FontHelper.sePaintFlags(songView);
        FontHelper.sePaintFlags(artistView);
        FontHelper.sePaintFlags(labelView);

        ImageView image =(ImageView) findViewById(R.id.detail_image);
        if(salesRecord.getImageUrl()==null){
            image.setImageResource(R.drawable.music2x);
        }
        else{

            String imagePath = salesRecord.getImageUrl();
            imagePath = (imagePath.contains("mzstatic.com") ? imagePath : imagePath.toLowerCase());
            Log.d(this.getClass().toString(), ">>>>> Image Path: " + imagePath);
            UrlImageViewHelper.setUrlDrawable(image, imagePath, null, UrlImageViewHelper.CACHE_DURATION_ONE_DAY);
        }

        actionBarSetup();
        fragmentManager = getSupportFragmentManager();

        loadDetailValues(salesRecord.getSalesRecordId());
    }

    protected void loadDetailValues(Integer salesRecordId){
        Log.d(this.getClass().toString(), ">>>>>> Loading List Values");
        SalesRecordDetailAsyncTask task = new SalesRecordDetailAsyncTask();
        MusicMartTaskFragment<Map<String, Object>, List<SalesRecordDetail>> salesRecordDetailMusicMartTaskFragment = (MusicMartTaskFragment<Map<String, Object>, List<SalesRecordDetail>>) fragmentManager.findFragmentByTag(TASK_FRAGMENT_TAG);
        if (null == salesRecordDetailMusicMartTaskFragment){
            salesRecordDetailMusicMartTaskFragment =
                    new MusicMartTaskFragment<Map<String, Object>, List<SalesRecordDetail>>(this, task, TASK_FRAGMENT_ID);
        }
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put(SalesRecordDetailAsyncTask.SALES_RECORD_ID, String.valueOf(salesRecordId));
        salesRecordDetailMusicMartTaskFragment.setValues(new Map[]{parameterMap});
        Log.d(this.getClass().toString(), ">>>>>> Showing salesRecordMusicMartTaskFragment");
        salesRecordDetailMusicMartTaskFragment.show(fragmentManager, TASK_FRAGMENT_TAG);
    }


    private void actionBarSetup() {

        // Update the action bar title with the TypefaceSpan instance
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Stores");
    }

    @Override
    public void callBack(List<SalesRecordDetail> result) {
        try{
            Log.d(this.getClass().toString(), ">>>>>>>>>>>>>>>>>> Inside callBackdetail");
            detailItems.addAll(result);
            ListView listView = (ListView) findViewById(R.id.detailSongs);
            List<SalesRecordDetail> partnerDetailItems = new ArrayList<SalesRecordDetail>();
            partnerDetailItems = detailItems.get(0).getPartnerSalesRecordDetail();
            detailItems.addAll(partnerDetailItems);
            SalesRecordDetailAdapter salesRecordDetailAdapter = new SalesRecordDetailAdapter(salesRecordDetailActivity, R.layout.detail_list_item, detailItems,getMusicMartApplication().getTypeFace());
            listView.setAdapter(salesRecordDetailAdapter);
            int currentPosition = listView.getLastVisiblePosition();
            SalesRecordDetail salesRecordDetail = detailItems.get(0);
            TextView bandView =(TextView) findViewById(R.id.detail_band);
            FontHelper.sePaintFlags(bandView);
            bandView.setText(" Band :  "+salesRecordDetail.getSuperLabel());
            bandView.setTypeface(getMusicMartApplication().getTypeFace());
            Log.d(this.getClass().toString(),">>>>>>>>>>>>>>>>>> currentPosition: " + currentPosition);
            registerClickCallback(listView);
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
                SalesRecordDetail salesRecordDetail = detailItems.get(position);
                showDialog(salesRecordDetail,detailItems);

            }
        });

    }

    public void mediaPlayer(String url) throws IOException {
        mp.setDataSource(url);
        mp.prepare();
        final ImageView play =(ImageView)findViewById(R.id.detail_play);
        final ImageView pause =(ImageView)findViewById(R.id.detail_pause);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                pause.setVisibility(View.VISIBLE);
                play.setVisibility(View.INVISIBLE);
                play.setEnabled(false);
                pause.setEnabled(true);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                pause.setVisibility(View.INVISIBLE);
                play.setVisibility(View.VISIBLE);
                play.setEnabled(true);
                pause.setEnabled(false);
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                    pause.setVisibility(View.INVISIBLE);
                    play.setVisibility(View.VISIBLE);
                    play.setEnabled(true);
            }
        });
    }


    private void showDialog(SalesRecordDetail salesRecordDetail,List<SalesRecordDetail> detailItem) {
        DialogFragment newFragment = new StoreDetailFragment(salesRecordDetail,detailItem);
        newFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
