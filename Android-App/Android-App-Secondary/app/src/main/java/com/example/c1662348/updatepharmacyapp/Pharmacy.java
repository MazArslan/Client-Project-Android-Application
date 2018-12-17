package com.example.c1662348.updatepharmacyapp;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.google.android.gms.maps.model.LatLng;
import com.example.c1662348.updatepharmacyapp.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Pharmacy implements Parcelable {
    private String name;
    private String phoneNumber;
    private boolean welshAvailable;
    private List<Service> services;

    public Pharmacy(String name, String phoneNumber, boolean welshAvailable) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.welshAvailable = welshAvailable;
        services = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public Pharmacy(Parcel in) {
        name = in.readString();
        phoneNumber = in.readString();
        welshAvailable = (Boolean) in.readSerializable();
        services = new ArrayList<>();
        in.readList(services, Service.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean isWelshAvailable() {
        return welshAvailable;
    }

    public void addService(Service service) {
        if (!services.contains(service)) {
            services.add(service);
        } else {
            Log.d("Pharmacy", service.getName() + " already added to pharmacy.");
        }
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void setWelshAvailable(boolean welshAvailable) {
        this.welshAvailable = welshAvailable;
    }

    public Boolean doesService(Service service) {
        return services.contains(service);
    }

    public List<Service> getServices() {
        return services;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeSerializable(welshAvailable);
        dest.writeList(services);
    }

    public static final Creator<Pharmacy> CREATOR = new Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel source) {
            return new Pharmacy(source);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };
}
