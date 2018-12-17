package com.example.c1662348.updatepharmacyapp;

import android.content.Context;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class Util {

    public static Map<String, String> htmlEncode(Pharmacy pharmacy) {
        Map<String, String> params = new HashMap<>();
        params.put("name", pharmacy.getName());
        params.put("phoneNumber", pharmacy.getPhoneNumber());
        params.put("welshAvailable", String.valueOf(pharmacy.isWelshAvailable()));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pharmacy.getServices().size(); i++) {
            String name = pharmacy.getServices().get(i).getName().replaceAll(" ", "_").toLowerCase();
            stringBuilder.append(TextUtils.htmlEncode(name));
            if (i < pharmacy.getServices().size() - 1) {
                stringBuilder.append(",");
            }
        }

        params.put("services", stringBuilder.toString());

        return params;
    }

    public static boolean systemLanguageIsWelsh(Context context) {
        Locale current = context.getResources().getConfiguration().locale;
        return current.getDisplayLanguage().equals("Cymraeg");
    }
}
