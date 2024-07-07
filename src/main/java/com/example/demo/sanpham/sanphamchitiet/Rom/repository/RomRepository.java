package com.example.demo.sanpham.sanphamchitiet.Rom.repository;

import com.example.demo.sanpham.sanphamchitiet.Rom.entity.Rom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RomRepository extends JpaRepository<Rom, UUID> {
}
