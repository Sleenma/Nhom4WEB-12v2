package com.example.demo.sanpham.cameratruoc.repository;


import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CameraTruocRepository extends JpaRepository<CameraTruoc, UUID> {

}
