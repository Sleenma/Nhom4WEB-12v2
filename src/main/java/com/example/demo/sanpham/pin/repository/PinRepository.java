package com.example.demo.sanpham.pin.repository;

import com.example.demo.sanpham.pin.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PinRepository extends JpaRepository<Pin, UUID> {
}
