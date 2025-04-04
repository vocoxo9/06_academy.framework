package com.kh.spring.notice.service;

import java.util.ArrayList;

import com.kh.spring.common.PageInfo;
import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {
	/* 공지사항 목록 조회 */
	// ArrayList<Notice> selectNoticeList();				// => 페이징 처리 적용 전
	ArrayList<Notice> selectNoticeList(PageInfo pi);	// => 페이징 처리 적용 후
	
	/* 공지사항 상세 조회 */
	Notice selectNoticeDetail(int noticeNo);
	
	/* 공지사항 전체 게시글 수 조회 */
	int selectNoticeCount();

	/* 공지사항 검색 시 게시글 수 조회 */
	int selectByNoticeTitleCount(String keyword);
	
	/* 공지사항 추가 */ 
	int insertNotice(Notice notice);
	
	/* 공지사항 수정 */
	int updateNotice(Notice notice);
	
	/* 공지사항 삭제 */
	int deleteNotice(int noticeNo);

	/* 공지사항 제목 검색 */
	ArrayList<Notice> searchNoticeByTitle(String keyword, PageInfo pi);	
}
