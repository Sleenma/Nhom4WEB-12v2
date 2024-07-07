package com.example.demo.sanpham.loaisac.controller;

import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import com.example.demo.sanpham.dophangiai.repository.DoPhanGiaiRepository;
import com.example.demo.sanpham.loaisac.entity.LoaiSac;
import com.example.demo.sanpham.loaisac.repository.LoaiSacRepository;
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
@RequestMapping("/SanPham/LoaiSacs")
public class LoaiSacController {

    @Autowired
    private LoaiSacRepository loaiSacRepository;

    @GetMapping
    public String hienThi(Model model){
        model.addAttribute("LoaiSac",loaiSacRepository.findAll());
        return "sanpham/loaisac";
    }
    @PostMapping("/Add")
    public String add(LoaiSac loaiSac) {
        loaiSacRepository.save(loaiSac);
        return "redirect:/SanPham/LoaiSacs";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<LoaiSac> optionalCameraSau = loaiSacRepository.findById(id);
        if (optionalCameraSau.isPresent()) {
            model.addAttribute("lsac", optionalCameraSau.get());
            model.addAttribute("LoaiSac", loaiSacRepository.findAll());
            return "sanpham/loaisac";
        } else {
            return "redirect:/SanPham/LoaiSacs";
        }
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        loaiSacRepository.deleteById(id);
        return "redirect:/SanPham/LoaiSacs";
    }
}
