package com.preludesys.umg.musicmart.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.preludesys.logic.Restlogic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SearchScreen extends Activity {
	EditText txt_search;
	Button t_s;

	ListView listViewObject;
	String item_id;
	String title;
	String searchString;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_list_screen);

		// URL to the JSON data
		// String stringUrl =
		// "http://wptrafficanalyzer.in/p/demo1/first.php/itemList";

		// String stringUrl
		// ="http://54.235.202.16:8080/NBC_WebServices/rest/todos/getItemList?service=TK&userid=502086792";

		// String Vservice = getIntent().getStringExtra("Service");
		// SharedPreferences pref =
		// getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
		// String username = pref.getString(PREF_USERNAME, null);
		// System.out.println("username::::::::::::"+username);
		// String Userid = getIntent().getStringExtra("UID");
		String searchstr = getIntent().getStringExtra("SearchString");
		System.out.println(":::::::::SEARCHSTRING::::::" + searchstr);
		String Vservice = "TK";
		String Userid = "502086792";
		String stringUrl = Restlogic.search(Vservice, Userid, searchstr);

		// Creating a new non-ui thread task to download json data
		DownloadTask downloadTask = new DownloadTask();

		// Starting the download process
		downloadTask.execute(stringUrl);

		// Getting a reference to ListView of activity_main
		listViewObject = (ListView) findViewById(R.id.listview);

	}

	/** A method to download json data from url */
	private String downloadUrl(String stringUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		try {
			URL url = new URL(stringUrl);

			// Creating an http connection to communicate with url
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("Accept", "application/json");
			// Connecting to url
			urlConnection.connect();

			// Reading data from url
			iStream = urlConnection.getInputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					iStream));

			StringBuffer sb = new StringBuffer();

			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			data = sb.toString();

			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
		}

		return data;
	}

	/** AsyncTask to download json data */
	private class DownloadTask extends AsyncTask<String, Integer, String> {
		String data = null;

		@Override
		protected String doInBackground(String... url) {
			try {
				data = downloadUrl(url[0]);

			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {

			JSONObject jObject;
			try {
				jObject = new JSONObject(result);

				System.out.println("strjson(result)::::::::" + result);
				System.out.println("jobj::::::" + jObject);

				System.out.println(result);

				ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();

				// Start parsing xml data
				listViewLoaderTask.execute(result);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/** AsyncTask to parse json data and load ListView */
	private class ListViewLoaderTask extends
			AsyncTask<String, Void, SimpleAdapter> {
		JSONObject jObject;
		List<HashMap<String, Object>> itemList = null;

		// Doing the parsing of xml data in a non-ui thread
		@Override
		protected SimpleAdapter doInBackground(String... strJson) {

			try {
				jObject = new JSONObject(strJson[0]);
				System.out.println("strjson::::::::" + strJson[0]);
				System.out.println("jobj::::::" + jObject);

				System.out.println(strJson[0]);

				/* New code for Testing */
				Iterator<?> rootIter = jObject.keys();
				while (rootIter.hasNext()) {
					String name = (String) rootIter.next();
					Object obj = jObject.get(name);
					if (obj instanceof JSONObject) {
						JSONObject jsonObj = (JSONObject) obj;
						System.out.println("jobject");
						// check jsonObj.isEmpty();
						JSONParser jpo = new JSONParser();
						try {
							// Getting the parsed data as a List construct
							//itemList = jpo.jsonObjectparsing(jsonObj);
						} catch (Exception e) {
							Log.d("Exception", e.toString());
						}

					} else if (obj instanceof JSONArray) {
						JSONArray jsonArray = (JSONArray) obj;
						System.out.println("jarray");
						// check jsonArray.size();

						System.out
								.println("::::::::inside else array(search):::::::::::");
						JSONParser jsonParserObject = new JSONParser();
						jsonParserObject.parse(jObject);
						try {
							// Getting the parsed data as a List construct
							itemList = jsonParserObject.parse(jObject);
						} catch (Exception e) {
							Log.d("Exception", e.toString());
						}
					} else {
						// check value obj.toString().equals("");
					}
				}

			} catch (Exception e) {
				Log.d("JSON Exception1", e.toString());
			}

			// Keys used in Hashmap
			String[] from = { "highContent", "empImageUrl", 
					"subContent", "subTitle", "title" };

			// Ids of views in listview_layout
			int[] to = { R.id.highContent, R.id.itemImageUrl, 
					R.id.subContent, R.id.subTitle, R.id.title };

			// Instantiating an adapter to store each items
			// R.layout.listview_layout defines the layout of each item
			SimpleAdapter adapter = new SimpleAdapter(getBaseContext(),
					itemList, R.layout.item_list_row, from, to);
			
			OnItemClickListener itemClickListener = new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View container,
						int position, long id) {
					System.out
							.println(":::::::::Inside on item click:::::::::");
					new AsyncTaskOperation_List().execute("");
				}
			};

			listViewObject.setOnItemClickListener(itemClickListener);
			return adapter;

		}

		/** Invoked by the Android on "doInBackground" is executed */
		@Override
		protected void onPostExecute(SimpleAdapter adapter) {

			// Setting adapter for the listview
			listViewObject.setAdapter(adapter);

			for (int i = 0; i < adapter.getCount(); i++) {
				HashMap<String, Object> imageObject = (HashMap<String, Object>) adapter
						.getItem(i);
				String imgUrl = (String) imageObject.get("empImageUrl_path");
				ImageLoaderTask imageLoaderTask = new ImageLoaderTask();

				HashMap<String, Object> hmDownload = new HashMap<String, Object>();
				imageObject.put("empImageUrl_path", imgUrl);
				imageObject.put("position", i);

				// Starting ImageLoaderTask to download and populate image in
				// the listview
				imageLoaderTask.execute(imageObject);
			}

			/*
			 * txt_search=(EditText) findViewById(R.id.search);
			 * txt_search.addTextChangedListener(new TextWatcher(){
			 * 
			 * @Override public void onTextChanged(CharSequence cs, int arg1,
			 * int arg2, int arg3) { // When user changed the Text
			 * //ListScreen.this.adapter.getFilter().filter(cs);
			 * searchString=cs.toString();
			 * 
			 * }
			 * 
			 * @Override public void beforeTextChanged(CharSequence arg0, int
			 * arg1, int arg2, int arg3) { // TODO Auto-generated method stub
			 * 
			 * }
			 * 
			 * @Override public void afterTextChanged(Editable arg0) { // TODO
			 * Auto-generated method stub }
			 * 
			 * 
			 * });
			 * 
			 * t_s=(Button) findViewById(R.id.button1);
			 * t_s.setOnClickListener(new View.OnClickListener() {
			 * 
			 * public void onClick(View v) {
			 * System.out.println("::::::::::::::::::search string::::::::"
			 * +searchString);
			 * System.out.println(":::::::::Inside post exe(search):::::::::");
			 * Intent intent = new Intent(ListScreen.this, SearchScreen.class);
			 * 
			 * intent.putExtra("SearchString", searchString);
			 * 
			 * 
			 * startActivity(intent); } });
			 */
			/*
			 * Restlogic res=new Restlogic(); String aaa=res.search("TK",
			 * "502086792", s);
			 */

		}
	}

	/** AsyncTask to download and load an image in ListView */
	private class ImageLoaderTask extends
			AsyncTask<HashMap<String, Object>, Void, HashMap<String, Object>> {

		@Override
		protected HashMap<String, Object> doInBackground(
				HashMap<String, Object>... hm) {

			System.out
					.println("Do In Background*******************************");

			InputStream iStream = null;
			String imgUrl = (String) hm[0].get("empImageUrl_path");
			int position = (Integer) hm[0].get("position");

			System.out.println("positon....." + position);

			URL url;
			try {
				url = new URL(imgUrl);

				// Creating an http connection to communicate with url
				HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();

				// Connecting to url
				urlConnection.connect();

				// Reading data from url
				iStream = urlConnection.getInputStream();

				// Getting Caching directory
				File cacheDirectory = getBaseContext().getCacheDir();

				// Temporary file to store the downloaded image
				File tmpFile = new File(cacheDirectory.getPath() + "/wpta_"
						+ position + ".png");

				// The FileOutputStream to the temporary file
				FileOutputStream fOutStream = new FileOutputStream(tmpFile);

				// Creating a bitmap from the downloaded inputstream
				Bitmap b = BitmapFactory.decodeStream(iStream);

				// Writing the bitmap to the temporary file as png file
				b.compress(Bitmap.CompressFormat.PNG, 100, fOutStream);

				// Flush the FileOutputStream
				fOutStream.flush();

				// Close the FileOutputStream
				fOutStream.close();

				// Create a hashmap object to store image path and its position
				// in the listview
				HashMap<String, Object> hmBitmap = new HashMap<String, Object>();

				// Storing the path to the temporary image file
				hmBitmap.put("empImageUrl", tmpFile.getPath());

				// Storing the position of the image in the listview
				hmBitmap.put("position", position);

				// Returning the HashMap object containing the image path and
				// position
				return hmBitmap;

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(HashMap<String, Object> result) {
			try {

				System.out
						.println("On post execute*******************************");

				// Getting the path to the downloaded image
				String path = (String) result.get("empImageUrl");

				// Getting the position of the downloaded image
				int position = (Integer) result.get("position");

				Log.e("abdul", "path" + path + "position" + position + "");

				// Getting adapter of the listview
				SimpleAdapter adapter = (SimpleAdapter) listViewObject
						.getAdapter();

				// Getting the hashmap object at the specified position of the
				// listview
				HashMap<String, Object> hm = (HashMap<String, Object>) adapter
						.getItem(position);

				// Overwriting the existing path in the adapter
				hm.put("empImageUrl", path);

				// Noticing listview about the dataset changes
				adapter.notifyDataSetChanged();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class AsyncTaskOperation_List extends
			AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPreExecute() {
			System.out.println(":::::::::Inside post exe:::::::::");
			Intent intent = new Intent(SearchScreen.this,
					ListDetailScreen.class);
			System.out.println(":::::::::temp_var1::::::::::" + item_id);
			System.out.println(":::::::::title::::::::::" + title);
			intent.putExtra("Mycontent", item_id);
			intent.putExtra("MyTitle", title);

			startActivity(intent);

		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}

		@Override
		protected void onPostExecute(String result) {

		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.listmenu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.home:
			back();
			return true;

		case R.id.email:
			email();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void back() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(SearchScreen.this, ListScreen.class);

		startActivity(intent);

	}

	private void email() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(SearchScreen.this, SendEmail.class);

		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		// Do Here what ever you want do on back press;
	}

}