package com.example.c1662348.updatepharmacyapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private static final String TAG = "JSON Parser";

    public static List<Service> parseServiceData(JSONObject json) throws IllegalArgumentException {
        List<Service> services = new ArrayList<>();
        if (json == null) {
            Log.d(TAG, "Json is null");
            throw new IllegalArgumentException();
        }
        try {
            for (int i = 0; i < json.length(); i++) {
                JSONObject serviceJSON = json.getJSONObject("service" + i);
                String name = serviceJSON.getString("name");
                Service service = new Service(name);
                services.add(service);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return services;
    }

    public static Pharmacy parsePharmacyData(JSONObject json, List<Service> services) throws IllegalArgumentException {
        if (json == null) {
            Log.d(TAG, "JSON is null");
            throw new IllegalArgumentException();
        }
        try {
            JSONObject pharmacyJson = json.getJSONObject("pharmacy0");
            String name = pharmacyJson.getString("name");
            String phoneNumber = pharmacyJson.getString("phoneNumber");
            Boolean welshAvailable = pharmacyJson.getBoolean("welshAvailable");

            Pharmacy pharmacy = new Pharmacy(name, phoneNumber, welshAvailable);
            String encodedServiceString = pharmacyJson.getString("services");
            String[] encodedServices = encodedServiceString.split(",");
            for (String stringService : encodedServices) {
                String serviceName = stringService.replaceAll("_", " ").toLowerCase();
                for (Service service : services) {
                    if (service.equals(serviceName)) {
                        pharmacy.addService(service);
                        break;
                    }
                }
            }
            return pharmacy;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
