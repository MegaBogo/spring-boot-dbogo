package com.dbogo.web.controller;

import com.dbogo.web.domain.Member;
import com.dbogo.web.domain.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * project
 */
@Controller
public class SampleController {

    @GetMapping("/sample1")
    public String sample1(Model model) {
        model.addAttribute("greeting", "모냐");

        return "sample1";
    }

    @GetMapping("/sample2")
    public void sample2(Model model) {

        Member member = new Member();
        member.setNickname("테스트");

        model.addAttribute("vo", member);
    }

    @GetMapping("/sample3")
    public void sample3(Model model) {

        List<MemberVO> list = new ArrayList<>();

        IntStream.range(1,5).forEach(i->{
          list.add(new MemberVO(123, "u0"+i, "p0" + i, "테스트" +i,
                  new Timestamp(System.currentTimeMillis())));
        });

        model.addAttribute("list",list);
    }
}
