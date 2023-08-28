/**
 * Copyright 2022 bejson.com
 */

package com.webank.ai.fate.client.form.job;

public class Intersection {

    private Input input;

    private String module;

    private Output output;

    public void setInput(Input input) {
        this.input = input;
    }

    public Input getInput() {
        return input;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getModule() {
        return module;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public Output getOutput() {
        return output;
    }

}