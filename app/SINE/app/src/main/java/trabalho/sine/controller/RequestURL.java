package trabalho.sine.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import trabalho.sine.interfaces.VolleyCallback;

public class RequestURL<E> {
    private final Class<E> type;
    private final Context context;
    private List<E> eList;

    public RequestURL(Class<E> type, Context context) {
        this.type = type;
        this.context = context;
    }

    public List<E> requestData(String url){
        request(url, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                Type eListTyle = new TypeToken<ArrayList<E>>(){}.getType();
                eList = gson.fromJson(response, eListTyle);
            }
        });
        return eList;
    }

    //Efetua a requisição dos dados através da url recebida pelo usuário.
    private void request(String url, final VolleyCallback callback) {

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error Request: ", error.getMessage());
            }
        });
        queue.add(stringRequest);
    }//doInBackground()
}//class RequestURL
