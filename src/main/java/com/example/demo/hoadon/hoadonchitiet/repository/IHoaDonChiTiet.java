package com.example.demo.hoadon.hoadonchitiet.repository;

import com.example.demo.hoadon.hoadonchitiet.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHoaDonChiTiet extends JpaRepository<HoaDonChiTiet, UUID> {

    @Query("SELECT h FROM HoaDonChiTiet h WHERE STR(h.hoaDon.ma) LIKE :maHoaDon")
    Page<HoaDonChiTiet> search(@Param("maHoaDon") String maHoaDon, Pageable pageable);

}
