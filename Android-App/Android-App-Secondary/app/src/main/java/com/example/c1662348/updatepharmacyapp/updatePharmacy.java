package com.example.c1662348.updatepharmacyapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.c1662348.updatepharmacyapp.JsonParser;
import com.example.c1662348.updatepharmacyapp.Login;
import com.example.c1662348.updatepharmacyapp.MainActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by c1662348 on 28/04/2018.
 */

public class updatePharmacy {
    private static final String TAG = "Send Data";
    String url = "http://lukewarlow.pythonanywhere.com/updatepharmacy";
    Context context;

    public updatePharmacy(Context context) {
        this.context = context;
    }

    public void sendJson(final Context context, final VolleyCallback callback) {
        final RequestQueue queue = Volley.newRequestQueue(context);
        final Pharmacy pharmacy = MainActivity.pharmacy;
        final Context finalContext = context;
        try {
            StringRequest update = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("SuccessTest", response);
                    Intent intent = new Intent(context, Login.class);
                    Toast.makeText(finalContext, "Updated successfully", Toast.LENGTH_LONG).show();
                    context.startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("ErrorTest", error.toString());
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> params = Util.htmlEncode(pharmacy);
                    return params;
                }
            };
            queue.add(update);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}


