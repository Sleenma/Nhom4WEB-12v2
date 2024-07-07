package com.example.demo.sanpham.danhmuc.repository;

import com.example.demo.sanpham.danhmuc.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DanhMucRepository extends JpaRepository<DanhMuc, UUID> {
}
