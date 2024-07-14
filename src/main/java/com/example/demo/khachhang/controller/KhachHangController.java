package com.example.demo.khachhang.controller;

import com.example.demo.khachhang.entity.DiaChi;
import com.example.demo.khachhang.entity.KhachHang;
import com.example.demo.khachhang.repository.DiaCHiRepository;
import com.example.demo.khachhang.repository.KhachHangRepository;
import com.example.demo.khachhang.response.KhachHangResponse;
import com.example.demo.khachhang.serive.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class KhachHangController {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private DiaCHiRepository diaCHiRepository;

    @GetMapping("/khachHang")
    public String hienThi(Model model,
                          @RequestParam(name = "page", defaultValue = "0") Integer page,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword,
                          @RequestParam(name = "deleted", defaultValue = "true") Boolean deleted) {
        model.addAttribute("keyword", keyword);
        KhachHang khachHangg = new KhachHang();
        String keyword1 = "%" + keyword + "%";

        Pageable pageable = PageRequest.of(page, 10, Sort.by("ngayTao").descending());
        Page<KhachHangResponse> khachHangPage = khachHangRepository.timKhachHangTheoTenVaSDT(keyword1, keyword1, keyword1, deleted, pageable);
        khachHangg.getDiaChiList().add(new DiaChi());
        model.addAttribute("khachHang", khachHangPage);
        return "khachhang/khachHang";
    }
//    @GetMapping("/khachHang")
//    private String hienThi(Model model,
//                           @RequestParam(name = "page", defaultValue = "0") Integer page,
//                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
//        model.addAttribute("keyword", keyword);
//
//        String keyword1 = "%" + keyword + "%";
//
//        Pageable pageable = PageRequest.of(page, 10, Sort.by("ngayTao").descending());
//        model.addAttribute("khachHang", khachHangRepository.timKhachHangTheoTenVaSDT(keyword1, keyword1,keyword1,  pageable));
//        return "khachhang/khachHang";
//    }
//    @RequestMapping("/khachHang")
//    public String hienthitatca(Model model) {
//model.addAttribute("khachHang",khachHangRepository.findAllByDeletedTrue());
//        return "khachhang/khachHang";
//    }

    @GetMapping("/khachHang/viewadd")
    public String hienthiadd() {
        return "khachhang/addKhachHang";
    }

//    @PostMapping("/khachHang/add")
//    public String add(KhachHang khachHang,DiaChi diaChi) {
////        khachHang.setNgayTao(new Date()); // Hoặc LocalDateTime.now() nếu sử dụng Java 8+
////        diaChi.setNgayTao(new Date()); // Hoặc LocalDateTime.now() nếu sử dụng J
//
//        for (DiaChi diaChi1 : khachHang.getDiaChiList()) {
//            if (diaChi1.getIdDC() == null) {
//                diaChi1.setIdDC(UUID.randomUUID());
//            }
//            diaChi.setKhachHang(khachHang); // Liên kết DiaChi với KhachHang
//        }
//        khachHangRepository.save(khachHang);
////        diaChi.setIdDC(new UUID());
//        diaCHiRepository.save(diaChi)                                   ;
//        return "redirect:/khachHang";
//    }

    @PostMapping("/khachHang/add")
    public String addKhachHang(@ModelAttribute KhachHang khachHang, Model model) {
        for (DiaChi diaChi : khachHang.getDiaChiList()) {
            diaChi.setKhachHang(khachHang); // Thiết lập lại quan hệ
//            khachHang.getDiaChiList().add(diaChi);
        }
            khachHangRepository.save(khachHang);
            return "redirect:/khachHang";
    }

    @PostMapping("/khachHang/update")
    public String updateKhachHang(@ModelAttribute KhachHang khachHang, Model model) {
        for (DiaChi diaChi : khachHang.getDiaChiList()) {
            diaChi.setKhachHang(khachHang); // Thiết lập lại quan hệ
//            khachHang.getDiaChiList().add(diaChi);
        }
        khachHangRepository.save(khachHang);
        return "redirect:/khachHang";
    }

//    @PostMapping("/khachHang/update")
//    public String update(@ModelAttribute KhachHang khachHang,DiaChi diaChi) {
//
//        khachHangRepository.save(khachHang);
//        diaCHiRepository.save(diaChi);
//        return "redirect:/khachHang";
//    }

    @GetMapping("/khachHang/detail/{id}")
    public String detail(KhachHang khachHang, @PathVariable("id") UUID id, Model model) {
        model.addAttribute("listKH", khachHangRepository.findById(id).orElse(null));
//        model.addAttribute("diaChi", khachHangRepository.findById(id).orElse(null));
        model.addAttribute("diaChiList",khachHangRepository.findById(id).orElse(null).getDiaChiList());
        return "khachhang/updateKhachHang";
    }

    @GetMapping("/khachHang/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        khachHangService.removeKhachHang(id);
        return "redirect:/khachHang";
    }

}