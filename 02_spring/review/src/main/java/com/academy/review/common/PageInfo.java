package com.academy.review.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageInfo {

    private int listCount;
    // 전체 게시글 수
    private int currPage;
    // 현재 페이지 번호
    private int pageLimit;
    // 페이징 바 최대 개수
    private int boardLimit;
    // 한 페이지 당 게시글 최대 개수

    // ------> 위의 4개를 가지고 아래 3개를 계산
    private int maxPage;
    // 페이지 가장 마지막 페이지 번호
    private int startPage;
    // 페이지 시작 번호
    private int endPage;
    // 페이지 끝 번호

    public PageInfo(int listCount, int currPage, int pageLimit, int boardLimit){
        this.listCount = listCount;
        this.currPage = currPage;
        this.pageLimit = pageLimit;
        this.boardLimit = boardLimit;

        this.maxPage = (int)Math.ceil((double)listCount/boardLimit);
        this.startPage = ((currPage -1 ) / pageLimit) * pageLimit + 1 ;
        this.endPage = startPage + pageLimit -1;

        this.endPage = endPage > maxPage ? maxPage : endPage;
        
    }
}
