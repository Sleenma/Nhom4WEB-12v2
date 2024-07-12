    package com.example.demo.sanpham.sanpham.controller;

    import com.example.demo.sanpham.camerasau.repository.CameraSauRepository;
    import com.example.demo.sanpham.cameratruoc.repository.CameraTruocRepository;
    import com.example.demo.sanpham.chip.repository.ChipRepository;
    import com.example.demo.sanpham.danhmuc.repository.DanhMucRepository;
    import com.example.demo.sanpham.dophangiai.repository.DoPhanGiaiRepository;
    import com.example.demo.sanpham.hang.repository.HangRepository;
    import com.example.demo.sanpham.hedieuhanh.repository.HeDieuHanhRepository;
    import com.example.demo.sanpham.loaisac.repository.LoaiSacRepository;
    import com.example.demo.sanpham.sanpham.entity.SanPham;
    import com.example.demo.sanpham.sanpham.repository.SanPhamRepository;
    import com.example.demo.sanpham.thenho.repository.TheNhoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import java.util.Optional;
    import java.util.UUID;

    @Controller
    @RequestMapping("/SanPham/SanPhams")
    public class SanPhamController {

        @Autowired
        private SanPhamRepository sanPhamRepository;

        @Autowired
        private CameraTruocRepository cameraTruocRepository;

        @Autowired
        private CameraSauRepository cameraSauRepository;

        @Autowired
        private ChipRepository chipRepository;

        @Autowired
        private DanhMucRepository danhMucRepository;

        @Autowired
        private DoPhanGiaiRepository doPhanGiaiRepository;

        @Autowired
        private HangRepository hangRepository;

        @Autowired
        private LoaiSacRepository loaiSacRepository;

        @Autowired
        private TheNhoRepository theNhoRepository;

        @Autowired
        private HeDieuHanhRepository heDieuHanhRepository;


        @GetMapping
        public String listSanPhams(Model model,
                                   @RequestParam(name = "page",defaultValue = "0") int pageNo,
                                   @RequestParam(name = "limit",defaultValue = "5") int pageSize,
                                   @RequestParam(name = "keyword",defaultValue = "") String keyword) {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            String k = "%" + keyword + "%";

            Page<SanPham> ds = this.sanPhamRepository.findSanPhamByMaSanPhamLikeOrTenSanPhamLike(k, k, pageable);
            model.addAttribute("sanPhams", ds);
            model.addAttribute("keyword",keyword);
            model.addAttribute("sanPham", new SanPham()); // for form binding
            return "sanpham/sanpham";
        }
        @GetMapping("/viewAdd")
        public String viewAdd(Model model) {
            model.addAttribute("sanPhams", sanPhamRepository.findAll());
            model.addAttribute("sanPham", new SanPham());
            model.addAttribute("cameraTruocList", cameraTruocRepository.findAll());
            model.addAttribute("cameraSauList", cameraSauRepository.findAll());
            model.addAttribute("chipList", chipRepository.findAll());
            model.addAttribute("danhMucList", danhMucRepository.findAll());
            model.addAttribute("doPhanGiaiList", doPhanGiaiRepository.findAll());
            model.addAttribute("hangList", hangRepository.findAll());
            model.addAttribute("loaiSacList", loaiSacRepository.findAll());
            model.addAttribute("theNhoList", theNhoRepository.findAll());
            model.addAttribute("heDieuHanhList", heDieuHanhRepository.findAll());
            return "sanpham/addSanPham";
        }

        @PostMapping("/save")
        public String saveOrUpdate(@ModelAttribute("sanPham") SanPham sanPham) {
            sanPhamRepository.save(sanPham);
            return "redirect:/SanPham/SanPhams";
        }

        @GetMapping("/edit/{id}")
        public String editSanPham(@PathVariable("id") UUID id, Model model) {
            Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);
            optionalSanPham.ifPresent(sanPham -> model.addAttribute("sanPham", sanPham));
            model.addAttribute("sanPhams", sanPhamRepository.findAll());
            return "sanpham/sanpham";
        }

        @GetMapping("/delete/{id}")
        public String deleteSanPham(@PathVariable("id") UUID id) {
            sanPhamRepository.deleteById(id);
            return "redirect:/SanPham/SanPhams";
        }
    }
