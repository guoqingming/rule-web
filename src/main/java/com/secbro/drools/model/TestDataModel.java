package com.secbro.drools.model;

import lombok.Data;
import org.kie.api.definition.type.Label;

import java.io.Serializable;

@Data
public class TestDataModel implements Serializable {
    static final long serialVersionUID = 1L;
    @Label("姓名")
    private String name;
    @Label("积分")
    private int score;
    @Label("年龄")
    private int age;

    public TestDataModel() {
    }


}
