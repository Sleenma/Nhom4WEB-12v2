package com.example.demo.sanpham.sanphamchitiet.imei.repository;

import com.example.demo.sanpham.sanphamchitiet.imei.entity.Imei;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImeiRepository extends JpaRepository<Imei, UUID> {
}
