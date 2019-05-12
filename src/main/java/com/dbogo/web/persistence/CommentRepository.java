package com.dbogo.web.persistence;

import com.dbogo.web.domain.Comment;
import com.dbogo.web.domain.FreeBoard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {


    @Query("SELECT r FROM Comment r WHERE r.board = ?1 " + " AND r.seq > 0 ORDER BY r.seq ASC")
    public List<Comment> getCommentOfFreeBord(FreeBoard board);






}
