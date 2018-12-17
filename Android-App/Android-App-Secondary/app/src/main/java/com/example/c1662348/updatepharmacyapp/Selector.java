package com.example.c1662348.updatepharmacyapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Selector extends DialogFragment {
    List<String> list = new ArrayList<>();
    UtilCallback callback;

    public void setCallback(UtilCallback callback){
        this.callback = callback;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] items = new String[Login.services.size() - 4];
        for (int i = 0; i < Login.services.size() - 4; i++) {
            items[i] = Login.services.get(i + 4).getName();
        }

        boolean[] checkedItems = new boolean[Login.services.size() - 4];
        if (!list.isEmpty()) {
            for (int i = 0; i < checkedItems.length; i++) {
                if (list.contains(Login.services.get(i + 4).getName())) {
                    checkedItems[i] = true;
                } else {
                    checkedItems[i] = false;
                }
            }
        } else {
            for (int i = 0; i < checkedItems.length; i++) {
                Pharmacy pharmacy = MainActivity.pharmacy;
                for (Service service : pharmacy.getServices()) {
                    System.out.println(service.getName());
                }
                if (pharmacy.doesService(Login.services.get(i + 4))) {
                    checkedItems[i] = true;
                }
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Select essential services on offer");
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    list.add(Login.services.get(which + 4).getName());
                } else if (list.contains(Login.services.get(which + 4).getName())) {
                    list.remove(Login.services.get(which + 4).getName());
                }
            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Service> services = new ArrayList<>();
                for (String s : list){
                    System.out.println(s);
                    services.add(new Service(s));
                }
                callback.onSuccess(services);
            }
        });

        return builder.create();
    }
}
