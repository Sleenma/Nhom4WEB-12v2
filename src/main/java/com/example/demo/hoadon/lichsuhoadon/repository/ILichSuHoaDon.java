package com.example.demo.hoadon.lichsuhoadon.repository;

import com.example.demo.hoadon.lichsuhoadon.entity.LichSuHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ILichSuHoaDon extends JpaRepository<LichSuHoaDon, UUID> {

    @Query("SELECT h FROM LichSuHoaDon h WHERE STR(h.hoaDon.ma) LIKE :maHoaDon")
    Page<LichSuHoaDon> search(@Param("maHoaDon") String maHoaDon, Pageable pageable);

}
