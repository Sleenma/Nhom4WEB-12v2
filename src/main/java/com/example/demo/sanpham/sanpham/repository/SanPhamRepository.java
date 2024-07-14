package com.example.demo.sanpham.sanpham.repository;

import com.example.demo.sanpham.sanpham.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {

     Page<SanPham> findSanPhamByMaSanPhamLikeOrTenSanPhamLike(String keyword, String key, Pageable pageable);

     @Query(value = "SELECT COUNT(spct.imei.id) AS soLuong " +
             "FROM SanPham sp " +
             "JOIN sp.sanPhamChiTiet spct " +
             "WHERE sp.deleted = 0")
     Long countIdImeiWhereDeletedIsZero();







}
