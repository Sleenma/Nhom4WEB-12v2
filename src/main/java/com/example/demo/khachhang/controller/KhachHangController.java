package com.example.demo.khachhang.controller;

import com.example.demo.khachhang.entity.DiaChi;
import com.example.demo.khachhang.entity.KhachHang;
import com.example.demo.khachhang.repository.DiaCHiRepository;
import com.example.demo.khachhang.repository.KhachHangRepository;
import com.example.demo.khachhang.serive.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private String hienThi(Model model,
                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        model.addAttribute("keyword", keyword);

        String keyword1 = "%" + keyword + "%";
        Pageable pageable = PageRequest.of(page, 10, Sort.by("ngayTao").descending());
        model.addAttribute("khachHang", khachHangRepository.timKhachHangTheoTenVaSDT(keyword1, keyword1,  pageable));
        return "khachhang/khachHang";
    }

//
//    @RequestMapping("/khachHang")
//    public String hienthitatca(Model model) {
//model.addAttribute("khachHang",khachHangRepository.findAllByDeletedTrue());
//        return "khachhang/khachHang";
//    }

    @GetMapping("/khachHang/viewadd")
    public String hienthiadd() {

        return "khachhang/addKhachHang";
    }

    @PostMapping("/khachHang/add")
    public String add(KhachHang khachHang,DiaChi diaChi) {
//        khachHang.setNgayTao(new Date()); // Hoặc LocalDateTime.now() nếu sử dụng Java 8+
//        diaChi.setNgayTao(new Date()); // Hoặc LocalDateTime.now() nếu sử dụng Java 8+
        khachHangRepository.save(khachHang);
        diaCHiRepository.save(diaChi);
        return "redirect:/khachHang";
    }
    @PostMapping("/khachHang/update")
    public String update(KhachHang khachHang,DiaChi diaChi) {
        khachHangRepository.save(khachHang);
        diaCHiRepository.save(diaChi);
        return "redirect:/khachHang";
    }
//@PostMapping("/khachHang/update")
//public String update(@ModelAttribute KhachHang khachHang, @ModelAttribute DiaChi diaChi, Model model) {
//    Optional<KhachHang> existingKhachHang = khachHangRepository.findById(khachHang.getId());
//    Optional<DiaChi> existingDiaChi = diaCHiRepository.findById(diaChi.getIdDC());
//
//    if (existingKhachHang.isPresent() && existingDiaChi.isPresent()) {
//        khachHangRepository.save(khachHang);
//        diaCHiRepository.save(diaChi);
//    } else {
//        // Thêm thông báo lỗi vào model nếu không tìm thấy khách hàng hoặc địa chỉ
//        model.addAttribute("error", "Không tìm thấy khách hàng hoặc địa chỉ để cập nhật");
//        return "redirect:/khachHang"; // Hoặc một trang khác để hiển thị lỗi
//    }
//
//    return "redirect:/khachHang";
//}

    @GetMapping("/khachHang/detail/{id}")
    public String detail(KhachHang khachHang, @PathVariable("id") UUID id, Model model) {
        model.addAttribute("listKH", khachHangRepository.findById(id).orElse(null));
        model.addAttribute("diaChi", diaCHiRepository.findById(id).orElse(null));
        model.addAttribute("diaChiList",diaCHiRepository.findAll());
        return "khachhang/updateKhachHang";
    }


    @GetMapping("/khachHang/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        khachHangService.removeKhachHang(id);
        return "redirect:/khachHang";
    }
//@GetMapping("/khachHang/findby")
//    public String findbyTenandSdt(KhachHang khachHang){
//        khachHangRepository.timKhachHangTheoTenVaSDT(khachHang.getTenKH(), khachHang.getSdt());
//    return "khachhang/khachHang";
//}

}

