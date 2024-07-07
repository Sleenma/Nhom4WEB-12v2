package com.example.demo.sanpham.sanphamchitiet.Rom.controller;

import com.example.demo.sanpham.sanphamchitiet.Rom.entity.Rom;
import com.example.demo.sanpham.sanphamchitiet.Rom.repository.RomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/Roms")
public class RomController {

    @Autowired
    private RomRepository romRepository;

    @GetMapping
    public String hienThi(Model model) {
        model.addAttribute("Roms", romRepository.findAll());
        model.addAttribute("rom", new Rom()); // This will be used for form binding
        return "sanpham/sanphamchitiet/rom";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute Rom rom) {
        romRepository.save(rom);
        return "redirect:/SanPham/Roms";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id, Model model) {
        Optional<Rom> rom = romRepository.findById(id);
        rom.ifPresent(r -> model.addAttribute("rom", r));
        model.addAttribute("Roms", romRepository.findAll());
        return "sanpham/sanphamchitiet/rom";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        romRepository.deleteById(id);
        return "redirect:/SanPham/Roms";
    }
}
