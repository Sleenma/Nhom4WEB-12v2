package com.example.demo.sanpham.hang.repository;

import com.example.demo.sanpham.hang.entity.Hang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HangRepository extends JpaRepository<Hang, UUID> {
}
