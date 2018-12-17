package com.example.c1662348.updatepharmacyapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main Activity";
    Button update;
    Button additionalServices;
    CheckBox welsh;
    updatePharmacy UpdatePharmacy = new updatePharmacy(this);
    public static String item;

    public static Pharmacy pharmacy;

    private Selector selector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welsh = findViewById(R.id.Welsh);
        pharmacy = Login.pharmacies.get(0);
        welsh.setChecked(pharmacy.isWelshAvailable());
        multiSelect();

        try {
            setUpdate();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setUpdate() throws JSONException {
        update = findViewById(R.id.update_data);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkboxWelsh();
                UpdatePharmacy.sendJson(getBaseContext(), new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        Log.d(TAG,"Success");
                    }
                    @Override
                    public void onSuccess(String result) {
                        Log.d(TAG,"Success");
                    }
                });
            }
        });

    }

    public void checkboxWelsh(){
        welsh = findViewById(R.id.Welsh);
        pharmacy.setWelshAvailable(welsh.isChecked());
    }

    public void multiSelect(){
        additionalServices = findViewById(R.id.additional_services);

        additionalServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selector == null) {
                    selector = new Selector();
                    selector.setCallback(new UtilCallback() {
                        @Override
                        public void onSuccess(List<Service> services) {
                            pharmacy.setServices(services);
                        }
                    });
                }
                selector.show(getFragmentManager(), "Select enhanced services");
            }
        });
    }
}
