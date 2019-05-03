package com.dbogo.web;

import com.dbogo.web.domain.Notice;
import com.dbogo.web.persistence.NoticeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeRepositoryTests {

    @Autowired
    private NoticeRepository repo;

    @Test
    public void inspect() {
      Class<?> clz = repo.getClass();
      System.out.println(clz.getName());

      Class<?>[] interfaces = clz.getInterfaces();

      Stream.of(interfaces).forEach(inter->System.out.println(inter.getName()));

      Class<?> superClasses = clz.getSuperclass();
      System.out.println(superClasses.getName());

    }

    @Test
    public void testInsert() {
        /*
        for(int i = 1; i<=200; i++) {
            Notice notice = new Notice();
            notice.setTitle(i+"번째 공지글");
            notice.setContent("내용..."+i+"채우기");
            notice.setRegName("관리자");
            noticeRepo.save(notice);
        }
        */
    }

    /*
    @Test
    public void testByTitle() {
        repo.findNoticeByTitle("제목..177")
            .forEach(notice -> System.out.println(notice));
    }
    */

    /*
    @Test
    public void testByRegName() {
        Collection<Notice> results = repo.findByRegName("관리자");
        results.forEach(notice -> System.out.println(notice));
    }
    */


    /*
    @Test
    public void testByRegNameContaining() {
        Collection<Notice> results = repo.findByRegNameContaining("관리");
        results.forEach(notice -> System.out.println(notice));
    }
    */


    /*
    @Test
    public void testByTitleContainingOrContentContaining() {
        Collection<Notice> res = repo.findByTitleContainingOrContentContaining("199","내용");
        res.forEach(notice -> System.out.println(notice));
    }
    */
    /*
    @Test
    public void testBySeqGreaterThanOrderBySeqDesc() {
        //spring boot 2.0.0
        Pageable paging = PageRequest.of(0, 10);
        Collection<Notice> res = repo.findBySeqGreaterThanOrderBySeqDesc(0L,paging);

        res.forEach(notice -> System.out.println(notice));
    }
    */

    @Test
    public void testSeqPagingSort() {
        //Page
        Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "seq");

        Page<Notice> result = repo.findBySeqGreaterThan(0L, paging);

        System.out.println("PAGE SIZE : " + result.getSize());
        System.out.println("TOTAL PAGES : " + result.getTotalPages());
        System.out.println("TOTAL COUNT : " + result.getTotalElements());
        System.out.println("NEXT : " + result.nextPageable());

        List<Notice> list = result.getContent();

        list.forEach(notice -> System.out.println(notice));

    }


}
