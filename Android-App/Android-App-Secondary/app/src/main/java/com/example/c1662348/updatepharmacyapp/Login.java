package com.example.c1662348.updatepharmacyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c1662348 on 20/04/2018.
 */

public class Login extends AppCompatActivity {
    //user inputs are checked on the server side.

    static List<Pharmacy> pharmacies;
    static List<Service> services; //List of all possible services, retrieved from server.
    public static String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        pharmacies = new ArrayList<>();
        services = new ArrayList<>();

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginChecker();
            }
        });
        NetworkHandler.getServices(this, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                services.addAll(JsonParser.parseServiceData(result));
            }

            @Override

            public void onSuccess(String result) {

            }
        });
    }

    public void loginChecker() {
        phoneNumber = ((EditText) findViewById(R.id.PharmNum)).getText().toString();

        NetworkHandler.getDataFromServer(getBaseContext(), new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) {
            }
            @Override
            public void onSuccess(String result) {
                Log.d("Login Check", "Success");
                try {
                    Log.d("Login Check", "Success");
                    Pharmacy pharmacy = JsonParser.parsePharmacyData(new JSONObject(result), services);
                    pharmacies.add(pharmacy);
                    successLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("Login Check", "error");
                }
            }
        }, phoneNumber);

    }

    public void successLogin(){
        Intent changePages = new Intent(this, MainActivity.class);
        Log.d("Changing","Success");
        startActivity(changePages);
    }

}
