package com.atguigu.gmall.pms.mapper;

import com.atguigu.gmall.pms.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价
 * 
 * @author Mr.xiao
 * @email xiao@atguigu.com
 * @date 2021-03-08 19:42:23
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {
	
}
