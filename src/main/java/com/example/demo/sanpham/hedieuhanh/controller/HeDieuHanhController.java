package com.example.demo.sanpham.hedieuhanh.controller;

import com.example.demo.sanpham.hedieuhanh.entity.HeDieuHanh;
import com.example.demo.sanpham.hedieuhanh.repository.HeDieuHanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/HeDieuHanhs")
public class HeDieuHanhController {

    @Autowired
    private HeDieuHanhRepository heDieuHanhRepository;

    @GetMapping
    public String listHeDieuHanh(Model model) {
        model.addAttribute("heDieuHanhs", heDieuHanhRepository.findAll());
        model.addAttribute("heDieuHanh", new HeDieuHanh()); // for form binding
        return "sanpham/hedieuhanh";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("heDieuHanh") HeDieuHanh heDieuHanh) {
        heDieuHanhRepository.save(heDieuHanh);
        return "redirect:/SanPham/HeDieuHanhs";
    }

    @GetMapping("/edit/{id}")
    public String editHeDieuHanh(@PathVariable("id") UUID id, Model model) {
        Optional<HeDieuHanh> optionalHeDieuHanh = heDieuHanhRepository.findById(id);
        optionalHeDieuHanh.ifPresent(heDieuHanh -> model.addAttribute("heDieuHanh", heDieuHanh));
        model.addAttribute("heDieuHanhs", heDieuHanhRepository.findAll());
        return "sanpham/hedieuhanh";
    }

    @GetMapping("/delete/{id}")
    public String deleteHeDieuHanh(@PathVariable("id") UUID id) {
        heDieuHanhRepository.deleteById(id);
        return "redirect:/SanPham/HeDieuHanhs";
    }
}
