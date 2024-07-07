package com.example.demo.hoadon.hoadon.controller;

import com.example.demo.hoadon.hoadon.entity.HoaDon;
import com.example.demo.hoadon.hoadon.repository.IHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hoa-don/")
public class HoaDonController {

    @Autowired
    private IHoaDon hoaDonRepository;

    @GetMapping("hien-thi")
    private String hienThi(Model model) {
        model.addAttribute("listHoaDon", hoaDonRepository.findAll());
        return "";
    }

    @PostMapping("add")
    private String add(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
        return "redirect:/hoa-don/hien-thi";
    }
}
