package com.dbogo.web.persistence;

import com.dbogo.web.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;


public interface NoticeRepository extends CrudRepository<Notice, Long> {

    public List<Notice> findNoticeByTitle(String title);

    public Collection<Notice> findByRegName(String name);

    // 작성자에 대한 like % 키워드 %
    public Collection<Notice> findByRegNameContaining(String name);


    //or 조건 처리
    public Collection<Notice> findByTitleContainingOrContentContaining(String title, String content);

    // title LIKE % ? % AND SEQ > ?
    public Collection<Notice> findByTitleContainingAndSeqGreaterThan(String keyword, Long num);

    //seq > ?  order by 처리
    public Collection<Notice> findBySeqGreaterThanOrderBySeqDesc(Long seq);

    //seq > ? ORDER BY seq DESC limit ?, ?
    public List<Notice> findBySeqGreaterThanOrderBySeqDesc(Long seq, Pageable paging);


    //page
    public Page<Notice> findBySeqGreaterThan(Long seq, Pageable paging);

}
