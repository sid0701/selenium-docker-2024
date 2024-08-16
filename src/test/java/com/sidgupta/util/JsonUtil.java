package com.sidgupta.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sidgupta.tests.vendorapplication.model.VendorAppTestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getData(String path, Class<T> type){

        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, type);
        }
        catch(Exception e){
            log.error("Cannot read the file at {}",path, e);
        }
        return null;
    }


}
