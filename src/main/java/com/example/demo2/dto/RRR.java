package com.example.demo2.dto;

import com.example.demo2.entity.TestE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RRR {
    private Long memberId;
    private List<TestE> test;
}
