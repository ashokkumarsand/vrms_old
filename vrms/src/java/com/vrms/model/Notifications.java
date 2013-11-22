/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.model;

/**
 *
 * @author acts
 */
public class Notifications {
    private Integer notificationID;
    private Integer notificationFrom;
    private Integer notificationTo;
    private String notificationText;
    private Boolean notificationStatus;

    public Integer getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Integer notificationID) {
        this.notificationID = notificationID;
    }

    public Integer getNotificationFrom() {
        return notificationFrom;
    }

    public void setNotificationFrom(Integer notificationFrom) {
        this.notificationFrom = notificationFrom;
    }

    public Integer getNotificationTo() {
        return notificationTo;
    }

    public void setNotificationTo(Integer notificationTo) {
        this.notificationTo = notificationTo;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public Boolean getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(Boolean notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString() {
        return "Notifications{" + "notificationID=" + notificationID + ", notificationFrom=" + notificationFrom + ", notificationTo=" + notificationTo + ", notificationText=" + notificationText + ", notificationStatus=" + notificationStatus + '}';
    }
     
}
