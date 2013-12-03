/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.model;

/**
 *
 * @author Ashok
 */
public class Cab {

    private int id;
    private int typeid;
    private String name;
    private String description;
    private boolean status;
    private String cabTypeValue;
    private int capacity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCabTypeValue() {
        return cabTypeValue;
    }

    public void setCabTypeValue(String cabTypeValue) {
        this.cabTypeValue = cabTypeValue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
