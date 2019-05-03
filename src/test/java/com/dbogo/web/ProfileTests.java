package com.dbogo.web;


import com.dbogo.web.domain.Member;
import com.dbogo.web.domain.Profile;
import com.dbogo.web.persistence.MemberRepository;
import com.dbogo.web.persistence.ProfileRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ProfileTests {

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    ProfileRepository profileRepo;

    /*
    @Test
    public void testInsertProfile() {
        Member member = new Member();
        member.setId(3);

        for(int i = 1; i<5; i++) {

            Profile profile1 = new Profile();
            profile1.setName("face"+i+".jpg");

            if(i == 1) {
                profile1.setCurrent(true);
            }
            profile1.setMember(member);
            profileRepo.save(profile1);

        }
    }
    */

    /*
    @Test
    public void testFetchJoin1() {
        List<Object[]> result = memberRepo.getMemberWithProfileCount(3);

        result.forEach(arr->
                System.out.println(Arrays.toString(arr)));
    }
    */

    @Test
    public void testFetchJoin2() {
        List<Object[]> result = memberRepo.getMemberWithProfile(3);
        result.forEach(arr-> System.out.println(Arrays.toString(arr)));
    }

}
