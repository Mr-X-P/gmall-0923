package com.atguigu.gmall.pms.vo;

import com.atguigu.gmall.pms.entity.SpuEntity;
import lombok.Data;

import java.util.List;

/**
 * author:Mr.xiao
 */
@Data
public class SpuVo extends SpuEntity {
    private List<String> spuImages;
    private List<SpuAttrValueVo> baseAttrs;
    private List<SkuVo> skus;

    public List<String> getSpuImages() {
        return spuImages;
    }

    public void setSpuImages(List<String> spuImages) {
        this.spuImages = spuImages;
    }

    public List<SpuAttrValueVo> getBaseAttrs() {
        return baseAttrs;
    }

    public void setBaseAttrs(List<SpuAttrValueVo> baseAttrs) {
        this.baseAttrs = baseAttrs;
    }

    public List<SkuVo> getSkus() {
        return skus;
    }

    public void setSkus(List<SkuVo> skus) {
        this.skus = skus;
    }
}
