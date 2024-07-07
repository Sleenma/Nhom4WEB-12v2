package com.example.demo.sanpham.camerasau.repository;

import com.example.demo.sanpham.camerasau.entity.CameraSau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CameraSauRepository extends JpaRepository<CameraSau, UUID> {

}
