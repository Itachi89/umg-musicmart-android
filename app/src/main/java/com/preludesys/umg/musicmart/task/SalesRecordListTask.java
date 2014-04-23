package com.preludesys.umg.musicmart.task;

//hi

import android.util.Base64;
import android.util.Log;

import com.preludesys.umg.musicmart.MusicMartApplication;
import com.preludesys.umg.musicmart.model.SalesRecord;
import com.preludesys.umg.musicmart.util.JSONParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SalesRecordListTask extends MusicMartAsyncTask<String, Void, List<SalesRecord>> {

	public SalesRecordListTask(MusicMartApplication application) {
		super(application);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<SalesRecord> doInBackground(String... parameters) {
		System.out.println(">>>>>>Inside SalesRecordListTask - doInBackground");
		List<SalesRecord> itemList = null;
        String username = "restclient";
        String password = "D3Rfg$gbmi^1Ydv3f*B";
        String unp = username+":"+password;
        String result = new String();
        //String deviceId =parameters[0];

        String url= "http://192.168.1.14:8080/umg-musicmart-web-services/rest/salesrecord/search?offset=0&limit=20&searchTerm=*&deviceId=5D26E4C6915A4301A08369348698A620FFFFFFFF";
        Log.d("urls", "url: " + url);



        InputStream is = null;
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(url);
            String encoded_login = Base64.encodeToString(unp.getBytes(), Base64.NO_WRAP);
            httpget.setHeader(new BasicHeader("Authorization", "Basic "+encoded_login));

            Log.d("URL","URL="+httpget.toString());

            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            sb.append(reader.readLine() + "\n");

            String line = "0";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.d(this.getClass().toString(), ">>>>>>  SalesRecord JSON " + result);
        } catch (Exception e) {
            Log.e(this.getClass().toString(), ">>>>>> Error in doBackground " + e.toString());
        }
        JSONParser<SalesRecord> parser = new JSONParser<SalesRecord>();

        return parser.parse(result);
	}

}
