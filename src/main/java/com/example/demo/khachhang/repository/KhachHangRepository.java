package com.example.demo.khachhang.repository;

import com.example.demo.khachhang.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
//    @Query("SELECT new com.example.KhachHang(kh.soDienThoai, kh.maKhachHang, kh.tenKhachHang, kh.id, " +
//            "CONCAT(dc.tinhDiaChi, ', ', dc.quanDiaChi, ', ', dc.xaDiaChi)) " +
//            "FROM KhachHang kh JOIN DiaChi dc ON kh.idDC = dc.id")
//    List<KhachHang> findAllKhachHangWithDiaChi();
//@Query("SELECT kh FROM KhachHang kh WHERE kh.deleted = true ")
//List<KhachHang> findAllByDeletedTrue();
//    @Query("SELECT kh.sdt, kh.maKH, kh.tenKH, kh.id FROM KhachHang kh WHERE kh.tenKH LIKE %:tenKH% AND kh.sdt LIKE %:sdt%")
//    List<Object[]> timKhachHangTheoTenVaSDT(@Param("tenKhachHang") String tenKhachHang, @Param("soDienThoai") String soDienThoai);
@Query("SELECT kh FROM KhachHang kh WHERE kh.deleted = true AND (kh.tenKH LIKE %:tenKH% OR kh.sdt LIKE %:sdt%)")
Page<KhachHang> timKhachHangTheoTenVaSDT(@Param("tenKH") String tenKH, @Param("sdt") String sdt, Pageable pageable);


}
