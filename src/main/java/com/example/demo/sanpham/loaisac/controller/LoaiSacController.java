package com.example.demo.sanpham.loaisac.controller;

import com.example.demo.sanpham.loaisac.entity.LoaiSac;
import com.example.demo.sanpham.loaisac.repository.LoaiSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/LoaiSacs")
public class LoaiSacController {

    @Autowired
    private LoaiSacRepository loaiSacRepository;

    @GetMapping
    public String listLoaiSacs(Model model) {
        model.addAttribute("loaiSacs", loaiSacRepository.findAll());
        model.addAttribute("loaiSac", new LoaiSac()); // for form binding
        return "sanpham/loaisac";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("loaiSac") LoaiSac loaiSac) {
        loaiSacRepository.save(loaiSac);
        return "redirect:/SanPham/LoaiSacs";
    }

    @GetMapping("/edit/{id}")
    public String editLoaiSac(@PathVariable("id") UUID id, Model model) {
        Optional<LoaiSac> optionalLoaiSac = loaiSacRepository.findById(id);
        optionalLoaiSac.ifPresent(loaiSac -> model.addAttribute("loaiSac", loaiSac));
        model.addAttribute("loaiSacs", loaiSacRepository.findAll());
        return "sanpham/loaisac";
    }

    @GetMapping("/delete/{id}")
    public String deleteLoaiSac(@PathVariable("id") UUID id) {
        loaiSacRepository.deleteById(id);
        return "redirect:/SanPham/LoaiSacs";
    }
}
