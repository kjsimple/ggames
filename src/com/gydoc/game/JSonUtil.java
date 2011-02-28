package com.gydoc.game;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 */
public class JSonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    private JSonUtil() {
        
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

}
