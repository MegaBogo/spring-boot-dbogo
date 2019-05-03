package com.dbogo.web.persistence;

import com.dbogo.web.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

}
