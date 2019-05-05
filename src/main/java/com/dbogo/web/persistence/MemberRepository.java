package com.dbogo.web.persistence;

import com.dbogo.web.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {

    @Query("SELECT m.id, count(p) FROM Member m LEFT OUTER JOIN Profile p " +
            " ON m.id = p.member WHERE m.id = ?1 GROUP BY m")
    public List<Object[]> getMemberWithProfileCount(int memberId);

    @Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p " +
            " ON m.id = p.member WHERE m.id = ?1 AND p.current = true")
    public List<Object[]> getMemberWithProfile(int memberId);


}
