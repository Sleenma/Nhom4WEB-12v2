package com.example.demo.sanpham.sanphamchitiet.anhsanpham.controller;

import com.example.demo.sanpham.sanphamchitiet.anhsanpham.entity.AnhSanPham;
import com.example.demo.sanpham.sanphamchitiet.anhsanpham.repository.AnhSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("SanPham/AnhSanPhams")
public class AnhSanPhamController {

    @Autowired
    private AnhSanPhamRepository anhSanPhamRepository;

    private static final String UPLOADED_FOLDER = "C:/Users/ASUS/Pictures/Screenshots/";

    @GetMapping
    public String hienThi(Model model) {
        model.addAttribute("anhSanPhams", anhSanPhamRepository.findAll());
        return "sanpham/sanphamchitiet/anhsanpham";
    }

    @PostMapping("/Add")
    public String handleFileUpload(@RequestParam("id") String id,
                                   @RequestParam("link") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/SanPham/AnhSanPhams/Add";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            AnhSanPham anhSanPham = new AnhSanPham();
            anhSanPham.setId(id.isEmpty() ? UUID.randomUUID() : UUID.fromString(id));
            anhSanPham.setLink("/images/" + file.getOriginalFilename());
            anhSanPham.setTenAnhSanPham(file.getOriginalFilename());
            anhSanPham.setTrangThai(1);

            anhSanPhamRepository.save(anhSanPham);

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/SanPham/AnhSanPhams";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<AnhSanPham> optionalAnhSanPham = anhSanPhamRepository.findById(id);
        if (optionalAnhSanPham.isPresent()) {
            model.addAttribute("asp", optionalAnhSanPham.get());
            model.addAttribute("anhSanPhams", anhSanPhamRepository.findAll());
            return "sanpham/sanphamchitiet/anhsanpham";
        } else {
            return "redirect:/SanPham/AnhSanPhams";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id) {
        anhSanPhamRepository.deleteById(id);
        return "redirect:/SanPham/AnhSanPhams";
    }
}
