package com.example.demo2.dto;

import com.example.demo2.entity.TestE;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;

@NoArgsConstructor()
@Builder
@Data
public class Response3 {
    private Long memberId;
    private String email;
    private List<TestE> tests;

    @QueryProjection
    public Response3(Long memberId, String email, List<TestE> tests){
        this.memberId = memberId;
        this.email = email;
        this.tests = tests;
    }
}
