/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vrms.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ashok
 */
public class ValidatePhoneNumber {

    private static final String MOBILE_PATTERN = "\\d{10}";
    private static Pattern pattern = Pattern.compile(MOBILE_PATTERN);

    public ValidatePhoneNumber() {
    }

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
