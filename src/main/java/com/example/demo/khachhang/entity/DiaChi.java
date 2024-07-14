package com.example.demo.khachhang.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dia_chi")
public class DiaChi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDC", nullable = false, updatable = false)
    private UUID idDC;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)// CascadeType.PERSIST sẽ tự động lưu đối tượng DiaChi nếu nó chưa được lưu
    @JoinColumn(name = "id")
    private KhachHang khachHang;


    @Column(name = "ma_dia_chi", unique = true, nullable = false)
    private String maDC;

    @Column(name = "tinh_dia_chi")
    private String tinh;

    @Column(name = "quan_dia_chi")
    private String quan;

    @Column(name = "xa_dia_chi")
    private String xa;

    @Column(name = "ten_tinh_dia_chi")
    private String tinhTen;

    @Column(name = "ten_quan_dia_chi")
    private String quanTen;

    @Column(name = "ten_xa_dia_chi")
    private String phuongTen;

    @Column(name = "dia_chi_chi_tiet")
    private String diaChiCT;

    @Column(name = "ngay_bat_dau")
    private Date ngayTao;

    @Column(name = "ngay_ket_thuc")
    private Date ngaySua;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "deleted")
    private Boolean deleted =true;



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
        if (maDC == null || maDC.isEmpty()) {
            maDC = generateCode();
        }   if (idDC == null) {
            idDC = UUID.randomUUID();
        }

//        ngayTao = new Date();
    }


}
