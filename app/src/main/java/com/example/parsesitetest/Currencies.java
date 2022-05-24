package com.example.parsesitetest;

public class Currencies {
    private String currName;
    private String firstVal;
    private String firstBank;
    private String secVal;
    private String secBank;

    public Currencies() {
    }

    public Currencies(String currName,  String firstBank, String firstVal, String secBank, String secVal) {
        this.currName = currName;
        this.firstVal = firstVal;
        this.firstBank = firstBank;
        this.secVal = secVal;
        this.secBank = secBank;
    }

    public String getCurrName() {
        return currName;
    }

    public void setCurrName(String currName) {
        this.currName = currName;
    }

    public String getFirstVal() {
        return firstVal;
    }

    public void setFirstVal(String firstVal) {
        this.firstVal = firstVal;
    }

    public String getFirstBank() {
        return firstBank;
    }

    public void setFirstBank(String firstBank) {
        this.firstBank = firstBank;
    }

    public String getSecVal() {
        return secVal;
    }

    public void setSecVal(String secVal) {
        this.secVal = secVal;
    }

    public String getSecBank() {
        return secBank;
    }

    public void setSecBank(String secBank) {
        this.secBank = secBank;
    }
}
