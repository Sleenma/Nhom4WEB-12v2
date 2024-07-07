package com.example.demo.sanpham.dophangiai.repository;

import com.example.demo.sanpham.dophangiai.entity.DoPhanGiai;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoPhanGiaiRepository extends JpaRepository<DoPhanGiai, UUID> {
}
