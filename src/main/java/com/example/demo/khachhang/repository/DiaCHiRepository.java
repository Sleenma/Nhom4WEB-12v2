package com.example.demo.khachhang.repository;

import com.example.demo.khachhang.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiaCHiRepository extends JpaRepository<DiaChi, UUID> {
}
