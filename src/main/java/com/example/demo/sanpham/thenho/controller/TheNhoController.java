package com.example.demo.sanpham.thenho.controller;

import com.example.demo.sanpham.thenho.entity.TheNho;
import com.example.demo.sanpham.thenho.repository.TheNhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/TheNhos")
public class TheNhoController {

    @Autowired
    private TheNhoRepository theNhoRepository;

    @GetMapping
    public String listTheNhos(Model model) {
        model.addAttribute("theNhos", theNhoRepository.findAll());
        model.addAttribute("theNho", new TheNho()); // for form binding
        return "sanpham/thenho";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("theNho") TheNho theNho) {
        theNhoRepository.save(theNho);
        return "redirect:/SanPham/TheNhos";
    }

    @GetMapping("/edit/{id}")
    public String editTheNho(@PathVariable("id") UUID id, Model model) {
        Optional<TheNho> optionalTheNho = theNhoRepository.findById(id);
        optionalTheNho.ifPresent(theNho -> model.addAttribute("theNho", theNho));
        model.addAttribute("theNhos", theNhoRepository.findAll());
        return "sanpham/thenho";
    }

    @GetMapping("/delete/{id}")
    public String deleteTheNho(@PathVariable("id") UUID id) {
        theNhoRepository.deleteById(id);
        return "redirect:/SanPham/TheNhos";
    }
}
