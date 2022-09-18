package com.heima.search.service;


import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dtos.HistorySearchDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface ApUserSearchService{
    /**
     * 保存用户搜索历史记录
     * @param keyword
     * @param userId
     */
    public void insert(String keyword,Integer userId);

    public ResponseResult findUserSearch();


    public ResponseResult delUserSearch(HistorySearchDto historySearchDto);
}