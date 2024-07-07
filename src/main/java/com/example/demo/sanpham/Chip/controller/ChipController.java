package com.example.demo.sanpham.Chip.controller;

import com.example.demo.sanpham.Chip.entity.Chip;
import com.example.demo.sanpham.Chip.repository.ChipRepository;
import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import com.example.demo.sanpham.cameratruoc.repository.CameraTruocRepository;
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
@RequestMapping("/SanPham/Chips")
public class ChipController {
    @Autowired
    private ChipRepository chipRepository;

    @GetMapping
    public String hienThi(Model model) {
        model.addAttribute("Chip", chipRepository.findAll());
        return "sanpham/chip";
    }
    @PostMapping("/Add")
    public String add(Chip chip) {
        chipRepository.save(chip);
        return "redirect:/SanPham/Chips";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<Chip   > optionalCameraSau = chipRepository.findById(id);
        if (optionalCameraSau.isPresent()) {
            model.addAttribute("chip1", optionalCameraSau.get());
            model.addAttribute("Chip", chipRepository.findAll());
            return "sanpham/chip";
        } else {
            return "redirect:/SanPham/Chips";
        }
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        chipRepository.deleteById(id);
        return "redirect:/SanPham/Chips";
    }
}
