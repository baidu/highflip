
package com.webank.ai.fate.client.form.task;
import lombok.Data;

import java.util.List;


@Data
public class TaskData {

    private int count;
    private List<FateTask> tasks;

}