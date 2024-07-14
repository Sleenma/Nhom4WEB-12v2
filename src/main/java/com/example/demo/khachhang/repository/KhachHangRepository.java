package com.example.demo.khachhang.repository;

import com.example.demo.khachhang.entity.KhachHang;
import com.example.demo.khachhang.response.KhachHangResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
//@Query("SELECT kh FROM KhachHang kh JOIN DiaChi dc ON kh.diaChi.idDC = dc.idDC WHERE kh.deleted = true AND (kh.tenKH LIKE %:tenKH% OR kh.sdt LIKE %:sdt% OR kh.email LIKE %:email% OR kh.deleted = :deleted )")
//Page<KhachHang> timKhachHangTheoTenVaSDT(@Param("tenKH") String tenKH, @Param("sdt") String sdt, @Param("email") String email,@Param("deleted") Boolean deleted, Pageable pageable);

    @Query("SELECT new com.example.demo.khachhang.response.KhachHangResponse(kh.id, kh.maKH, kh.tenKH, kh.email, kh.ngaySinh, kh.sdt, dc.tinhTen, dc.quanTen, dc.phuongTen, kh.deleted) " +
            "FROM KhachHang kh JOIN kh.diaChiList dc " +
            "WHERE kh.deleted = :deleted AND " +
            "(kh.tenKH LIKE %:tenKH% OR kh.sdt LIKE %:sdt% OR kh.email LIKE %:email%)")
    Page<KhachHangResponse> timKhachHangTheoTenVaSDT(@Param("tenKH") String tenKH,
                                                     @Param("sdt") String sdt,
                                                     @Param("email") String email,
                                                     @Param("deleted") Boolean deleted,
                                                     Pageable pageable);



}