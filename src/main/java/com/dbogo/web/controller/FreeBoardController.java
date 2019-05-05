package com.dbogo.web.controller;


import com.dbogo.web.domain.FreeBoard;
import com.dbogo.web.persistence.FreeBoardRepository;
import com.dbogo.web.vo.PageMaker;
import com.dbogo.web.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * project
 */
@Controller
@RequestMapping("/freeBoard/")
@Log
public class FreeBoardController {
    @Autowired
    private FreeBoardRepository repo;

    @GetMapping("/list")
    public void list(Model model
                     , PageVO vo) {

        Pageable page = vo.makePageable(0, "seq");
        Page<FreeBoard> result = repo.findAll(repo.makePredicate(null, null), page);

        log.info("" + page);
        log.info("" + result);

        model.addAttribute("result", new PageMaker(result));
    }
}