package com.example.demo.sanpham.thenho.repository;

import com.example.demo.sanpham.thenho.entity.TheNho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TheNhoRepository extends JpaRepository<TheNho, UUID> {
}
