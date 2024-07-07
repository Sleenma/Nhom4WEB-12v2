package com.example.demo.sanpham.sanphamchitiet.Rom.entity;
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
@Table(name = "rom")
public class Rom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ma_rom", unique = true, nullable = false)
    private String maRom = UUID.randomUUID().toString();

    @Column(name = "dung_luong")
    private int dungLuong;

    @Column(name = "trang_thai")
    private int trangThai;
}
