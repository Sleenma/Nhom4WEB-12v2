package com.example.demo.sanpham.sanphamchitiet.ram.controller;

import com.example.demo.sanpham.sanphamchitiet.ram.entity.Ram;
import com.example.demo.sanpham.sanphamchitiet.ram.repository.RamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/Rams")
public class RamController {

    @Autowired
    private RamRepository ramRepository;

    @GetMapping
    public String hienThi(Model model) {
        List<Ram> rams = ramRepository.findAll();
        model.addAttribute("rams", rams);
        model.addAttribute("ram", new Ram()); // Empty Ram object for form binding
        return "sanpham/sanphamchitiet/ram"; // Thymeleaf template name for rendering RAM items and form
    }

    @PostMapping("/add")
    public String themRam(@ModelAttribute("ram") Ram ram) {
        ramRepository.save(ram);
        return "redirect:/SanPham/Rams"; // Redirect to the RAM list page after adding
    }

    @GetMapping("/edit/{id}")
    public String suaRam(@PathVariable("id") UUID id, Model model) {
        Ram ram = ramRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid RAM Id:" + id));
        model.addAttribute("ram", ram);
        List<Ram> rams = ramRepository.findAll();
        model.addAttribute("rams", rams);
        return "sanpham/sanphamchitiet/ram"; // Thymeleaf template name for rendering RAM items and form
    }

    @GetMapping("/delete/{id}")
    public String xoaRam(@PathVariable("id") UUID id) {
        ramRepository.deleteById(id);
        return "redirect:/SanPham/Rams"; // Redirect to the RAM list page after deletion
    }
}
