package com.example.demo.sanpham.thenho.controller;

import com.example.demo.sanpham.dophangiai.repository.DoPhanGiaiRepository;
import com.example.demo.sanpham.pin.entity.Pin;
import com.example.demo.sanpham.thenho.entity.TheNho;
import com.example.demo.sanpham.thenho.repository.TheNhoRepository;
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
@RequestMapping("SanPham/TheNhos")
public class TheNhoController {

    @Autowired
    private TheNhoRepository theNhoRepository;

    @GetMapping
    public String hienThi(Model model){
        model.addAttribute("TheNho",theNhoRepository.findAll());
        return "sanpham/thenho";
    }
    @PostMapping("/Add")
    public String add(TheNho pin) {
        theNhoRepository.save(pin);
        return "redirect:/SanPham/TheNhos";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<TheNho> optionalCameraSau = theNhoRepository.findById(id);
        if (optionalCameraSau.isPresent()) {
            model.addAttribute("tn", optionalCameraSau.get());
            model.addAttribute("TheNho", theNhoRepository.findAll());
            return "sanpham/thenho";
        } else {
            return "redirect:/SanPham/TheNhos";
        }
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        theNhoRepository.deleteById(id);
        return "redirect:/SanPham/TheNhos";
    }
}
