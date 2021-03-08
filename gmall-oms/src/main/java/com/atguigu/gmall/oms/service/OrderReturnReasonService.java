package com.atguigu.gmall.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;
import com.atguigu.gmall.oms.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author Mr.xiao
 * @email xiao@atguigu.com
 * @date 2021-03-09 00:29:41
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageResultVo queryPage(PageParamVo paramVo);
}

