package com.example.aniket.testdemo.app;

/**
 * MainActivity.java .
 * @author Aniket Mane
 * @version 1.0
 */

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.aniket.testdemo.adapter.CustomListAdapter;
import com.example.aniket.testdemo.models.SearchResultModel;
import com.example.aniket.testdemo.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity  {



    private EditText edtSearch;
    private ProgressDialog pDialog;
    private ListView listData;
    private CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();

    }
    //get the actual views
    private void getViews() {
      //  btnSearch = (Button) findViewById(R.id.btnSearch);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
      //  btnSearch.setOnClickListener(this);
        listData = (ListView) findViewById(R.id.lv_Data);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length()>2) {
                    processRequest(s.toString());
                }
                else {
                    listData.setAdapter(null);

                    if (s.toString().trim().length()==0)
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.empty_warning_message), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    /**
     * @param searchString -Search text Enter in edit text
     * @return void
     * Get the data for requested search text
     * */
    private void processRequest(String searchString)

    {
        Log.e("URL", "" + Constants.getInstance().genrateURL(searchString));
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, Constants.getInstance().genrateURL(searchString), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (pDialog != null) {
                                pDialog.dismiss();
                            }
                            if (response!=null) {
                                if (response.has("query")) {
                                    String responseQuery = response.getJSONObject("query").toString();
                                    ArrayList<SearchResultModel> data = parseData(response);
                                    adapter = new CustomListAdapter(MainActivity.this, data);
                                    listData.setAdapter(adapter);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (pDialog != null) {
                            pDialog.dismiss();
                        }
                        Log.e("Volley", "Error");

                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jor);

    }


    /**
     * @param objData -JSON Object
     * @return ArrayList<SearchResultModel>
     * Parse the JSON Object returned from API
     * */
    private ArrayList<SearchResultModel> parseData(JSONObject objData)

    {
        ArrayList<SearchResultModel> data = null;
        try {
            if (objData != null) {
                data = new ArrayList<SearchResultModel>();
                JSONObject objContinue = null;

                if (objData.has("continue")) {
                    objContinue = objData.getJSONObject("continue");
                }
                JSONObject objQuery = null;
                if (objData.has("query")) {
                    objQuery = objData.getJSONObject("query");
                }
                int offset = 0;
                if (objContinue!=null) {
                    if (objContinue.has("query")) {

                        offset = objContinue.getInt("gpsoffset");
                    }
                }
                JSONObject pages = null;
                if(objQuery!=null) {
                    if (objQuery.has("pages")) {
                        pages = objQuery.getJSONObject("pages");
                    }
                }
                if (pages != null ) {
                    Iterator<String> keys = pages.keys();
                    while (keys.hasNext()) {
                        SearchResultModel model = new SearchResultModel();
                        String key = keys.next();
                        JSONObject innerJObject = pages.getJSONObject(key);
                        if (innerJObject.has("pageid")) {
                            model.setPageId(Long.parseLong(innerJObject.getString("pageid")));

                        }
                        if (innerJObject.has("title")) {
                            model.setTitle(innerJObject.getString("title"));

                        }
                        if (innerJObject.has("index")) {
                            model.setIndex(Integer.parseInt(innerJObject.getString("index")));

                        }
                        if (innerJObject.has("thumbnail")) {
                            JSONObject objectThumbnail = innerJObject.getJSONObject("thumbnail");
                            if (objectThumbnail.has("source")) {
                                model.getImageModel().setUrlSource(objectThumbnail.getString("source"));
                            }
                            if (objectThumbnail.has("width")) {
                                model.getImageModel().setWidth(Integer.parseInt(objectThumbnail.getString("width")));
                            }
                            if (objectThumbnail.has("height")) {
                                model.getImageModel().setHeight(Integer.parseInt(objectThumbnail.getString("height")));
                            }

                        }
                        data.add(model);
                    }

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return data;
    }


}
