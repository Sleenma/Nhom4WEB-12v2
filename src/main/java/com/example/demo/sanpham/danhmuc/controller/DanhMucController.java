package com.example.demo.sanpham.danhmuc.controller;

import com.example.demo.sanpham.danhmuc.entity.DanhMuc;
import com.example.demo.sanpham.danhmuc.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/DanhMucs")
public class DanhMucController {

    @Autowired
    private DanhMucRepository danhMucRepository;

    @GetMapping
    public String listDanhMuc(Model model) {
        model.addAttribute("danhMucs", danhMucRepository.findAll());
        model.addAttribute("danhMuc", new DanhMuc()); // for form binding
        return "sanpham/danhmuc";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("danhMuc") DanhMuc danhMuc) {
        danhMucRepository.save(danhMuc);
        return "redirect:/SanPham/DanhMucs";
    }

    @GetMapping("/edit/{id}")
    public String editDanhMuc(@PathVariable("id") UUID id, Model model) {
        Optional<DanhMuc> optionalDanhMuc = danhMucRepository.findById(id);
        optionalDanhMuc.ifPresent(danhMuc -> model.addAttribute("danhMuc", danhMuc));
        model.addAttribute("danhMucs", danhMucRepository.findAll());
        return "sanpham/danhmuc";
    }

    @GetMapping("/delete/{id}")
    public String deleteDanhMuc(@PathVariable("id") UUID id) {
        danhMucRepository.deleteById(id);
        return "redirect:/SanPham/DanhMucs";
    }
}
