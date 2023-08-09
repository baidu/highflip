
package com.webank.ai.fate.client.form.task;


import lombok.Data;

@Data
public class Optimal_binning_param {

    private String adjustment_factor;
    private int init_bin_nums;
    private String init_bucket_method;
    private String max_bin;
    private int max_bin_pct;
    private String metric_method;
    private double min_bin_pct;
    private boolean mixture;
}