package com.example.c1662348.updatepharmacyapp;

import java.io.Serializable;

public class Service implements Serializable {
    private String name;

    public Service(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        else if (o instanceof Service) {
            Service otherService = (Service) o;
            return name.toLowerCase().equals(otherService.name.toLowerCase());
        } else if (o instanceof String) {
            String serviceName = (String) o;
            return name.toLowerCase().equals(serviceName.toLowerCase());
        }
        else return false;
    }
}
