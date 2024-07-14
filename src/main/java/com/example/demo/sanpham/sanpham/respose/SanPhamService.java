package com.example.demo.sanpham.sanpham.respose;

import com.example.demo.sanpham.sanpham.entity.SanPham;
import com.example.demo.sanpham.sanpham.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {
//    @Autowired
//    private SanPhamRepository sanPhamRepository;
//
//    public List<SanPham> getSanPhamByMaDanhMuc(String maDanhMuc) {
//        return sanPhamRepository.findSanPhamByMaDanhMuc(maDanhMuc);
//    }
//
//    public Page<SanPham> searchSanPham(String keyword, String maDanhMuc, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        String searchKeyword = "%" + keyword + "%";
//        return sanPhamRepository.searchByKeywordAndMaDanhMuc(searchKeyword, maDanhMuc, pageable);
//    }
}
