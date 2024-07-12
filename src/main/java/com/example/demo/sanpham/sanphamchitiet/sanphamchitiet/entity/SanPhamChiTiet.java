package com.example.demo.sanpham.sanphamchitiet.sanphamchitiet.entity;

import com.example.demo.sanpham.sanpham.entity.SanPham;
import com.example.demo.sanpham.sanphamchitiet.Rom.entity.Rom;
import com.example.demo.sanpham.sanphamchitiet.anhsanpham.entity.AnhSanPham;
import com.example.demo.sanpham.sanphamchitiet.imei.entity.Imei;
import com.example.demo.sanpham.sanphamchitiet.mausac.entity.MauSac;
import com.example.demo.sanpham.sanphamchitiet.ram.entity.Ram;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "san_pham_chi_tiet")
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_imei",referencedColumnName = "id")
    private Imei imei;

    @ManyToOne
    @JoinColumn(name = "id_mau_sac",referencedColumnName = "id")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "id_ram",referencedColumnName = "id")
    private Ram ram;

    @ManyToOne
    @JoinColumn(name = "id_rom",referencedColumnName = "id")
    private Rom rom;

    @ManyToOne
    @JoinColumn(name = "id_anh_san_pham",referencedColumnName = "id")
    private AnhSanPham anhSanPham;

    @Column(name = "ma_san_pham_chi_tiet", unique = true, nullable = false)
    private String maSanPhamChiTiet;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private Double donGia;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

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
        if (maSanPhamChiTiet == null || maSanPhamChiTiet.isEmpty()) {
            maSanPhamChiTiet = generateCode();
        }
    }


}
