package com.example.demo.sanpham.hang.controller;

import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import com.example.demo.sanpham.hang.entity.Hang;
import com.example.demo.sanpham.hang.repository.HangRepository;
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
@RequestMapping("/SanPham/Hangs")
public class HangController {


    @Autowired
    private HangRepository hangRepository;

    @GetMapping
    public String hienThi(Model model){
        model.addAttribute("Hang",hangRepository.findAll());
        return "sanpham/hang";
    }
    @PostMapping("/Add")
    public String add(Hang hang ){
        hangRepository.save(hang);
        return "redirect:/SanPham/Hangs";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<Hang> optionalCameraSau = hangRepository.findById(id);
        if (optionalCameraSau.isPresent()) {
            model.addAttribute("hang1", optionalCameraSau.get());
            model.addAttribute("Hang", hangRepository.findAll());
            return "sanpham/Hangs";
        } else {
            return "redirect:/SanPham/Hangs";
        }
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        hangRepository.deleteById(id);
        return "redirect:/SanPham/Hangs";
    }

}
