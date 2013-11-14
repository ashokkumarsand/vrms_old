/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.util;

/**
 *
 * @author Ashok
 */
public class VRMSUtil {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
