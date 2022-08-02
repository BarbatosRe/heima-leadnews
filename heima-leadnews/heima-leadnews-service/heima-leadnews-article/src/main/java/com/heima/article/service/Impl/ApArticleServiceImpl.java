package com.heima.article.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.ArticleConstants;

import com.heima.model.article.dots.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper,ApArticle> implements ApArticleService {

    // 单页最大加载的数字
    private final static Short Max_PAGE_SIZE=50;

    @Autowired
    private ApArticleMapper apArticleMapper;
    /**
     *
     * @param dto
     * @param type 1为加载更多 2位加载最新
     * @return
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //1.校验参数
        Integer size = dto.getSize();
        if (size==null || size==0){
            size=10;
        }
        //设置最大分页不能超过50
        size=Math.min(size,Max_PAGE_SIZE);
        dto.setSize(size);
        //类型参数检验
        if (!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)){
            type=ArticleConstants.LOADTYPE_LOAD_MORE;
        }
        //文章频道校验
        if (StringUtils.isBlank(dto.getTag())){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        //时间校验
        if (dto.getMaxBehotTime()==null) dto.setMaxBehotTime(new Date());
        if (dto.getMinBehotTime()==null) dto.setMinBehotTime(new Date());

        //2.查询数据
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, type);
        //3.结果封装
        ResponseResult responseResult=ResponseResult.okResult(apArticles);
        return responseResult;
    }
}