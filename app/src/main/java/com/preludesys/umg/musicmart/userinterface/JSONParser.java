package com.preludesys.umg.musicmart.userinterface;

import com.preludesys.anb.userinterface.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/** A class to parse json data */

public class JSONParser {
	
	// Receives a JSONObject and returns a list
	public List<HashMap<String,Object>> parse(JSONObject jObject){		
	
		JSONArray jsonArrayObject = null;
		try {		
			// Retrieves all the elements in the 'countries' array 
			jsonArrayObject = jObject.getJSONArray("itemList");
			
			System.out.println(":::jsonArrayObject"+jsonArrayObject);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		 // Invoking getJsonObject with the array of json object
		 // where each json object represent a country
		return getJsonArrayObject(jsonArrayObject);
	}
	
	
	private List<HashMap<String, Object>> getJsonArrayObject(JSONArray jsonArrayObject){
		int jsonObjectCount = jsonArrayObject.length();
		List<HashMap<String, Object>> itemList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> item = null;	

		// Taking each item, parses and adds to list object 
		for(int i=0; i<jsonObjectCount;i++){
			try {
				// Call getJsonObject with item JSON object to parse the item 
				item = getJsonObject((JSONObject)jsonArrayObject.get(i));
				itemList.add(item);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return itemList;
	}
	
	// Parsing the Country JSON object 
	private HashMap<String, Object> getJsonObject(JSONObject jsonObject){

		HashMap<String, Object> item = new HashMap<String, Object>();
		String highContent = "";
		String empImageUrl="";
		String itemId = "";
		String subContent = "";
		String subTitle = "";
		String title = "";		
		
		try {
			highContent = jsonObject.getString("highContent");
			empImageUrl = jsonObject.getString("empImageUrl");
			itemId = jsonObject.getString("itemId");
			subContent = jsonObject.getString("subContent");
			subTitle =jsonObject.getString("subTitle");
			title = jsonObject.getString("title");
			
			String details =" " + itemId + "    "+
                    "    " + subContent + 
                    "               " + title + "    " + subTitle;
			System.out.println(":::::::::::::JSONPARSER::::::::::"+details);
			
			item.put("highContent", highContent);
			item.put("empImageUrl", R.drawable.blank);
			item.put("empImageUrl_path", empImageUrl);
			System.out.println("empImageUrl_path*****"+empImageUrl);
			item.put("details", details);
		
			
		} catch (JSONException e) {			
			e.printStackTrace();
		}		
		return item;
	}
}