package com.example.demo.hoadon.hoadonchitiet.controller;

import com.example.demo.hoadon.hoadonchitiet.entity.HoaDonChiTiet;
import com.example.demo.hoadon.hoadonchitiet.repository.IHoaDonChiTiet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/hoa-don-chi-tiet/")
public class HoaDonChiTietController {

    @Autowired
    private IHoaDonChiTiet hoaDonChiTietRepository;

    @GetMapping("hien-thi")
    private String hienThi(Model model,
                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        model.addAttribute("keyword", keyword);
        String keyword1 = "%" + keyword + "%";

        Pageable pageable = PageRequest.of(page, 5);
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietRepository.search(keyword1, pageable));
        return "hoadon/hoadonchitiet/views";
    }

    @PostMapping("add")
    private String add(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
        return "redirect:/hoa-don-chi-tiet/hien-thi";
    }

    @PostMapping("update")
    private String update(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
        return "redirect:/hoa-don-chi-tiet/hien-thi";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("hoaDonChiTiet", hoaDonChiTietRepository.findById(id).get());
        model.addAttribute("listHoaDonChiTiet", hoaDonChiTietRepository.findAll());
        return "hoadon/hoadonchitiet/views";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        hoaDonChiTietRepository.deleteById(id);
        return "redirect:/hoa-don-chi-tiet/hien-thi";
    }
}
