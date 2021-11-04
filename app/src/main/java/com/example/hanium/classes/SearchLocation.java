package com.example.hanium.classes;

public class SearchLocation {
    private String ADMNM;
    private String districtId;

    public SearchLocation(String ADMNM, String districtId) {
        this.ADMNM = ADMNM;
        this.districtId = districtId;
    }

    public String getADMNM() {
        return ADMNM;
    }

    public void setADMNM(String ADMNM) {
        this.ADMNM = ADMNM;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
}
