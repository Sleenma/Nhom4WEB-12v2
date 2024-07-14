package com.example.demo.khachhang.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangResponse {
    private UUID id;
    private String maKH;
    private String tenKH;
    private String email;
    private String ngaySinh;
    private String sdt;
    private String tinhTen;
    private String quanTen;
    private String phuongTen;
    private Boolean deleted;
}
