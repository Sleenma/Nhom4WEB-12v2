package com.example.demo.khachhang.serive;

import com.example.demo.khachhang.entity.DiaChi;
import com.example.demo.khachhang.entity.KhachHang;
import com.example.demo.khachhang.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;


    public void removeKhachHang(UUID id) {
        KhachHang khachHang = khachHangRepository.findById(id).orElse(null);
        if (khachHang != null) {
            khachHang.setDeleted(false); // Cập nhật trạng thái thành false
            khachHangRepository.save(khachHang);

            // Cập nhật trạng thái deleted của DiaChi

        }
    }
}
