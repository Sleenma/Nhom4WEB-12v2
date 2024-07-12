package com.example.demo.sanpham.chip.repository;

import com.example.demo.sanpham.chip.entity.Chip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChipRepository extends JpaRepository<Chip, UUID> {

}
