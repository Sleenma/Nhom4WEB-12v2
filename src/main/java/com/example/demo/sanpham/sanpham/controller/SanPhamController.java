    package com.example.demo.sanpham.sanpham.controller;

    import com.example.demo.sanpham.camerasau.entity.CameraSau;
    import com.example.demo.sanpham.camerasau.repository.CameraSauRepository;
    import com.example.demo.sanpham.cameratruoc.entity.CameraTruoc;
    import com.example.demo.sanpham.cameratruoc.repository.CameraTruocRepository;
    import com.example.demo.sanpham.chip.entity.Chip;
    import com.example.demo.sanpham.chip.repository.ChipRepository;
    import com.example.demo.sanpham.danhmuc.entity.DanhMuc;
    import com.example.demo.sanpham.danhmuc.repository.DanhMucRepository;
    import com.example.demo.sanpham.dophangiai.entity.DoPhanGiai;
    import com.example.demo.sanpham.dophangiai.repository.DoPhanGiaiRepository;
    import com.example.demo.sanpham.hang.entity.Hang;
    import com.example.demo.sanpham.hang.repository.HangRepository;
    import com.example.demo.sanpham.hedieuhanh.entity.HeDieuHanh;
    import com.example.demo.sanpham.hedieuhanh.repository.HeDieuHanhRepository;
    import com.example.demo.sanpham.loaisac.entity.LoaiSac;
    import com.example.demo.sanpham.loaisac.repository.LoaiSacRepository;
    import com.example.demo.sanpham.pin.entity.Pin;
    import com.example.demo.sanpham.pin.repository.PinRepository;
    import com.example.demo.sanpham.sanpham.entity.SanPham;
    import com.example.demo.sanpham.sanpham.repository.SanPhamRepository;
    import com.example.demo.sanpham.sanpham.respose.SanPhamService;
    import com.example.demo.sanpham.thenho.entity.TheNho;
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

        @Autowired
        private PinRepository pinRepository;

        @Autowired

        private SanPhamService sanPhamService;

        @GetMapping
        public String listSanPhams(Model model,
                                   @RequestParam(name = "page",defaultValue = "0") int pageNo,
                                   @RequestParam(name = "limit",defaultValue = "5") int pageSize,
                                   @RequestParam(name = "keyword",defaultValue = "") String keyword) {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            String k = "%" + keyword + "%";

            Page<SanPham> ds = this.sanPhamRepository.findSanPhamByMaSanPhamLikeOrTenSanPhamLike(k, k, pageable);
            Long countIdImeiWhereDeletedIsZero = sanPhamRepository.countIdImeiWhereDeletedIsZero();

            // Add attributes to the model
            model.addAttribute("sanPhams", ds);
            model.addAttribute("sanPhamList", sanPhamRepository.findAll());
            model.addAttribute("keyword", keyword);
            model.addAttribute("sanPham", new SanPham()); // for form binding
            model.addAttribute("cameraTruocList", cameraTruocRepository.findAll());
            model.addAttribute("cameraSauList", cameraSauRepository.findAll());
            model.addAttribute("chipList", chipRepository.findAll());
            model.addAttribute("danhMucList", danhMucRepository.findAll());
            model.addAttribute("doPhanGiaiList", doPhanGiaiRepository.findAll());
            model.addAttribute("hangList", hangRepository.findAll());
            model.addAttribute("loaiSacList", loaiSacRepository.findAll());
            model.addAttribute("theNhoList", theNhoRepository.findAll());
            model.addAttribute("heDieuHanhList", heDieuHanhRepository.findAll());
            model.addAttribute("pinList", pinRepository.findAll());
            model.addAttribute("countIdImeiWhereDeletedIsZero", countIdImeiWhereDeletedIsZero);
            return "sanpham/sanpham";
        }

//        @GetMapping
//        public String listSanPhams(Model model,
//                                   @RequestParam(name = "page", defaultValue = "0") int pageNo,
//                                   @RequestParam(name = "limit", defaultValue = "5") int pageSize,
//                                   @RequestParam(name = "keyword", defaultValue = "") String keyword,
//                                   @RequestParam(name = "maDanhMuc", defaultValue = "") String maDanhMuc) {
//            Pageable pageable = PageRequest.of(pageNo, pageSize);
//            String searchKeyword = "%" + keyword + "%";
//
//            Page<SanPham> ds;
//            if (maDanhMuc.isEmpty()) {
//                ds = sanPhamService.searchSanPham(keyword, maDanhMuc, pageNo, pageSize);
//            } else {
//                ds = sanPhamService.searchSanPham(keyword, maDanhMuc, pageNo, pageSize);
//            }
//
//            model.addAttribute("sanPhams", ds);
//            model.addAttribute("keyword", keyword);
//            model.addAttribute("maDanhMuc",danhMucRepository.findAll());
//            model.addAttribute("sanPham", new SanPham()); // for form binding
//            return "sanpham/sanpham";
//        }

        @GetMapping("/viewAdd")
        public String viewAdd(Model model) {
            model.addAttribute("danhMuc", new DanhMuc());
            model.addAttribute("hang", new Hang());
            model.addAttribute("heDieuHanh", new HeDieuHanh());
            model.addAttribute("chip", new Chip());
            model.addAttribute("loaiSac", new LoaiSac());
            model.addAttribute("theNho", new TheNho());
            model.addAttribute("cameraTruoc", new CameraTruoc());
            model.addAttribute("cameraSau", new CameraSau());
            model.addAttribute("doPhanGiai", new DoPhanGiai());
            model.addAttribute("pin", new Pin());

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
            model.addAttribute("pinList", pinRepository.findAll());
            return "sanpham/addSanPham";
        }

        @PostMapping("/save")
        public String AddSP(@ModelAttribute("sanPham") SanPham sanPham) {
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

        @PostMapping("/DanhMucs/save")
        public String hangMucAdd(@ModelAttribute("danhMuc") DanhMuc danhMuc) {
            danhMucRepository.save(danhMuc);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }
        @PostMapping("/Hangs/save")
        public String hangAdd(@ModelAttribute("hang") Hang hang) {
            hangRepository.save(hang);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }
        @PostMapping("/HeDieuHanhs/save")
        public String heDieuhanhAdd(@ModelAttribute("heDieuHanh") HeDieuHanh heDieuHanh) {
            heDieuHanhRepository.save(heDieuHanh);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }
        @PostMapping("/Chips/save")
        public String chipAdd(@ModelAttribute("chip") Chip chip) {
            chipRepository.save(chip);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }
        @PostMapping("/LoaiSacs/save")
        public String loaiSacAdd(@ModelAttribute("loaiSac") LoaiSac loaiSac) {
            loaiSacRepository.save(loaiSac);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }
        @PostMapping("/TheNhos/save")
        public String theNhoAdd(@ModelAttribute("theNho") TheNho theNho) {
            theNhoRepository.save(theNho);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }
        @PostMapping("/CameraTruocs/save")
        public String cameraTruocAdd(@ModelAttribute("cameraTruoc") CameraTruoc cameraTruoc) {
            cameraTruocRepository.save(cameraTruoc);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }
        @PostMapping("/DoPhanGiais/save")
        public String doPhanGiaiAdd(@ModelAttribute("doPhanGiai") DoPhanGiai doPhanGiai) {
            doPhanGiaiRepository.save(doPhanGiai);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }
        @PostMapping("/Pins/save")
        public String pinAdd(@ModelAttribute("pin") Pin pin) {
            pinRepository.save(pin);
            return "redirect:/SanPham/SanPhams/viewAdd";
        }


    }
