/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.model;

import java.util.ArrayList;
import java.util.List;

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

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public List<Integer> getRequestUserId() {
        if(requestUserId==null){
            requestUserId = new ArrayList<>();
        }
        return requestUserId;
    }

    public enum PointType {

        PICK {
            @Override
            public int getDBValue() {
                return 1;
            }
        }, DROP {
            @Override
            public int getDBValue() {
                return 2;
            }
        }, VISIT {
            @Override
            public int getDBValue() {
                return 3;
            }
        };

        public abstract int getDBValue();
    }
    private Integer id;
    private Integer fromId;
    private List<Integer> requestUserId;
    private Integer requestId;
    private PointType pointType;
    private Integer nextId;
}
