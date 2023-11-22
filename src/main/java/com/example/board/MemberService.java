package com.example.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;//list 반환
import java.util.Optional;//객체 없으면 pass
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class MemberService {

    private final MemberRepository memberRepository;

    public String signup(Dto dto){

        Member member = new Member(dto.getEmail(), dto.getPassword(), dto.getAge());

        memberRepository.save(member);


        return "저장 완료";
    }
    public List<MembersResDto> getUserMembers(){
        List<MembersResDto> list = memberRepository.findAll()
                .stream()
                .map(member->new MembersResDto(member.getEmail(), member.getAge())).collect(Collectors.toList());
        return list;
    }
    public Member updateMember(Long id, Member member) {
        Optional<Member> memberData=memberRepository.findById(id);

        Member newMember=memberData.get();


        newMember.setEmail(member.getEmail());
        newMember.setPassword(member.getPassword());
        newMember.setAge(member.getAge());

        memberRepository.save(newMember);

        return newMember;
    }

    public boolean deleteUserById(Long userId) {
        // 해당 userId에 해당하는 회원 조회
        Member member = memberRepository.findById(userId).orElse(null);

        // 만약 회원이 존재하면 삭제하고 true 반환, 존재하지 않으면 false 반환
        if (member != null) {
            memberRepository.delete(member);
            return true;
        } else {
            return false;
        }
    }


}



