
package com.webank.ai.fate.client.form.task;
import lombok.Data;

import java.util.List;


@Data
public class Role {

    private List<Integer> guest;
    private List<Integer> host;

}