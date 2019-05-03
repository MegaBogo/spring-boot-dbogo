package com.dbogo.web.persistence;

import com.dbogo.web.domain.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {

    //게시물 번호의 역순으로 페이징 처리.
    public List<FreeBoard> findBySeqGreaterThan(Long seq, Pageable pageable);


    @Query("SELECT b.seq, b.title, count(r) " +
    " FROM FreeBoard b LEFT OUTER JOIN b.replies r " +
    " WHERE b.seq > 0 GROUP BY b ")
    public List<Object[]> getPage(Pageable page);

}
