package com.example.demo.phieugiamgia.repository;

import com.example.demo.phieugiamgia.entity.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPhieuGiamGia extends JpaRepository<PhieuGiamGia, UUID> {
}
