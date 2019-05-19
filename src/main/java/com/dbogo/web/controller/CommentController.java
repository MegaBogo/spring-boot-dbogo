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

    @PostMapping("/{seq}")
    public ResponseEntity<List<Comment>> add(
            @PathVariable("seq") Long seq,
            @RequestBody Comment comment) {


        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setSeq(seq);
        commentRepo.save(comment);


        return new ResponseEntity<>(getListByFreeBoard(freeBoard), HttpStatus.CREATED);

    }

    private List<Comment> getListByFreeBoard(FreeBoard freeBoard) throws RuntimeException {
        log.info("getListByFreeBoard .... " + freeBoard);

        return commentRepo.getCommentOfFreeBord(freeBoard);
    }




}