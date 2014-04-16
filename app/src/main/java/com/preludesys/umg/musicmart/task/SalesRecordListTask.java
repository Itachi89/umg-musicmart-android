package com.preludesys.umg.musicmart.task;

//hi
import com.preludesys.umg.musicmart.MusicMartApplication;
import com.preludesys.umg.musicmart.model.SalesRecord;

import org.codehaus.jackson.map.Module;

import java.util.List;

public class SalesRecordListTask extends MusicMartAsyncTask<Module, Void, List<SalesRecord>> {

	public SalesRecordListTask(MusicMartApplication application) {
		super(application);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<SalesRecord> doInBackground(Module... moduleParameters) {
		System.out.println(">>>>>>Inside SalesRecordListTask - doInBackground");
		 Module module = moduleParameters[0];
		List<SalesRecord> itemList = null;
		/*try {
			itemList = getApplication().getTodDoWebService().getItemList(module);
			System.out.println(">>>>>>Inside SalesRecordListTask - Home fetch complete " + module.getName());
		} catch (AnBException e) {
			e.printStackTrace();
		}*/
		return itemList;
	}

}
