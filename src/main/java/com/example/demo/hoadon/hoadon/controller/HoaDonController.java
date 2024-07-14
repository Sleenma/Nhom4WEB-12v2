package com.example.demo.hoadon.hoadon.controller;

import com.example.demo.hoadon.hoadon.entity.HoaDon;
import com.example.demo.hoadon.hoadon.repository.IHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/hoa-don/")
public class HoaDonController {

    @Autowired
    private IHoaDon hoaDonRepository;

    @GetMapping("hien-thi")
    public String hienThi(Model model,
                          @RequestParam(name = "page", defaultValue = "0") Integer page,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword,
                          @RequestParam(name = "type", defaultValue = "all") String type,
                          @RequestParam(name = "trangThai", defaultValue = "0") Integer trangThai) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);
        model.addAttribute("trangThai", trangThai);

        Pageable pageable = PageRequest.of(page, 5);
        String keyword1 = "%" + keyword + "%";
        Page<HoaDon> hoaDonPage;

        if ("all".equals(type)) {
            hoaDonPage = hoaDonRepository.findByKeyword(keyword1, pageable);
        } else {
            Integer loaiHoaDon = "offline".equals(type) ? 1 : 2;
            hoaDonPage = hoaDonRepository.search(keyword1, keyword1, keyword1, loaiHoaDon, trangThai, pageable);
        }

        model.addAttribute("listHoaDon", hoaDonPage);
        return "hoadon/viewhoadon/views";
    }

    // Phương thức xử lý xuất Excel
    @PostMapping("export-excel")
    private void exportExcel() {
        // Xử lý xuất Excel ở đây
    }

    @GetMapping("view-add")
    private String viewadd(Model model) {
            model.addAttribute("hoaDon", new HoaDon());
            return "hoadon/viewhoadon/add";
    }

    @PostMapping("add")
    private String add(HoaDon hoaDon,
                       @RequestParam("ngayThanhToan") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayThanhToan,
                       @RequestParam("ngayNhan") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayNhan,
                       @RequestParam("ngayTao") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngayTao,
                       @RequestParam("ngaySua") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngaySua) {
        hoaDon.setNgayThanhToan(ngayThanhToan);
        hoaDon.setNgayNhan(ngayNhan);
        hoaDon.setNgayTao(ngayTao);
        hoaDon.setNgaySua(ngaySua);
        hoaDonRepository.save(hoaDon);
        return "redirect:/hoa-don/hien-thi";
    }

    @PostMapping("update")
    private String update(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
        return "redirect:/hoa-don/hien-thi";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("hoaDon", hoaDonRepository.findById(id).orElse(null));
        model.addAttribute("listHoaDon", hoaDonRepository.findAll());
        return "hoadon/viewhoadon/add";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable("id") UUID id) {
        hoaDonRepository.deleteById(id);
        return "redirect:/hoa-don/hien-thi";
    }
}
