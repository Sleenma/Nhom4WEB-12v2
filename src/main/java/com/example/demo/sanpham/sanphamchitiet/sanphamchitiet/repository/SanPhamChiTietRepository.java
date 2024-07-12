package com.example.demo.sanpham.sanphamchitiet.sanphamchitiet.repository;

import com.example.demo.sanpham.sanphamchitiet.sanphamchitiet.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, UUID> {
}
