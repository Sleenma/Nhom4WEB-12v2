package com.example.demo.sanpham.camerasau.service;

import com.example.demo.sanpham.camerasau.entity.CameraSau;
import com.example.demo.sanpham.camerasau.repository.CameraSauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CameraSauService {

    @Autowired
    private CameraSauRepository cameraSauRepository;

    public List<CameraSau> getAllCameras() {
        return cameraSauRepository.findAll();
    }

    public CameraSau getCameraById(UUID id) {
        return cameraSauRepository.findById(id).orElse(null);
    }

    public CameraSau createCamera(CameraSau cameraSau) {
        return cameraSauRepository.save(cameraSau);
    }

    public CameraSau updateCamera(UUID id, CameraSau cameraSau) {
        if (cameraSauRepository.existsById(id)) {
            cameraSau.setId(id); // Ensure the ID is set for update
            return cameraSauRepository.save(cameraSau);
        }
        return null; // Handle entity not found
    }

    public boolean deleteCamera(UUID id) {
        if (cameraSauRepository.existsById(id)) {
            cameraSauRepository.deleteById(id);
            return true;
        }
        return false; // Handle entity not found
    }
}
