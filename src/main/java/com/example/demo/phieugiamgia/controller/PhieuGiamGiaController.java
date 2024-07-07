package com.example.demo.phieugiamgia.controller;

import com.example.demo.phieugiamgia.entity.PhieuGiamGia;
import com.example.demo.phieugiamgia.repository.IPhieuGiamGia;
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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("pgg")
public class PhieuGiamGiaController {
    @Autowired
    IPhieuGiamGia pggRepo;

    @GetMapping("index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int pageNo,
                        @RequestParam(name = "limit",defaultValue = "5") int pageSize,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        String k = "%" + keyword + "%";
        Page<PhieuGiamGia> ds = this.pggRepo.findPhieuGiamGiaByMaLikeOrTenLike(k, k, pageable);
//        Page<PhieuGiamGia> ds = this.pggRepo.findAll(pageable);
        model.addAttribute("data", ds);
        model.addAttribute("keyword",keyword);
        return "phieugiamgia/index";
    }

    @RequestMapping("create")
    public String create(){
        return "phieugiamgia/create";
    }

    @PostMapping("store")
    public String store(
            Model model, PhieuGiamGia pgg
    ){
            this.pggRepo.save(pgg);
            return "redirect:/pgg/index";

    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") UUID id){
        this.pggRepo.deleteById(id);
        return "redirect:/pgg/index";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") UUID id, Model model){
        PhieuGiamGia ms = this.pggRepo.findById(id).get();
        model.addAttribute("data",ms);
        return "phieugiamgia/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            Model model, PhieuGiamGia pgg
    ){
        this.pggRepo.save(pgg);
        return "redirect:/pgg/index";
    }

}
