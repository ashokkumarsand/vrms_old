/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.model;

/**
 *
 * @author Ashok
 */
public class Point {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(Integer requestUserId) {
        this.requestUserId = requestUserId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    public enum PointType {
        PICK, VIST, DROP;
    }
    
    private Integer id;
    private Integer requestUserId;
    private Integer requestId;
    private PointType pointType;
    private Integer nextId;
}
