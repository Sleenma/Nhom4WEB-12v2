package com.example.demo.hoadon.hoadon.repository;

import com.example.demo.hoadon.hoadon.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface IHoaDon extends JpaRepository<HoaDon, UUID> {

    @Query("SELECT h FROM HoaDon h WHERE h.ma LIKE :ma OR h.tenNguoiNhan LIKE :ten OR STR(h.soDienThoai) LIKE :sdt")
    Page<HoaDon> search(@Param("ma") String ma, @Param("ten") String ten, @Param("sdt") String sdt, Pageable pageable);

    /**
     *
    @Query("SELECT h FROM HoaDon h WHERE (:minNgayTao IS NULL OR h.ngayTao >= :minNgayTao) AND (:maxNgayTao IS NULL OR h.ngayTao <= :maxNgayTao) AND (:minNgayTao IS NULL OR :maxNgayTao IS NULL OR h.ngayTao BETWEEN :minNgayTao AND :maxNgayTao)")
    Page<HoaDon> searchWithDateRange(
            @Param("minNgayTao") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime minNgayTao,
            @Param("maxNgayTao") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime maxNgayTao,
            Pageable pageable);

     */

    /** @Query("SELECT h FROM HoaDon h WHERE " +
            "(:keyword IS null OR h.ma LIKE %:keyword% OR h.tenNguoiNhan LIKE %:keyword% OR STR(h.soDienThoai) LIKE %:keyword%) " +
            "AND (:fromDate IS null OR h.ngayTao >= :fromDate) " +
            "AND (:toDate IS null OR h.ngayTao <= :toDate) ")
    Page<HoaDon> search(
            @Param("keyword") String keyword,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable); */
}
