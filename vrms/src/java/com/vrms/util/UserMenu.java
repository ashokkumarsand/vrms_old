/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ashok
 */
public enum UserMenu {
    USER_MANAGE{

        @Override
        public List<Permissions> getPermissions() {
            return Arrays.asList(Permissions.ADD_USER,Permissions.BLOCK_USER,Permissions.UNBLOCK_USER);
        }
        
    },CAB_MANAGE{

        @Override
        public List<Permissions> getPermissions() {
            return Arrays.asList(Permissions.ADD_CAB,Permissions.ADD_VEHICLE,Permissions.ASSIGN_CAB,Permissions.VIEW_CAB);
        }
        
    },REQUEST_CAB{ 

        @Override
        public List<Permissions> getPermissions() {
            return Arrays.asList(Permissions.REQUEST_MAKE,Permissions.REQUEST_ONLY);
        }
        
    },LOG_MANAGE{

        @Override
        public List<Permissions> getPermissions() {
            return Arrays.asList(Permissions.VIEW_LOG,Permissions.INSERT_LOG);
        }
        
    };
    public abstract List<Permissions> getPermissions();
}
