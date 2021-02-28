package com.customify.server.data_format.CustomerFeedback;
/**
 * @author: NSENGIYUMVA Gershom
 * 
 * @Description
 * 
 * Setting and getting feedback data to be formatted
 * 
 * done 7 Feb 2021
 * */ 
import java.io.Serializable;

public class CustomerFeedbackDataFormat implements Serializable{    
    private int customer_id, business_id;
    private String title, description;

    public CustomerFeedbackDataFormat(int customer_id, int business_id, String title, String description) {        
        this.customer_id = customer_id;
        this.business_id = business_id;
        this.title = title;
        this.description = description;
    }


    // define the getters and setters
    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getBusinessId() {
        return business_id;
    }

    public void setBusinessId(int business_id) {
        this.business_id = business_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
