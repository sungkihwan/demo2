package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "test")
public class TestE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long testId;
    @Column(nullable = false, name = "member_id")
    private Long memberId;
    @Column(nullable = false, name = "t_id")
    private Long tId;

    @ManyToOne()
    @JoinColumn(
            name = "t_id",
            referencedColumnName = "t_id",
            insertable=false,
            updatable=false,
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private TTable tTable;
}
