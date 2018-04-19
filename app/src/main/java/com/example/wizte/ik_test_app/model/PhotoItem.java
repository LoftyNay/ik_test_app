package com.example.wizte.ik_test_app.model;

import com.google.gson.annotations.SerializedName;

public class PhotoItem {

    @SerializedName("owner")
    private String owner;

    @SerializedName("server")
    private String server;

    @SerializedName("geo_is_friend")
    private int geoIsFriend;

    @SerializedName("isfriend")
    private int isfriend;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("woeid")
    private String woeid;

    @SerializedName("accuracy")
    private int accuracy;

    @SerializedName("secret")
    private String secret;

    @SerializedName("title")
    private String title;

    @SerializedName("height_m")
    private String heightM;

    @SerializedName("isfamily")
    private int isfamily;

    @SerializedName("geo_is_public")
    private int geoIsPublic;

    @SerializedName("geo_is_contact")
    private int geoIsContact;

    @SerializedName("geo_is_family")
    private int geoIsFamily;

    @SerializedName("ispublic")
    private int ispublic;

    @SerializedName("context")
    private int context;

    @SerializedName("farm")
    private int farm;

    @SerializedName("id")
    private String id;

    @SerializedName("url_s")
    private String urlM;

    @SerializedName("width_m")
    private String widthM;

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("longitude")
    private double longitude;

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getServer() {
        return server;
    }

    public void setGeoIsFriend(int geoIsFriend) {
        this.geoIsFriend = geoIsFriend;
    }

    public int getGeoIsFriend() {
        return geoIsFriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getIsfriend() {
        return isfriend;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setHeightM(String heightM) {
        this.heightM = heightM;
    }

    public String getHeightM() {
        return heightM;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }

    public int getIsfamily() {
        return isfamily;
    }

    public void setGeoIsPublic(int geoIsPublic) {
        this.geoIsPublic = geoIsPublic;
    }

    public int getGeoIsPublic() {
        return geoIsPublic;
    }

    public void setGeoIsContact(int geoIsContact) {
        this.geoIsContact = geoIsContact;
    }

    public int getGeoIsContact() {
        return geoIsContact;
    }

    public void setGeoIsFamily(int geoIsFamily) {
        this.geoIsFamily = geoIsFamily;
    }

    public int getGeoIsFamily() {
        return geoIsFamily;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setContext(int context) {
        this.context = context;
    }

    public int getContext() {
        return context;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public int getFarm() {
        return farm;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUrlM(String urlM) {
        this.urlM = urlM;
    }

    public String getUrlM() {
        return urlM;
    }

    public void setWidthM(String widthM) {
        this.widthM = widthM;
    }

    public String getWidthM() {
        return widthM;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return
                "PhotoItem{" +
                        "owner = '" + owner + '\'' +
                        ",server = '" + server + '\'' +
                        ",geo_is_friend = '" + geoIsFriend + '\'' +
                        ",isfriend = '" + isfriend + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",woeid = '" + woeid + '\'' +
                        ",accuracy = '" + accuracy + '\'' +
                        ",secret = '" + secret + '\'' +
                        ",title = '" + title + '\'' +
                        ",height_m = '" + heightM + '\'' +
                        ",isfamily = '" + isfamily + '\'' +
                        ",geo_is_public = '" + geoIsPublic + '\'' +
                        ",geo_is_contact = '" + geoIsContact + '\'' +
                        ",geo_is_family = '" + geoIsFamily + '\'' +
                        ",ispublic = '" + ispublic + '\'' +
                        ",context = '" + context + '\'' +
                        ",farm = '" + farm + '\'' +
                        ",id = '" + id + '\'' +
                        ",url_m = '" + urlM + '\'' +
                        ",width_m = '" + widthM + '\'' +
                        ",place_id = '" + placeId + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }
}