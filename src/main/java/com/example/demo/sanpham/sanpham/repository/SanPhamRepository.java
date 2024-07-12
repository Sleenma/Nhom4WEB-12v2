package com.example.demo.sanpham.sanpham.repository;

import com.example.demo.sanpham.sanpham.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
//
//    @Query("SELECT *" +
//            "FROM SanPham s " +
//            "JOIN SanPhamChiTiet spct ON s.id = spct.sanPham.id " +
//            "JOIN AnhSanPham a ON spct.anhSanPham.id = a.id " +
//            "JOIN Hang h ON s.hang.id = h.id " +
//            "WHERE s.deleted = 0 and where  ")
//    List<SanPham> findAllActiveProducts(String keyword, String key, Pageable pageable);
     Page<SanPham> findSanPhamByMaSanPhamLikeOrTenSanPhamLike(String keyword, String key, Pageable pageable);



}
