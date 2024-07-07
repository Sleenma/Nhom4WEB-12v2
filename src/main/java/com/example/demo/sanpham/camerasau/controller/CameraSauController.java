package com.example.demo.sanpham.camerasau.controller;

import com.example.demo.sanpham.camerasau.entity.CameraSau;
import com.example.demo.sanpham.camerasau.service.CameraSauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/SanPham/CameraSaus")
public class CameraSauController {

    @Autowired
    private CameraSauService cameraSauService;

    @GetMapping("/list")
    public String getAllCameras(Model model) {
        List<CameraSau> cameras = cameraSauService.getAllCameras();
        model.addAttribute("cameras", cameras);
        return "sanpham/camerasau"; // Thymeleaf template name
    }
    @PostMapping("/save")
    public String saveOrUpdateCamera(@ModelAttribute("camera") CameraSau camera) {
        // Save or update the camera
        cameraSauService.createCamera(camera);
        return "redirect:/SanPham/CameraSaus/list";
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CameraSau> getCameraById(@PathVariable("id") UUID id) {
        CameraSau cameraSau = cameraSauService.getCameraById(id);
        if (cameraSau != null) {
            return ResponseEntity.ok(cameraSau);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CameraSau> createCamera(@RequestBody CameraSau cameraSau) {
        CameraSau createdCamera = cameraSauService.createCamera(cameraSau);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCamera);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CameraSau> updateCamera(@PathVariable("id") UUID id, @RequestBody CameraSau cameraSau) {
        CameraSau updatedCamera = cameraSauService.updateCamera(id, cameraSau);
        if (updatedCamera != null) {
            return ResponseEntity.ok(updatedCamera);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamera(@PathVariable("id") UUID id) {
        if (cameraSauService.deleteCamera(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
