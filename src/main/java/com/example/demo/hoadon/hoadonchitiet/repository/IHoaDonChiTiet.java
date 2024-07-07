package com.example.demo.hoadon.hoadonchitiet.repository;

import com.example.demo.hoadon.hoadonchitiet.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHoaDonChiTiet extends JpaRepository<HoaDonChiTiet, UUID> {
}
