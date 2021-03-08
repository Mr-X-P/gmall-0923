package com.atguigu.gmall.oms.mapper;

import com.atguigu.gmall.oms.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author Mr.xiao
 * @email xiao@atguigu.com
 * @date 2021-03-09 00:29:41
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
	
}
