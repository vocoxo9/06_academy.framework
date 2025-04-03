package com.kh.spring.notice.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.kh.spring.notice.model.dao.NoticeDao;
import com.kh.spring.notice.model.vo.Notice;

// @Component
@Service
public class NoticeServiceImpl implements NoticeService{
	private final NoticeDao nDao;
	public NoticeServiceImpl(NoticeDao nDao) {
		this.nDao = nDao;
	}
	
	
	@Override
	public ArrayList<Notice> selectNoticeList() {
		return (ArrayList<Notice>)nDao.selectNoticeList();
	}

	@Override
	public Notice selectNoticeDetail(int noticeNo) {
		return nDao.selectNoticeDetail(noticeNo);
	}

	@Override
	public int insertNotice(Notice notice) {
		return nDao.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return nDao.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return nDao.deleteNotice(noticeNo);
	}

}
