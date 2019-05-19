package com.dbogo.web.controller;


import com.dbogo.web.domain.FreeBoard;
import com.dbogo.web.persistence.FreeBoardRepository;
import com.dbogo.web.storage.StorageService;
import com.dbogo.web.vo.PageMaker;
import com.dbogo.web.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * project
 */
@Controller
@RequestMapping("/freeBoard/")
@Log
public class FreeBoardController {

    private final StorageService storageService;

    @Autowired
    public FreeBoardController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private FreeBoardRepository repo;

    @GetMapping("/list")
    public void list(Model model
                     , PageVO vo) {

        Pageable page = vo.makePageable(0, "seq");
        Page<FreeBoard> result = repo.findAll(repo.makePredicate(vo.getType(), vo.getKeyword()), page);

        log.info("" + page);
        log.info("" + result);

        model.addAttribute("result", new PageMaker(result));
    }

    @GetMapping("/register")
    public void register(@ModelAttribute("vo")FreeBoard vo) {
        log.info("register get");
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("vo")FreeBoard vo
                           , @RequestParam("file") MultipartFile file
                           , RedirectAttributes rttr) {


        storageService.store(file);

        log.info("register post");
        log.info(""+vo);

        repo.save(vo);
        rttr.addFlashAttribute("msg", "success");

        //여러 번 게시물 등록 방지 :  Post-Redirect-Get방식
        return "redirect:/freeBoard/list";

    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id
                    , Model model) {

        log.info("MODIFY SEQ " + id);

        repo.findById(id).ifPresent(freeBoard -> model.addAttribute("vo", freeBoard));

        return "freeBoard/modify";
    }

    @PostMapping("/modify")
    public String modify(FreeBoard board, RedirectAttributes rttr) {
        log.info("modify post");
        log.info(""+board);

        repo.findById(board.getSeq()).ifPresent( origin -> {

            origin.setTitle(board.getTitle());
            origin.setContent(board.getContent());

            repo.save(origin);
            rttr.addFlashAttribute("msg", "success");
        });

        return "redirect:/freeBoard/view/"+board.getSeq();

    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id
                    , Model model) {

        log.info("id" + id);

        repo.findById(id).ifPresent(freeBoard -> model.addAttribute("vo", freeBoard));

        return "freeBoard/view";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        log.info("DELETE SEQ " + id);

        repo.deleteById(id);

        return "redirect:/freeBoard/list/";
    }

}