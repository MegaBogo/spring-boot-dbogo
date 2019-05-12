package com.dbogo.web.controller;

import com.dbogo.web.domain.Comment;
import com.dbogo.web.domain.FreeBoard;
import com.dbogo.web.persistence.CommentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@Log
@RequestMapping("/comment/*")
public class CommentController {

    @Autowired
    private CommentRepository commentRepo;

    @PostMapping("/{bno}")
    public ResponseEntity<List<Comment>> add(
            @PathVariable("bno") Long bno,
            @RequestBody Comment comment) {


        FreeBoard board = new FreeBoard();
        board.setSeq(bno);
        commentRepo.save(comment);


        return new ResponseEntity<>(getListByFreeBoard(board), HttpStatus.CREATED);

    }

    private List<Comment> getListByFreeBoard(FreeBoard board) throws RuntimeException {
        log.info("getListByFreeBoard .... " + board);

        return commentRepo.getCommentOfFreeBord(board);
    }




}