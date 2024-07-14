package com.example.demo.hoadon.hoadon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "hoa_don")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ma", unique = true, nullable = false)
    private String ma;

    @Column(name = "id_nhan_vien")
    private UUID idNhanVien;

    @Column(name = "id_khach_hang")
    private UUID idKhachHang;

    @Column(name = "id_phieu_giam_gia")
    private UUID idPhieuGiamGia;

    @Column(name = "id_imei")
    private UUID idImei;

    @Column(name = "hanh_dong")
    private Integer hanhDong;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "loai_hoa_don")
    private Integer loaiHoaDon;

    @Column(name = "tong_san_pham")
    private Integer tongSanPham;

    @Column(name = "tong_tien")
    private Double tongTien;

    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;

    @Column(name = "so_dien_thoai")
    private Integer soDienThoai;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ngay_thanh_toan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    @Column(name = "ngay_sua")
    private LocalDateTime ngaySua;

    @Column(name = "ngay_nhan")
    private LocalDateTime ngayNhan;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Column(name = "deleted")
    private Integer deleted;

    public String getFormattedNgayTao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return this.ngayTao.format(formatter);
    }
}
