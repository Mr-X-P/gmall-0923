package com.atguigu.gmall.pms.mapper;

import com.atguigu.gmall.pms.entity.SpuDescEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * spu信息介绍
 * 
 * @author Mr.xiao
 * @email xiao@atguigu.com
 * @date 2021-03-08 19:42:22
 */
@Transactional
@Mapper
public interface SpuDescMapper extends BaseMapper<SpuDescEntity> {
	
}
