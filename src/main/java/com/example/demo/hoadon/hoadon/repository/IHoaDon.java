package com.example.demo.hoadon.hoadon.repository;

import com.example.demo.hoadon.hoadon.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IHoaDon extends JpaRepository<HoaDon, UUID> {

//    Page<HoaDon> findByMaLikeOrTenNguoiNhanLikeOrSoDienThoaiLike(String ma, String ten, String sdt, Pageable pageable);

    @Query("SELECT h FROM HoaDon h WHERE h.ma LIKE :ma OR h.tenNguoiNhan LIKE :ten OR STR(h.soDienThoai) LIKE :sdt")
    Page<HoaDon> search(@Param("ma") String ma, @Param("ten") String ten, @Param("sdt") String sdt, Pageable pageable);

}
