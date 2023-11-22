package com.example.board;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@ResponseBody
@RequiredArgsConstructor
public class BoardController {

    private final MemberService memberService;

    @PostMapping("/user")
    public String signup(@RequestBody Dto dto) {
        return memberService.signup(dto);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody Member member) {
        memberService.updateMember(id, member);
        return ResponseEntity.status(HttpStatus.CREATED).body("수정 완료");
    }

    @GetMapping("/user/{id}")
    public List<MembersResDto> getMembers(@PathVariable Long id) {
        return memberService.getUserMembers();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean success = memberService.deleteUserById(id);

        if (success) {
            return ResponseEntity.ok("User 삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User 없음");
        }
    }
}
