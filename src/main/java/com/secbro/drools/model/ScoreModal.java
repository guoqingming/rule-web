package com.secbro.drools.model;

import lombok.Data;
import org.kie.api.definition.type.Label;

import java.io.Serializable;

/**
 * @program: springboot-drools-integration
 * @description:
 * @author: guoqingming
 * @create: 2018-12-20 15:50
 **/
@Data
public class ScoreModal implements Serializable {
    static final long serialVersionUID = 1L;
    @Label("分数")
    private int score;

    public ScoreModal(int score) {
        this.score = score;
    }
}