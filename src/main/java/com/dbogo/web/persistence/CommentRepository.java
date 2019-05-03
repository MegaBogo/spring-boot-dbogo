package com.dbogo.web.persistence;

import com.dbogo.web.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
