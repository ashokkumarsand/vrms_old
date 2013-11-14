/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.validate;

import java.util.regex.Pattern;

/**
 *
 * @author Ashok
 */
public class ValidateExtensionNo {
    private static final String EXT_PATTERN = "\\d+";
    private static Pattern pattern = Pattern.compile(EXT_PATTERN);


    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean validate(final String hex) {
        return pattern.matcher(hex).matches();

    }
}
