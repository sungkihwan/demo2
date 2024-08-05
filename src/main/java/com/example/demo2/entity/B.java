package com.example.demo2.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "b")
@EqualsAndHashCode(callSuper=false)
public class B {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "b_id")
    private Long bId;

    @Column(name = "member_id")
    private Long memberId;
}
