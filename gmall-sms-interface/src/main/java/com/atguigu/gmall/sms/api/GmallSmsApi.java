package com.atguigu.gmall.sms.api;

import com.atguigu.gmall.common.bean.ResponseVo;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * author:Mr.xiao
 */
public interface GmallSmsApi {
    @PostMapping("sms/skubounds/skusale/save")
    ResponseVo<Object> saveSkuSalueInfo(@RequestBody SkuSaleVo skusaleVo);
}
