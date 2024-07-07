package com.example.demo.sanpham.sanphamchitiet.mausac.repository;

import com.example.demo.sanpham.sanphamchitiet.mausac.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MauSacRepository extends JpaRepository<MauSac, UUID> {

}
