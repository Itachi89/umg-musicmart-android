package com.preludesys.umg.musicmart.userinterface;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.app.httpJson;
import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.SalesRecord;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    public static List<SalesRecord> mySongs= new ArrayList<SalesRecord>();
    httpJson json = new httpJson();
    private String url = "http://jay-preludesys-mac.local:8080/umg-musicmart-web-services/rest/salesrecord/1?deviceId=B15391CD13774F7D843FCCA3D230CF36FFFFFFFF";


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObjectMapper mapper = new ObjectMapper();
        //InputStream jsonFileInputStream = getResources().openRawResource(R.raw.salesrecordlist);
        String sample= json.getValueResult(url);
        InputStream jsonFileInputStream = new ByteArrayInputStream(sample.getBytes());


        try {


           mySongs = mapper.readValue(jsonFileInputStream, new TypeReference<ArrayList<SalesRecord>>(){});
           // Log.i(this.getClass().toString(), ">>>>>>>>> Array Length" + mySongs.size());
           // Log.i(this.getClass().toString(), ">>>>>>>>> Array First Record" + mySongs.get(0).getTitle());

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        populateListView();
        registerClickCallback();

    }
    private void populateListView() {
        ArrayAdapter<SalesRecord> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.txtSongs);
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.txtSongs);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked,
                                    int position, long id) {

                SalesRecord clickedCar = mySongs.get(position);
                String message = "You clicked position " + position
                        + " Which is Song " + clickedCar.getTitle();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<SalesRecord> {
        public MyListAdapter() {
            super(MainActivity.this, R.layout.item_view, mySongs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            SalesRecord currentSong = mySongs.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_icon);
            imageView.setImageResource(R.drawable.bug);

            TextView titleText = (TextView) itemView.findViewById(R.id.item_Title);
            titleText.setText(currentSong.getTitle());

            TextView artistText = (TextView) itemView.findViewById(R.id.item_Artistid);
            artistText.setText("" + currentSong.getArtistId());

            TextView lwtdText = (TextView) itemView.findViewById(R.id.item_lwtd);
            lwtdText.setText(currentSong.getLwtd());

            TextView wtdText = (TextView) itemView.findViewById(R.id.item_wtd);
            wtdText.setText(currentSong.getWtd());

            TextView rtdText = (TextView) itemView.findViewById(R.id.item_rtd);
            rtdText.setText(currentSong.getRtd());

            return itemView;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
