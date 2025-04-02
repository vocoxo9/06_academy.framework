package com.kh.spring.notice.service;

import java.util.ArrayList;

import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {
	/* 공지사항 목록 조회 */
	ArrayList<Notice> selectNoticeList();
	
	/* 공지사항 상세 조회 */
	Notice selectNoticeDetail(int noticeNo);
	
	/* 공지사항 추가 */ 
	int insertNotice(Notice notice);
	
	/* 공지사항 수정 */
	int updateNotice(Notice notice);
	
	/* 공지사항 삭제 */
	int deleteNotice(int noticeNo);	
}
