package com.example.demo.sanpham.sanphamchitiet.imei.controller;

import com.example.demo.sanpham.sanphamchitiet.imei.entity.Imei;
import com.example.demo.sanpham.sanphamchitiet.imei.repository.ImeiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/Imeis")
public class ImeiController {

    @Autowired
    private ImeiRepository imeiRepository;

    @GetMapping
    public String listImeis(Model model) {
        List<Imei> imeis = imeiRepository.findAll();
        model.addAttribute("imeis", imeis);
        return "sanpham/sanphamchitiet/imei";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("imei", new Imei());
        return "sanpham/sanphamchitiet/imei";
    }

    @PostMapping("/add")
    public String addImei(@ModelAttribute("imei") Imei imei) {
        imeiRepository.save(imei);
        return "redirect:/SanPham/Imeis";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        Optional<Imei> optionalImei = imeiRepository.findById(id);
        if (optionalImei.isPresent()) {
            model.addAttribute("imei", optionalImei.get());
            return "sanpham/sanphamchitiet/imei";
        } else {
            return "redirect:/SanPham/Imeis";
        }
    }

    @PostMapping("/edit")
    public String updateImei(@ModelAttribute("imei") Imei imei) {
        imeiRepository.save(imei);
        return "redirect:/SanPham/Imeis";
    }

    @GetMapping("/delete/{id}")
    public String deleteImei(@PathVariable("id") UUID id) {
        imeiRepository.deleteById(id);
        return "redirect:/SanPham/Imeis";
    }
}
