package com.preludesys.umg.musicmart.util;

import android.util.Log;

import com.preludesys.umg.musicmart.model.SalesRecord;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** A class to parse json data */

public class JSONParser<T> {

   public List<SalesRecord> parse(String jsonValue){
       ObjectMapper mapper = new ObjectMapper();
       List<SalesRecord> myList = new ArrayList<SalesRecord>();
       try {
           TypeReference<List<SalesRecord>> typeRef = new TypeReference<List<SalesRecord>>(){};
           myList = mapper.readValue(jsonValue, typeRef);
           Log.d(this.getClass().toString(), ">>>>>>>>> JSONParser value  " + myList.get(0).getClass() );

        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return myList;

    }

}