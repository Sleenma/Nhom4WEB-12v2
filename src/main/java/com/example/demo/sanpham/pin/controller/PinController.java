package com.example.demo.sanpham.pin.controller;


import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import com.example.demo.sanpham.pin.entity.Pin;
import com.example.demo.sanpham.pin.repository.PinRepository;
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
@RequestMapping("SanPham/Pins")
public class PinController {
    @Autowired
    private PinRepository pinRepository;

    @GetMapping

    public String hienThi(Model model) {

        model.addAttribute("Pin", pinRepository.findAll());

        return "sanpham/pin";
    }

    @PostMapping("/Add")
    public String add(Pin pin) {
        pinRepository.save(pin);
        return "redirect:/SanPham/Pins";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<Pin> optionalCameraSau = pinRepository.findById(id);
        if (optionalCameraSau.isPresent()) {
            model.addAttribute("pn", optionalCameraSau.get());
            model.addAttribute("Pin", pinRepository.findAll());
            return "sanpham/pin";
        } else {
            return "redirect:/SanPham/Pins";
        }
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        pinRepository.deleteById(id);
        return "redirect:/SanPham/Pins";
    }
}
