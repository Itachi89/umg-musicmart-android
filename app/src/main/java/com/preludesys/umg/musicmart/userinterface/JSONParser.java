package com.preludesys.umg.musicmart.userinterface;

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

/** A class to parse json data */

public class JSONParser {
	String Json;
    List<SalesRecord> myList= new ArrayList<SalesRecord>();

    public List<SalesRecord> JSONMapper(ArrayList<SalesRecord> myList, String Json){
        this.Json=Json;
        this.myList= myList;
        ObjectMapper mapper = new ObjectMapper();
        InputStream jsonFileInputStream = new ByteArrayInputStream(Json.getBytes());

        try {
            // Retrieves all the elements in the 'countries' array
            myList = mapper.readValue(jsonFileInputStream, new TypeReference<ArrayList<SalesRecord>>(){});

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