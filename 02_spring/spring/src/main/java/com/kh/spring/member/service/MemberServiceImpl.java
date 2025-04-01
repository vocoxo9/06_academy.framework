package com.kh.spring.member.service;

import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		// 필드로 선언된 객체(빈)를 생성자 방식으로 주입
@Service		// @Component 보다 좀 더 구체화된 객체를 의미 => Service bean 등록
public class MemberServiceImpl implements MemberService{
	/*
	 * ** MemberDao 객체에 대한 DI 처리 **
	 */
	private final MemberDao mDao;
/*
	@Autowired
	public MemberServiceImpl(MemberDao mDao) {
		this.mDao = mDao;
	}
	// => 생성자 주입 방식 --> lombok 기능 사용으로 주석처리
*/
	@Override
	public int insertMember(Member m) {
		return mDao.insertMember(m);
	}

	@Override
	public Member loginMember(Member m) {
		return mDao.loginMember(m);
	}

	@Override
	public Member updateMember(Member m) {
		// 회원 정보 수정
		int updateMem = mDao.updateMember(m);
		
		// 수정 성공 시 회원 정보 조회
		if(updateMem > 0) {
			return mDao.loginMember(m);	
		} else {
			return null;
		}
	}

	@Override
	public int deleteMember(String userId) {
		return mDao.deleteMember(userId);
	}
	
}
