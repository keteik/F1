package com.formula1.F1.circuit;

public class CircuitSeason {
    private String url;
    private String circuitName;
    private String locality;
    private String country;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public String getLocality() {
        return locality;
    }

    public String getCountry() {
        return country;
    }
}
