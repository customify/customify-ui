package com.customify.cli.data_format.billing;

import com.customify.cli.Keys;
import com.customify.server.models.submodels.Plan;

// Created BY Moss Aimelyse whole document

public class PlanFormat extends Plan<Integer, String, String>{
    private Keys key;

    public PlanFormat(Keys key, Integer planId, String planTitle, String planDescription) {
        super(planId, planTitle, planDescription);
        this.key =key;
    }
    public void setKey(Keys key) {
        this.key = key;
    }
    public Keys getKey() {
        return this.key;
    }
}
