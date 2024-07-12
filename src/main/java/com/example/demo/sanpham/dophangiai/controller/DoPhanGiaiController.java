package com.example.demo.sanpham.dophangiai.controller;


import com.example.demo.sanpham.danhmuc.entity.DanhMuc;
import com.example.demo.sanpham.dophangiai.entity.DoPhanGiai;
import com.example.demo.sanpham.dophangiai.repository.DoPhanGiaiRepository;
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
@RequestMapping("/SanPham/DoPhanGiais")
public class DoPhanGiaiController {

    @Autowired
    private DoPhanGiaiRepository doPhanGiaiRepository;

    @GetMapping
    public String listDoPhanGiai(Model model) {
        model.addAttribute("doPhanGiais", doPhanGiaiRepository.findAll());
        model.addAttribute("doPhanGiai", new DoPhanGiai()); // for form binding
        return "sanpham/dophangiai";
    }

    @PostMapping("/save")
    public String saveOrUpdate(@ModelAttribute("doPhanGiai") DoPhanGiai doPhanGiai) {
        doPhanGiaiRepository.save(doPhanGiai);
        return "redirect:/SanPham/DoPhanGiais";
    }

    @GetMapping("/edit/{id}")
    public String editDoPhanGiai(@PathVariable("id") UUID id, Model model) {
        Optional<DoPhanGiai> optionalDoPhanGiai = doPhanGiaiRepository.findById(id);
        optionalDoPhanGiai.ifPresent(doPhanGiai -> model.addAttribute("doPhanGiai", doPhanGiai));
        model.addAttribute("doPhanGiais", doPhanGiaiRepository.findAll());
        return "sanpham/dophangiai";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoPhanGiai(@PathVariable("id") UUID id) {
        doPhanGiaiRepository.deleteById(id);
        return "redirect:/SanPham/DoPhanGiais";
    }

}
