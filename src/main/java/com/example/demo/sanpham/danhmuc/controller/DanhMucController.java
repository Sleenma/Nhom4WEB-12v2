package com.example.demo.sanpham.danhmuc.controller;

import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import com.example.demo.sanpham.danhmuc.entity.DanhMuc;
import com.example.demo.sanpham.danhmuc.repository.DanhMucRepository;
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
@RequestMapping("/SanPham/DanhMucs1")
public class DanhMucController {
    @Autowired

    private DanhMucRepository danhMucRepository;

    @GetMapping
    public String hienThi(Model model){

        model.addAttribute("DanhMuc",danhMucRepository.findAll());

        return "sanpham/danhmuc";

    }
    @PostMapping("/Add")
    public String add(DanhMuc danhMuc) {
        danhMucRepository.save(danhMuc);
        return "redirect:/SanPham/DanhMucs1";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<DanhMuc> optionalCameraSau = danhMucRepository.findById(id);
        if (optionalCameraSau.isPresent()) {
            model.addAttribute("danhmuc1", optionalCameraSau.get());
            model.addAttribute("DanhMuc", danhMucRepository.findAll());
            return "sanpham/danhmuc";
        } else {
            return "redirect:/SanPham/DanhMucs1";
        }
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        danhMucRepository.deleteById(id);
        return "redirect:/SanPham/DanhMucs1";
    }

}
