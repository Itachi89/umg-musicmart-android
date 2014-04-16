package com.preludesys.umg.musicmart.userinterface;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.preludesys.anb.adapter.AnBItemAdapter;
import com.preludesys.anb.listener.PostTaskExecuteListener;
import com.preludesys.anb.model.Item;
import com.preludesys.anb.model.Module;
import com.preludesys.anb.task.AnBItemListTask;
import com.preludesys.anb.task.AnBSearchTask;

import java.util.List;

public class ListScreen extends MusicMartActivity {
	EditText txt_search;
	Button t_s;
    final ListScreen listScreen = this;
	String[] temp_var1;
	String title;
	String searchString;
	public Module module;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_list_screen);
		AnBItemListTask itemlListTask = new AnBItemListTask(getAnBApplication());
		itemlListTask.setTaskProgressListener(getTaskProgressListener());
		itemlListTask.setPostTaskExecuteListener(new PostTaskExecuteListener<List<Item>>() {
			public void performOperation(List<Item> items) {
				try{
					AnBItemAdapter itemAdapter = new AnBItemAdapter(listScreen, R.layout.item_list_row, items);
					ListView listView = (ListView) findViewById(R.id.listview);
					listView.setAdapter(itemAdapter);
					listView.setOnItemClickListener(getItemOnClickListener(listView));
				} 
				catch (Exception e) {
					System.out.println("Error in Home Listener : " + e);
				}
			}
		});
		

		t_s = (Button) findViewById(R.id.button1);
		t_s.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("!!!!!!!!!!!!@@@@@@@@@##########$$$$$$$$$%%%%%%%%%%");
				
				txt_search =(EditText) findViewById(R.id.search);
				searchString=txt_search.getText().toString();
				System.out.println("::::::::::::::::::search string::::::::"	+ searchString);
				
//				Intent intent = new Intent(ListScreen.this,	SearchScreen.class);
//				intent.putExtra("SearchString", searchString);
//				startActivity(intent);
				
				boolean isweek=false;
				

				if(!searchString.equalsIgnoreCase(""))
				{
				
					try
					{
					
					 if((searchString.charAt(0)+"").equalsIgnoreCase("w"))
					 {
						 if(Integer.parseInt(searchString.charAt(1)+"")>0)
						 {
							 isweek=true;
						  }
						 else
						 {
							 isweek=false;
						 }
					 }
					 
					}
					catch(Exception e)
					{
						e.printStackTrace();
						isweek=false;
					}
				}
				else
				{
					Search("@","@");
				}
				
				
					
				if(isweek==true)
				{
					Search(searchString,"@");
				}
				else
				{
					Search("@",searchString);
				}
				
				
			}
		});
		
		
		module=(Module) getIntent().getSerializableExtra("modules");
		System.out.println("bundle data module(listscreen):::::::"+module);
		itemlListTask.execute(module);
		
		
//		Module module = (Module)  getIntent().getExtras().get("module");
//		itemlListTask.execute(module);
		
	}

	protected AdapterView.OnItemClickListener getItemOnClickListener(
			final ListView listView) {
		return new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, 	int position, long arg3) {
				Item item = (Item) listView.getItemAtPosition(position);
				System.out.println(">>>>>>>>>>>>> Testing Onclick Listener: " + item.getItemId());
				Intent intent = new Intent(ListScreen.this, ListDetailScreen.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("item", item);
				bundle.putSerializable("modules",module);
				intent.putExtras(bundle);
				intent.putExtra("Item", item);
				startActivity(intent);
			}
		};
	}
	
	
	public void Search(String service,String title)
	{
		System.out.println("^^^^^^^^^^********Inside search**********^^^^^^^^^^^^");
		AnBSearchTask itemlListTask = new AnBSearchTask(getAnBApplication());
		itemlListTask.setTaskProgressListener(getTaskProgressListener());
		itemlListTask.setPostTaskExecuteListener(new PostTaskExecuteListener<List<Item>>() {
			public void performOperation(List<Item> items) {
				try{
					
					System.out.println("Items >"+items.size());
					
					AnBItemAdapter itemAdapter = new AnBItemAdapter(listScreen, R.layout.item_list_row, items);
					ListView listView = (ListView) findViewById(R.id.listview);
					listView.setAdapter(itemAdapter);
					listView.setOnItemClickListener(getItemOnClickListener(listView));
					
				} 
				catch (Exception e) {
					System.out.println("Error in Home Listener : " + e);
				}
			}
		});
		
		itemlListTask.execute(service,title);
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
		startActivity(new Intent(ListScreen.this, HomeScreen.class));

	}

	private void email() {
		startActivity(new Intent(ListScreen.this, SendEmail.class));
	}
	
	@Override
	public void onBackPressed() {
	
	}
}