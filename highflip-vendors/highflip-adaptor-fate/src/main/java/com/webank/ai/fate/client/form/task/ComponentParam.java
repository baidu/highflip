
package com.webank.ai.fate.client.form.task;
import lombok.Data;

import java.util.List;


@Data
public class ComponentParam {

    private List<String> _feeded_deprecated_params;
    private boolean _is_raw_conf;
    private String _name;
    private List<String> _user_feeded_params;
    private double adjustment_factor;
    private int bin_indexes;
    private String bin_names;
    private int bin_num;
    private String category_indexes;
    private String category_names;
    private int compress_thres;
    private Encrypt_param encrypt_param;
    private double error;
    private int head_size;
    private boolean local_only;
    private String method;
    private boolean need_run;
    private Optimal_binning_param optimal_binning_param;
    private boolean skip_static;
    private Transform_param transform_param;
}