package com.example.demo2.repository;

import com.example.demo2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Member, Integer> {
}
