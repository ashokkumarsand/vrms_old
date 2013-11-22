/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrms.viewallocations;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author acts
 */
public class Request {
    
    private Integer requestID;
    private Integer userID;
    private Timestamp startTime;
     private Timestamp endTime;
     private Integer mobileNO;
      private Integer pointID;
       private Integer requestStatusID;
       private String purpose;

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getMobileNO() {
        return mobileNO;
    }

    public void setMobileNO(Integer mobileNO) {
        this.mobileNO = mobileNO;
    }

    public Integer getPointID() {
        return pointID;
    }

    public void setPointID(Integer pointID) {
        this.pointID = pointID;
    }

    public Integer getRequestStatusID() {
        return requestStatusID;
    }

    public void setRequestStatusID(Integer requestStatusID) {
        this.requestStatusID = requestStatusID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Request{" + "requestID=" + requestID + ", userID=" + userID + ", startTime=" + startTime + ", endTime=" + endTime + ", mobileNO=" + mobileNO + ", pointID=" + pointID + ", requestStatusID=" + requestStatusID + ", purpose=" + purpose + '}';
    }
     
}
