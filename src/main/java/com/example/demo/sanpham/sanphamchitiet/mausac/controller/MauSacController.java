package com.example.demo.sanpham.sanphamchitiet.mausac.controller;

import com.example.demo.sanpham.sanphamchitiet.mausac.entity.MauSac;
import com.example.demo.sanpham.sanphamchitiet.mausac.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/MauSacs")
public class MauSacController {

    @Autowired
    private MauSacRepository mauSacRepository;

    @GetMapping
    public String hienThi(Model model) {
        List<MauSac> mauSacs = mauSacRepository.findAll();
        model.addAttribute("mauSacs", mauSacs);
        model.addAttribute("mauSac", new MauSac()); // For the add/edit form
        return "sanpham/sanphamchitiet/mausac"; // Thymeleaf template for listing and form
    }

    @PostMapping("/save")
    public String saveMauSac(@ModelAttribute("mauSac") MauSac mauSac) {
        mauSacRepository.save(mauSac);
        return "redirect:/SanPham/MauSacs"; // Redirect to list after adding/editing
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(id);
        if (optionalMauSac.isPresent()) {
            model.addAttribute("mauSac", optionalMauSac.get());
        }
        return "sanpham/sanphamchitiet/mausac"; // Thymeleaf template for listing and form
    }

    @GetMapping("/delete/{id}")
    public String deleteMauSac(@PathVariable("id") UUID id) {
        mauSacRepository.deleteById(id);
        return "redirect:/SanPham/MauSacs"; // Redirect to list after deletion
    }
}
