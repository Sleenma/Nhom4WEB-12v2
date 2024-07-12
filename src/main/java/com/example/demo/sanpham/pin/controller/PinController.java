package com.example.demo.sanpham.pin.controller;

import com.example.demo.sanpham.pin.entity.Pin;
import com.example.demo.sanpham.pin.repository.PinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/Pins")
public class PinController {

    @Autowired
    private PinRepository pinRepository;

    @GetMapping
    public String listPins(Model model) {
        model.addAttribute("pins", pinRepository.findAll());
        model.addAttribute("pin", new Pin()); // for form binding
        return "sanpham/pin";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("pin") Pin pin) {
        pinRepository.save(pin);
        return "redirect:/SanPham/Pins";
    }

    @GetMapping("/edit/{id}")
    public String editPin(@PathVariable("id") UUID id, Model model) {
        Optional<Pin> optionalPin = pinRepository.findById(id);
        optionalPin.ifPresent(pin -> model.addAttribute("pin", pin));
        model.addAttribute("pins", pinRepository.findAll());
        return "sanpham/pin";
    }

    @GetMapping("/delete/{id}")
    public String deletePin(@PathVariable("id") UUID id) {
        pinRepository.deleteById(id);
        return "redirect:/SanPham/Pins";
    }
}
