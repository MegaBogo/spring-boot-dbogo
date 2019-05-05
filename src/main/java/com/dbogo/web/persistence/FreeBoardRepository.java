package com.dbogo.web.persistence;

import com.dbogo.web.domain.FreeBoard;
import com.dbogo.web.domain.QFreeBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>,
        QuerydslPredicateExecutor<FreeBoard> {

    //게시물 번호의 역순으로 페이징 처리.
    public List<FreeBoard> findBySeqGreaterThan(Long seq, Pageable pageable);


    @Query("SELECT b.seq, b.title, count(r) " +
            " FROM FreeBoard b LEFT OUTER JOIN b.replies r " +
            " WHERE b.seq > 0 GROUP BY b ")
    public List<Object[]> getPage(Pageable page);

    /*
    파라미터로 전달하는 Predicate는 QFreeBoard를 이용해서 작성해야함.
    Java8부터는 default 키워드를 이용해서 인터페이스에서 직접 메소드의 내용을 구현해 줄 수 있음.
     */
    public default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        QFreeBoard board = QFreeBoard.freeBoard;

        builder.and(board.seq.gt(0));


        if (type == null)
            return builder;

        switch (type) {
            case "t":
                builder.and(board.title.like("%" + keyword + "%"));
                break;
            case "c":
                builder.and(board.content.like("%" + keyword + "%"));
                break;
            case "w":
                builder.and(board.regId.like("%" + keyword + "%"));
                break;
        }

        return builder;
    }


}
