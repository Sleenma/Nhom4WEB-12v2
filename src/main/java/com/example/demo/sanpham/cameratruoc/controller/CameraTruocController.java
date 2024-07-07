package com.example.demo.sanpham.cameratruoc.controller;

import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import com.example.demo.sanpham.cameratruoc.repository.CameraTruocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/CameraTruocs")
public class CameraTruocController {

    @Autowired
    private CameraTruocRepository cameraTruocRepository;

    @GetMapping
    public String listCameraTruoc(Model model) {
        model.addAttribute("cameraTruocs", cameraTruocRepository.findAll());
        model.addAttribute("cameraTruoc", new CameraTruoc()); // for form binding
        return "sanpham/cameratruoc";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("cameraTruoc") CameraTruoc cameraTruoc) {
        cameraTruocRepository.save(cameraTruoc);
        return "redirect:/SanPham/CameraTruocs";
    }

    @GetMapping("/edit/{id}")
    public String editCameraTruoc(@PathVariable("id") UUID id, Model model) {
        Optional<CameraTruoc> optionalCameraTruoc = cameraTruocRepository.findById(id);
        optionalCameraTruoc.ifPresent(cameraTruoc -> model.addAttribute("cameraTruoc", cameraTruoc));
        model.addAttribute("cameraTruocs", cameraTruocRepository.findAll());
        return "sanpham/cameratruoc";
    }

    @GetMapping("/delete/{id}")
    public String deleteCameraTruoc(@PathVariable("id") UUID id) {
        cameraTruocRepository.deleteById(id);
        return "redirect:/SanPham/CameraTruocs";
    }
}
