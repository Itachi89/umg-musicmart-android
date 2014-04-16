package com.preludesys.umg.musicmart.userinterface;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.preludesys.anb.adapter.AnBItemDetailAdapter;
import com.preludesys.anb.listener.PostTaskExecuteListener;
import com.preludesys.anb.model.Item;
import com.preludesys.anb.model.ItemDetail;
import com.preludesys.anb.model.Module;
import com.preludesys.anb.task.AnBItemDetailListTask;
import com.preludesys.anb.task.AnBUpdateTask;
import com.preludesys.anb.userinterface.R;
import com.preludesys.anb.util.AnBUpdate;

import java.util.List;

public class ListDetailScreen extends MusicMartActivity {

	TextView title,subcontent;
	TextView type,projcode,payterm,sdate,value;
	public String version ="TK";
	public String user ="502086792";
	public long itemid;
	final ListDetailScreen listDetailScreen = this;
	Item item=new Item();
	Module module=new Module();
	Integer approve_status=1;
	Integer reject_status=2;
	String aaa;
	
	public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);     
	      setContentView(R.layout.item_list_detail_screen);
	      AnBItemDetailListTask itemlDetailListTask = new AnBItemDetailListTask(getAnBApplication());
	      itemlDetailListTask.setTaskProgressListener(getTaskProgressListener());
	      itemlDetailListTask.setPostTaskExecuteListener(new PostTaskExecuteListener<List<ItemDetail>>() {
			public void performOperation(List<ItemDetail> itemDetail) {
				try{
					AnBItemDetailAdapter itemDetailAdapter = new AnBItemDetailAdapter(listDetailScreen, R.layout.item_detail_list_view, itemDetail);
					ListView listView = (ListView) findViewById(R.id.listdetailview);
					listView.setAdapter(itemDetailAdapter);
					} 
				catch (Exception e) {
					System.out.println("Error in Home Listener : " + e);
				}
				
			}
		});
	      module=(Module) getIntent().getSerializableExtra("modules");
			System.out.println("bundle data module(listdetailscreen):::::::"+module);
	       item = (Item)  getIntent().getExtras().get("Item");
	      itemlDetailListTask.execute(item);
	      
	}

    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.detailmenu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.approve:
			approve();
			return true;
			
		case R.id.reject:
			reject();
			return true;
		
		case R.id.back:
			back();
			return true;
		
		
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void back() {
		Intent intent = new Intent(ListDetailScreen.this, ListScreen.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("modules",module);
		intent.putExtras(bundle);
		startActivity(intent);
		
	}

	

	public void approve() {
		
		Builder builder = new Builder(this);
		builder.setMessage("Approve");
	    builder.setCancelable(true);
	    builder.setPositiveButton("I Agree", new OkOnClickListener());
	    builder.setNegativeButton("Cancel", new CancelOnClickListener());

	      AlertDialog dialog = builder.create();
	      dialog.show();

	}
	public void reject() {

		Builder alert = new Builder(this);
		alert.setMessage("Reason for Rejection ");
		final EditText input = new EditText(this);
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  Editable value = input.getText();
		  String rejectComments  =value.toString();
		  System.out.println("Comment for Rejection:::::::"+rejectComments);
		  AnBUpdateTask anbUpdateTask = new AnBUpdateTask(getAnBApplication());
		  AnBUpdate anbUpdate= new AnBUpdate(reject_status,rejectComments.replaceAll(" ", "_"),item);
		  anbUpdateTask.setTaskProgressListener(getTaskProgressListener());
		  anbUpdateTask.setPostTaskExecuteListener(new PostTaskExecuteListener<String>() {
				public void performOperation(String a) {
					
				}
			});
		  anbUpdateTask.execute(anbUpdate);
		  Intent intent = new Intent(ListDetailScreen.this, ListScreen.class);
		  Bundle bundle = new Bundle();
		  bundle.putSerializable("modules",module);
		  intent.putExtras(bundle);
		  startActivity(intent);
		 }
});
		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			 
		  }
		});
		alert.show();
		}
	
	
	

	@Override
	public void onBackPressed() {
	
	}
	
	// for approve
		
	private final class CancelOnClickListener implements
	  DialogInterface.OnClickListener {
	  public void onClick(DialogInterface dialog, int which) {
	 
	  }
	}

	private final class OkOnClickListener implements
	    DialogInterface.OnClickListener {
	  public void onClick(DialogInterface dialog, int which) {
		  String approveComments=null;
		  AnBUpdateTask anbUpdateTask = new AnBUpdateTask(getAnBApplication());
		  AnBUpdate anbUpdate= new AnBUpdate(approve_status,approveComments,item);
		  anbUpdateTask.setTaskProgressListener(getTaskProgressListener());
		  anbUpdateTask.setPostTaskExecuteListener(new PostTaskExecuteListener<String>() {
				public void performOperation(String a) {
					
				}
			});
		  anbUpdateTask.execute(anbUpdate);
		  Intent intent = new Intent(ListDetailScreen.this, ListScreen.class);
		  Bundle bundle = new Bundle();
		  bundle.putSerializable("modules",module);
		  intent.putExtras(bundle);
		  startActivity(intent);
	 
	  }

	
	} 
	
}
