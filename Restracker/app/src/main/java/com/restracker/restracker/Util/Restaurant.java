package com.restracker.restracker.Util;

import java.util.ArrayList;

public class Restaurant {
    private String restaurantName;
    private String restuarantAddress;
    private String restaurantLatitude;
    private String restaurantLongitude;
    private String tags;
    private float stars;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestuarantAddress() {
        return restuarantAddress;
    }

    public void setRestuarantAddress(String restuarantAddress) {
        this.restuarantAddress = restuarantAddress;
    }

    public String getRestaurantLatitude() {
        return restaurantLatitude;
    }

    public void setRestaurantLatitude(String restaurantLatitude) {
        this.restaurantLatitude = restaurantLatitude;
    }

    public String getRestaurantLongitude() {
        return restaurantLongitude;
    }

    public void setRestaurantLongitude(String restaurantLongitude) {
        this.restaurantLongitude = restaurantLongitude;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float starts) {
        this.stars = starts;
    }
}
