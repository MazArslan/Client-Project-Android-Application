package com.example.c1662348.updatepharmacyapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NetworkHandler {
    private static final String TAG = "Network handler";

    public static void getServices(final Activity activity, final VolleyCallback callback) {
        final Context context = activity.getApplicationContext();
        final RequestQueue queue = Volley.newRequestQueue(context);

        //Adapted from https://developer.android.com/training/volley/index.html
        String url = "https://lukewarlow.pythonanywhere.com/services";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Network request error!");
                        error.printStackTrace();
                    }
                });
        queue.add(request);
    }

    public static void getDataFromServer(final Context context, final VolleyCallback callback, final String pharmacyNumber) {
        //Adapted from https://developer.android.com/training/volley/index.html

        final RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://lukewarlow.pythonanywhere.com/findpharmacy";

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Login Error",1000).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("phoneNumber", pharmacyNumber);
                Log.d("Param Check", String.valueOf(params));
                return params;
            }
        };
        queue.add(stringRequest);
    }
}
