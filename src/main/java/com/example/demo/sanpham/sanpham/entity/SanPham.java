package com.example.demo.sanpham.sanpham.entity;

import com.example.demo.sanpham.chip.entity.Chip;
import com.example.demo.sanpham.camerasau.entity.CameraSau;
import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import com.example.demo.sanpham.danhmuc.entity.DanhMuc;
import com.example.demo.sanpham.dophangiai.entity.DoPhanGiai;
import com.example.demo.sanpham.hang.entity.Hang;
import com.example.demo.sanpham.hedieuhanh.entity.HeDieuHanh;
import com.example.demo.sanpham.loaisac.entity.LoaiSac;
import com.example.demo.sanpham.pin.entity.Pin;
import com.example.demo.sanpham.sanphamchitiet.sanphamchitiet.entity.SanPhamChiTiet;
import com.example.demo.sanpham.thenho.entity.TheNho;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_hang")
    private Hang hang;

    @ManyToOne
    @JoinColumn(name = "id_the_nho")
    private TheNho theNho;

    @ManyToOne
    @JoinColumn(name = "id_loai_sac")
    private LoaiSac loaiSac;

    @ManyToOne
    @JoinColumn(name = "id_he_dieu_hanh")
    private HeDieuHanh heDieuHanh;

    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "id_do_phan_giai")
    private DoPhanGiai doPhanGiai;

    @ManyToOne
    @JoinColumn(name = "id_pin")
    private Pin pin;

    @ManyToOne
    @JoinColumn(name = "id_camera_truoc")
    private CameraTruoc cameraTruoc;

    @ManyToOne
    @JoinColumn(name = "id_camera_sau")
    private CameraSau cameraSau;

    @ManyToOne
    @JoinColumn(name = "id_chip")
    private Chip chip;

    @ManyToOne
    @JoinColumn(name = "id_san_pham_chi_tiet")
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "ma_san_pham", unique = true, nullable = false)
    private String maSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "deleted")
    private Integer deleted;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CODE_LENGTH = 8;

    public static String generateCode() {
        Random random = new SecureRandom();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        return code.toString();
    }

    @PrePersist
    private void prePersist() {
        if (maSanPham == null || maSanPham.isEmpty()) {
            maSanPham = generateCode();
        }
    }
}
