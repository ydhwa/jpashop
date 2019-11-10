package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  // 기본적으로 데이터 변경은 트랜잭션 안에서 이루어져야 함. javax 말고 spring이 제공하는 트랜젝션을 사용하는 것이 바람직하다.
@RequiredArgsConstructor    // final이 있는 필드만 가지고 생성자를 만들어 준다.
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원 가입
     */
    @Transactional  // 데이터 변경이 이루어져야 하므로 다시 정의
    public Long join(Member member) {

        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        // 만일의 상황을 위해 member의 name에 유니크 제약조건을 걸어두자.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
