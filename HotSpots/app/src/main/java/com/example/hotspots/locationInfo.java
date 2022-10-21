package com.example.hotspots;

public class locationInfo {
    private String locName;
    private String address;
    private float beerRating;
    private float wineRating;
    private float musicRating;

    // Need empty constructor for Firebase Realtime Database
    public locationInfo(){

    }

    public String getLocName(){
        return locName;
    }

    public void setLocName(String locName){
        this.locName = locName;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public float getBeerRating(){
        return beerRating;
    }

    public void setBeerRating(float beerRating){
        this.beerRating = beerRating;
    }

    public float getWineRating(){
        return wineRating;
    }

    public void setWineRating(float wineRating){
        this.wineRating = wineRating;
    }

    public float getMusicRating(){
        return musicRating;
    }

    public void setMusicRating(float musicRating){
        this.musicRating = musicRating;
    }
}
