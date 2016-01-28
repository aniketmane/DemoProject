package com.example.aniket.testdemo.util;

/**
 * Constants.java .
 * @author Aniket Mane
 * @version 1.0
 */
public class Constants {

    private static String HOST_NAME="https://en.wikipedia.org/w/api.php?";
    private static String ACTION="action";
    private static String PROP="prop";
    private static String FORMAT="format";
    private static String PIPROP="piprop";
    private static String PITTHUMBSIZE="pithumbsize";
    private static String PILIMIT="pilimit";
    private static String GENRATOR="generator";
    private static String GPSEARCH="gpssearch";

    //create an object of Constants
    private static Constants instance = new Constants();

    //make the constructor private so that this class cannot be
    //instantiated
    private Constants(){}

    //Get the only object available
    public static Constants getInstance(){
        return instance;
    }

    /**
     * @param searchString -Search text Enter in edit text
     * @return String URL
     * Get the server URL
     * */
    public String genrateURL(String searchString)
    {
        return HOST_NAME+ACTION+"=query&"+PROP+"=pageimages&"+FORMAT+"=json&"+PIPROP+"=thumbnail&"+PITTHUMBSIZE+"=50&"+PILIMIT+"=50&"+GENRATOR+"=prefixsearch&"+GPSEARCH+"="+searchString;

    }



}
