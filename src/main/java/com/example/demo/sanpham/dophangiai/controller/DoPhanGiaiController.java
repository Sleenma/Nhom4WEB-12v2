package com.example.demo.sanpham.dophangiai.controller;


import com.example.demo.sanpham.danhmuc.entity.DanhMuc;
import com.example.demo.sanpham.dophangiai.entity.DoPhanGiai;
import com.example.demo.sanpham.dophangiai.repository.DoPhanGiaiRepository;
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
@RequestMapping("/SanPham/DoPhanGiais")
public class DoPhanGiaiController {

    @Autowired
    private DoPhanGiaiRepository doPhanGiaiRepository;

    @GetMapping
    public String hienThi(Model model){
        model.addAttribute("DoPhanGiai",doPhanGiaiRepository.findAll());
        return "sanpham/dophangiai";
    }

    @PostMapping("/Add")
    public String add(DoPhanGiai doPhanGiai) {
        doPhanGiaiRepository.save(doPhanGiai);
        return "redirect:/SanPham/DoPhanGiais";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<DoPhanGiai> optionalCameraSau = doPhanGiaiRepository.findById(id);
        if (optionalCameraSau.isPresent()) {
            model.addAttribute("DoPhanGiai1", optionalCameraSau.get());
            model.addAttribute("DoPhanGiai", doPhanGiaiRepository.findAll());
            return "sanpham/dophangiai";
        } else {
            return "redirect:/SanPham/DoPhanGiais";
        }
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        doPhanGiaiRepository.deleteById(id);
        return "redirect:/SanPham/DoPhanGiais";
    }
}
