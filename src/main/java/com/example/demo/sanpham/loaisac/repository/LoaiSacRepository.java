package com.example.demo.sanpham.loaisac.repository;

import com.example.demo.sanpham.loaisac.entity.LoaiSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoaiSacRepository extends JpaRepository<LoaiSac, UUID> {
}
