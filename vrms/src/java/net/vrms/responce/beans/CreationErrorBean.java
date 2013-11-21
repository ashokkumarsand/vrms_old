/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vrms.responce.beans;

import java.util.ArrayList;
import java.util.List;
import net.vrms.request.constants.CustomErrorsValues;

/**
 *
 * @author Ashok
 */
public class CreationErrorBean {
    private List<ErrorBean> errors;

    public CreationErrorBean() {
        errors = new ArrayList<>();
    }

    public List<ErrorBean> getErrors() {
        return errors;
    }
    
    
    public class ErrorBean{
        private String field;
        private CustomErrorsValues value;
        private String message;

        public ErrorBean(String field, CustomErrorsValues value, String message) {
            this.field = field;
            this.value = value;
            this.message = message;
        }

        
        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public CustomErrorsValues getValue() {
            return value;
        }

        public void setValue(CustomErrorsValues value) {
            this.value = value;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
