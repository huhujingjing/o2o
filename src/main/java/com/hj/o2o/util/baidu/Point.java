package com.hj.o2o.util.baidu;

/**
 * @Description:
 * @Author: HUJING
 * @Date: 2019/3/24 18:52
 */
public class Point {
    private double lng;

    private double lat;

    private String address;

    public Point(){}

    public Point(double lat,double lng,String address){
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
