package com.atguigu.gmall.pms.vo;
import com.atguigu.gmall.pms.entity.SpuAttrValueEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * author:Mr.xiao
 */
public class SpuAttrValueVo extends SpuAttrValueEntity {
    public void servalueSelected(List<Object> valueSelected){
        if (CollectionUtils.isEmpty(valueSelected)){
            return;
        }
        this.setAttrValue(StringUtils.join(valueSelected,","));
    }
}
