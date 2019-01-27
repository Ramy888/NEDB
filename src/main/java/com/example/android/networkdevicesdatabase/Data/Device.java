package com.example.android.networkdevicesdatabase.Data;

import java.io.Serializable;


/**
 * Created by Rami.magdy on 17/04/2018.
 */

public class Device implements Serializable{

    private String deviceName;
    private String deviceImage;
    private String deviceInfo_1;
    private String deviceInfo_2;
    private String deviceInfo_3;
    private String deviceInfo_4;
    private String deviceType;
    private String topologyImage;
    private String topologyName;
    private String videoUrl;
    private String pdfUrl;



    public Device() {
    }


    public String getDeviceName() {
        return deviceName;
    }

    /*public void setDeviceName(String name) {
        this.deviceName = name;
    }*/

    public String getDeviceImage() {
        return deviceImage;
    }

   /* public void setDeviceImage(String image) {
        this.deviceImage = image;
    }*/


    public String getDeviceInfo_1() {
        return deviceInfo_1;
    }

    /*public void setDeviceInfo_1(String info1) {
        this.deviceInfo_1 = info1;
    }*/


    public String getDeviceInfo_2() {
        return deviceInfo_2;
    }

   /* public void setDeviceInfo_2(String info2) {
        this.deviceInfo_2 = info2;
    }*/


    public String getDeviceInfo_3() {
        return deviceInfo_3;
    }

   /* public void setDeviceInfo_3(String info3) {
        this.deviceInfo_3 = info3;
    }*/

    public String getDeviceInfo_4() {
        return deviceInfo_4;
    }

   /* public void setDeviceInfo_4(String info4) {
        this.deviceInfo_4 = info4;
    }*/

    public String getDeviceType() {
        return deviceType;
    }

   /* public void setDeviceType(String type) {
        this.deviceType = type;
    }*/


    public String getTopologyImage() {
        return topologyImage;
    }

   /* public void setTopologyImage(String topologyImage) {
        this.topologyImage = topologyImage;
    }*/


    public String getTopologyName() {
        return topologyName;
    }

   /* public void setTopologyName(String topologyName) {
        this.topologyName = topologyName;
    }*/

    public String getVideoURL() {
        return videoUrl;
    }

    /*public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }*/

    public String getPdfUrl() {
        return pdfUrl;
    }


}

