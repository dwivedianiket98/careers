package com.interscope.careers.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobApplication {

    @Id
    private Integer jobApplicationId;
    private Integer jobId;

    private Integer userId;

    private JobApplcationStatus status;
}
