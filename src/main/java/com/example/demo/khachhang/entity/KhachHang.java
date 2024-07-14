package com.example.demo.khachhang.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "khach_hang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

//
//@JoinColumn(name = "idDC")
@OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL,orphanRemoval = true)
private List<DiaChi> diaChiList = new ArrayList<>();


//    @ManyToOne(cascade = CascadeTy    pe.PERSIST)// CascadeType.PERSIST sẽ tự động lưu đối tượng DiaChi nếu nó chưa được lưu
//    private DiaChi diaChi;

    @Column(name = "ten_khach_hang")
    private String tenKH;

    @Column(name = "makhach_hang", unique = true, nullable = false)
    private String maKH;

    @Column(name = "so_dien_thoai")
    private String sdt;

    @Column(name = "ngay_sinh")
    private String ngaySinh;

    @Column(name = "email")
    private String email;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "ngay_bat_dau")
    private Date ngayTao;

    @Column(name = "ngay_ket_thuc")
    private Date ngaySua;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "deleted")
    private Boolean deleted = true;

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
        if (maKH == null || maKH.isEmpty()) {
            maKH = generateCode();
        }
        if (matKhau == null || matKhau.isEmpty()) {
            matKhau = "1"; // Default password set to "1"
        }
//        ngayTao = new Date();
    }

}
