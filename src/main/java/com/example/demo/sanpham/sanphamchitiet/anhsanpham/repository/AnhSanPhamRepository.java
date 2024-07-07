package com.example.demo.sanpham.sanphamchitiet.anhsanpham.repository;

import com.example.demo.sanpham.sanphamchitiet.anhsanpham.entity.AnhSanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnhSanPhamRepository extends JpaRepository<AnhSanPham, UUID> {
}
