package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


// 스프링에 객체에 넣어둔다 (스프링 빈이 관리된다)
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired //맴버 컨트롤러가 생성이 될 때 스프링 빈에 등록되어 있는 맴버 서비스의 객체를 가져다가 넣어준다 (DI: Dependuncy Injection
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
