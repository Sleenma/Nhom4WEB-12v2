package com.example.demo.sanpham.chip.controller;

import com.example.demo.sanpham.chip.entity.Chip;
import com.example.demo.sanpham.chip.repository.ChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/Chips")
public class ChipController {
    @Autowired
    private ChipRepository chipRepository;

    @GetMapping
    public String hienThi(Model model) {
        model.addAttribute("chips", chipRepository.findAll());
        model.addAttribute("chip", new Chip());
        return "sanpham/chip";

    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("chip") Chip chip) {
        chipRepository.save(chip);
        return "redirect:/SanPham/Chips";
    }

    @GetMapping("/edit/{id}")
    public String editChip(@PathVariable("id") UUID id, Model model) {
        Optional<Chip> optionalChip = chipRepository.findById(id);
        optionalChip.ifPresent(chip -> model.addAttribute("chip", chip));
        model.addAttribute("Chips", chipRepository.findAll());
        return "sanpham/chip";
    }

    @GetMapping("/delete/{id}")
    public String deleteChip(@PathVariable("id") UUID id) {
        chipRepository.deleteById(id);
        return "redirect:/SanPham/Chips";
    }
}
