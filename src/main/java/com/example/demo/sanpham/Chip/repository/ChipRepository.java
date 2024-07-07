package com.example.demo.sanpham.Chip.repository;

import com.example.demo.sanpham.Chip.entity.Chip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChipRepository extends JpaRepository<Chip, UUID> {

}
