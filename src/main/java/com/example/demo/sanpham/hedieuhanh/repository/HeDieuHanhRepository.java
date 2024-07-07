package com.example.demo.sanpham.hedieuhanh.repository;

import com.example.demo.sanpham.hedieuhanh.entity.HeDieuHanh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HeDieuHanhRepository extends JpaRepository<HeDieuHanh, UUID> {




}
