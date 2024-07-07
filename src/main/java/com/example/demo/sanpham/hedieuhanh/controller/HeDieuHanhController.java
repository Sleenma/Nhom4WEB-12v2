package com.example.demo.sanpham.hedieuhanh.controller;

import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import com.example.demo.sanpham.hang.repository.HangRepository;
import com.example.demo.sanpham.hedieuhanh.entity.HeDieuHanh;
import com.example.demo.sanpham.hedieuhanh.repository.HeDieuHanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/HeDieuHanhs")
public class HeDieuHanhController {
    @Autowired
    private HeDieuHanhRepository heDieuHanhRepository;

    @GetMapping
    public String hienThi(Model model) {
        model.addAttribute("HeDieuHanh", heDieuHanhRepository.findAll());
        return "sanpham/hedieuhanh";

    }

    @PostMapping("/Add")
    public String add(HeDieuHanh heDieuHanh) {
        heDieuHanhRepository.save(heDieuHanh);
        return "redirect:/SanPham/HeDieuHanhs";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<HeDieuHanh> optionalCameraSau = heDieuHanhRepository.findById(id);
        if (optionalCameraSau.isPresent()) {
            model.addAttribute("dedieu", optionalCameraSau.get());
            model.addAttribute("HeDieuHanh", heDieuHanhRepository.findAll());
            return "sanpham/hedieuahnh";
        } else {
            return "redirect:/SanPham/HeDieuHanhs";
        }
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        heDieuHanhRepository.deleteById(id);
        return "redirect:/SanPham/HeDieuHanhs";
    }
}
