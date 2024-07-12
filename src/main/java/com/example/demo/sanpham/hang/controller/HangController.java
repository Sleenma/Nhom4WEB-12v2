package com.example.demo.sanpham.hang.controller;

import com.example.demo.sanpham.hang.entity.Hang;
import com.example.demo.sanpham.hang.repository.HangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/Hangs")
public class HangController {

    @Autowired
    private HangRepository hangRepository;

    @GetMapping
    public String listHangs(Model model) {
        model.addAttribute("hangs", hangRepository.findAll());
        model.addAttribute("hang", new Hang()); // for form binding
        return "sanpham/hang";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("hang") Hang hang) {
        hangRepository.save(hang);
        return "redirect:/SanPham/Hangs";
    }

    @GetMapping("/edit/{id}")
    public String editHang(@PathVariable("id") UUID id, Model model) {
        Optional<Hang> optionalHang = hangRepository.findById(id);
        optionalHang.ifPresent(hang -> model.addAttribute("hang", hang));
        model.addAttribute("hangs", hangRepository.findAll());
        return "sanpham/hang";
    }

    @GetMapping("/delete/{id}")
    public String deleteHang(@PathVariable("id") UUID id) {
        hangRepository.deleteById(id);
        return "redirect:/SanPham/Hangs";
    }
}
