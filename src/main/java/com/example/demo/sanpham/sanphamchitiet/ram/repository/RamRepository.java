package com.example.demo.sanpham.sanphamchitiet.ram.repository;

import com.example.demo.sanpham.sanphamchitiet.ram.entity.Ram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RamRepository extends JpaRepository<Ram, UUID> {
}
