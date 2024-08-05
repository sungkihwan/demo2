package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "member_id")
    private Long memberId;
    @Column(nullable = false)
    private String email;
    @OneToMany(fetch = FetchType.LAZY)
    @Builder.Default
    @JoinColumn(name = "member_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<TestE> test = new ArrayList<>();

    @OneToMany()
    @Builder.Default
    @JsonIgnore
    @JoinColumn(name = "member_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<TestE> test2 = new ArrayList<>();

    @OneToMany()
    @Builder.Default
    @JsonIgnore
    @JoinColumn(name = "member_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<TestE> test3 = new ArrayList<>();

    @OneToMany()
    @Builder.Default
    @JoinColumn(name = "member_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<TestE> test4 = new ArrayList<>();

    @OneToMany()
    @Builder.Default
//    @JsonIgnore
    @JoinColumn(name = "member_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<B> b = new ArrayList<>();
}