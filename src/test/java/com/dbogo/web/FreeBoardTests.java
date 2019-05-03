package com.dbogo.web;

import com.dbogo.web.domain.Comment;
import com.dbogo.web.domain.FreeBoard;
import com.dbogo.web.persistence.CommentRepository;
import com.dbogo.web.persistence.FreeBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit //테스트 결과 commit 
public class FreeBoardTests {

    @Autowired
    FreeBoardRepository boardRepo;

    @Autowired
    CommentRepository commentRepo;

    /*
    @Test
    public void insertDummy() {
        IntStream.range(1,200).forEach(i->{
            FreeBoard board = new FreeBoard();
            board.setTitle("Free Board ... " + i);
            board.setContent("자유게시판 "+i);
            board.setRegId("devbogo");

            boardRepo.save(board);

        });
    }
    */

    /*
    랜덤하게 댓글을 추가.
    2가지 방식을 사용 할 수 있음
    - 단방향에서 처리하듯이 Comment를 생성하고, FreeBoard 자체는 새로 만들어서 bno속성만 지정하는 방식
    - 양방향이므로 FreeBoard 객체를 얻어온 후 Comment를 댓글 리스트에 추가 한 후에 FreeBoard자체를 저장하는 방식
     */

    //양방향 처리
    /*
    @Transactional
    @Test
    public void insertComment2Way() {
        Optional<FreeBoard> result = boardRepo.findById(199L);

        result.ifPresent(board-> {
            List<Comment> replies = board.getReplies();
            Comment comment = new Comment();
            comment.setContent("REPLY .......... ");
            comment.setRegId("devbogo");
            comment.setBoard(board);

            replies.add(comment);

            board.setReplies(replies);

            boardRepo.save(board);

        });
    }
     */

    //단방향처리
    /*
    @Test
    public void insertComment1Way() {
        FreeBoard board = new FreeBoard();
        board.setSeq(199L);

        Comment comment = new Comment();
        comment.setContent("COMMENT~~~");
        comment.setRegId("devbogo");
        comment.setBoard(board);

        commentRepo.save(comment);

    }
    */

    /*
    @Test
    public void testList1() {

        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");

        boardRepo.findBySeqGreaterThan(0L, page).forEach(board -> {
            log.info(board.getSeq() + ":"+board.getTitle());
        });
    }
    */

    /*
    board.getReplies().size()호출 할 때
    [org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.dbogo.web.domain.FreeBoard.replies, could not initialize proxy - no Session]
    에러가 나는 이유는,

    JPA는 연관관계가 있는 엔티티를 조회할 때 기본적으로 '지연 로딩(lazy loding)'이라는 방식을 이용.
    지연 로딩은 말 그대로 '게으른' 이라는 의미로, 정보가 필요하기 전까지는 최대한 테이블에 접근하지 않는 방식을 의미.

    지연로딩을 하는 가장 큰 이유는 성능때문임.
    하나의 엔티티가 여러 엔티티들과 종속적인 관계를 맺고 있다면, SQL에서는 조인을 이용하는데,
    조인이 복잡해질수록 성능이 저하되기 때문.
    따라서 JPA에서는 연관관계의 Collection타입을 처리할 때 '지연 로딩'을 기본으로 사용

    지연로딩<->즉시로딩(eager loading)
    즉시 로딩은 일반적으로 조인을 이용해서 필요한 모든 정보를 처리.

    즉시 로딩을 사용하려면 @OneToMany에 'fetch'라는 속성값으로 'FetchType.EAGER'를 지정하면 됩니다.
     */

    /*
    @Transactional //지연 로딩으로 댓글을 가져오고 싶다면.
    @Test
    public void testList2() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
        boardRepo.findBySeqGreaterThan(0L, page).forEach(board->{
            log.info(board.getSeq() + " : " + board.getTitle() + " : " + board.getReplies().size());
        });
    }
    */

    /*
    지연 로딩의 문제를 해결하는 가장 좋은 방법은 @Query를 이용해 조인 처리를 하는 것.
    Hibernate 5.2(Spring 2.0)는 연관관계가 없는 엔티티 간에도 조인이 가능
    Hibernate 5.0(Spring 1.5.4)는 연관관계가 존재하는 경우에 조인을 이용해서 처리가 가능
     */
    @Test
    public void testList3() {
        Pageable page = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");

        boardRepo.getPage(page).forEach(arr ->
                log.info(Arrays.toString(arr)));
    }
}
