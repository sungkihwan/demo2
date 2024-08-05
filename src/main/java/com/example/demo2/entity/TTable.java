package com.example.demo2.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ttable")
@EqualsAndHashCode(callSuper=false)
public class TTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "t_id")
    private Long tId;
    @Column(nullable = false)
    private String ttt;

    @Column(name = "member_id")
    private Long memberId;
}
