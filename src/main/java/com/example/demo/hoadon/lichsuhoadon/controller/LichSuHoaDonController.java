package com.example.demo.hoadon.lichsuhoadon.controller;

import com.example.demo.hoadon.lichsuhoadon.entity.LichSuHoaDon;
import com.example.demo.hoadon.lichsuhoadon.repository.ILichSuHoaDon;
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
@RequestMapping("/lich-su-hoa-don/")
public class LichSuHoaDonController {

    @Autowired
    private ILichSuHoaDon lichSuHoaDonRepository;

    @GetMapping("hien-thi")
    private String hienThi(Model model,
                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        model.addAttribute("keyword", keyword);
        String keyword1 = "%" + keyword + "%";

        Pageable pageable = PageRequest.of(page, 5);
        model.addAttribute("listLichSuHoaDon", lichSuHoaDonRepository.search(keyword1, pageable));
        return "hoadon/lichsuhoadon/views";
    }

    @PostMapping("add")
    private String add(LichSuHoaDon lichSuHoaDon) {
        lichSuHoaDonRepository.save(lichSuHoaDon);
        return "redirect:/lich-su-hoa-don/hien-thi";
    }

    @PostMapping("update")
    private String update(LichSuHoaDon lichSuHoaDon) {
        lichSuHoaDonRepository.save(lichSuHoaDon);
        return "redirect:/lich-su-hoa-don/hien-thi";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("lichSuHoaDon", lichSuHoaDonRepository.findById(id).get());
        model.addAttribute("listLichSuHoaDon", lichSuHoaDonRepository.findAll());
        return "hoadon/lichsuhoadon/views";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        lichSuHoaDonRepository.deleteById(id);
        return "redirect:/lich-su-hoa-don/hien-thi";
    }
}
