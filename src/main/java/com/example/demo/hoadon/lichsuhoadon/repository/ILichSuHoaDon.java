package com.example.demo.hoadon.lichsuhoadon.repository;

import com.example.demo.hoadon.lichsuhoadon.entity.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ILichSuHoaDon extends JpaRepository<LichSuHoaDon, UUID> {
}
