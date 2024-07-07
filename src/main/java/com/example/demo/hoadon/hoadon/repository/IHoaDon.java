package com.example.demo.hoadon.hoadon.repository;

import com.example.demo.hoadon.hoadon.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHoaDon extends JpaRepository<HoaDon, UUID> {
}
