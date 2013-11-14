/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.model;

import com.vrms.util.Permissions;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashok
 */
public class Roles {

    private int roleid;
    private String rolename;
    private List<Permissions> permission;

    public Roles() {
        permission = new ArrayList<>();
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<Permissions> getPermission() {
        return permission;
    }
    
    
}
