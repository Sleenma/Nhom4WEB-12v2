package com.example.demo.sanpham.sanphamchitiet.anhsanpham.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "anh_san_pham")

public class AnhSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "link")
    private String link;

    @Column(name = "ten_anh_san_pham")
    private String tenAnhSanPham;

    @Column(name = "trang_thai")
    private int trangThai;

}
