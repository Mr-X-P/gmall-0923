package com.atguigu.gmall.pms.service.impl;

import com.atguigu.gmall.pms.entity.*;
import com.atguigu.gmall.pms.feign.GmallSmsClient;
import com.atguigu.gmall.pms.mapper.SkuMapper;
import com.atguigu.gmall.pms.mapper.SpuDescMapper;
import com.atguigu.gmall.pms.service.SkuAttrValueService;
import com.atguigu.gmall.pms.service.SkuImagesService;
import com.atguigu.gmall.pms.service.SpuAttrValueService;
import com.atguigu.gmall.pms.vo.SkuVo;
import com.atguigu.gmall.pms.vo.SpuAttrValueVo;
import com.atguigu.gmall.pms.vo.SpuVo;
import com.atguigu.gmall.sms.vo.SkuSaleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.common.bean.PageResultVo;
import com.atguigu.gmall.common.bean.PageParamVo;

import com.atguigu.gmall.pms.mapper.SpuMapper;
import com.atguigu.gmall.pms.service.SpuService;
import org.springframework.util.CollectionUtils;


@Service("spuService")
public class SpuServiceImpl extends ServiceImpl<SpuMapper, SpuEntity> implements SpuService {
    @Autowired(required = false)
    private SpuDescMapper descMapper;
    @Autowired
    private SpuAttrValueService baseService;
    @Autowired(required = false)
    private SkuMapper skuMapper;
    @Autowired
    private SkuAttrValueService skuAttrValueService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private GmallSmsClient smsClient;

    @Override
    public PageResultVo queryPage(PageParamVo paramVo) {
        IPage<SpuEntity> page = this.page(
                paramVo.getPage(),
                new QueryWrapper<SpuEntity>()
        );

        return new PageResultVo(page);
    }

    @Override
    public PageResultVo querySpuByCidOrKeyPage(PageParamVo pageParamVo, Long cid) {
        QueryWrapper<SpuEntity> wrapper = new QueryWrapper<>();
        if (cid != 0){
            wrapper.eq("category_id", cid);
        }
        String key = pageParamVo.getKey();
        if (StringUtils.isNoneBlank(key)){
            wrapper.and(t ->t.eq("id",key)).or().like("name",key);

        }
        IPage<SpuEntity> page = this.page(
                pageParamVo.getPage(),
                wrapper
        );
        return new PageResultVo(page);
    }

    @Override
    public void bigsave(SpuVo spuVo) {
        ///保存spu相关
        // 1.1 保存sup基本信息
        spuVo.setPublishStatus(1);//默认是已上架
        spuVo.setCreateTime(new Date());//新增时,更新时间和创建时间一致
        spuVo.setUpdateTime(spuVo.getCreateTime());
        save(spuVo);
        Long spuId = spuVo.getId();//获取新增后的spuId

        //1.2 保存spu的描述信息
        SpuDescEntity spuInfoDescEntity = new SpuDescEntity();
        spuInfoDescEntity.setSpuId(spuId);
        spuInfoDescEntity.setDecript(StringUtils.join(spuVo.getSpuImages(),","));
        descMapper.insert(spuInfoDescEntity);
        //1.3 保存spu的规格参数信息
        List<SpuAttrValueVo> baseAttrs = spuVo.getBaseAttrs();
        if (!CollectionUtils.isEmpty(baseAttrs)){
            List<SpuAttrValueEntity> spuAttrValueEntities= baseAttrs.stream().map(spuAttrValueVo -> {
                spuAttrValueVo.setSpuId(spuId);
                spuAttrValueVo.setSort(0);
                return spuAttrValueVo;
            }).collect(Collectors.toList());
            this.baseService.saveBatch(spuAttrValueEntities);
        }
        /// 2. 保存sku相关信息
        List<SkuVo> skuVos = spuVo.getSkus();
        if (CollectionUtils.isEmpty(skuVos)){
            return;
        }
        skuVos.forEach(skuVo -> {
            SkuEntity skuEntity = new SkuEntity();
            BeanUtils.copyProperties(skuVo,skuEntity);
            skuEntity.setBrandId(spuVo.getBrandId());
            skuEntity.setCategoryId(spuVo.getCategoryId());
            skuEntity.setName(skuVo.getName());
            skuEntity.setTitle(skuVo.getTitle());
            skuEntity.setSubtitle(skuVo.getSubtitle());
            skuEntity.setWeight(skuVo.getWeight());
            skuEntity.setPrice(skuVo.getPrice());
            List<String> images = skuVo.getImages();
            if (!CollectionUtils.isEmpty(images)){
                skuEntity.setDefaultImage(skuEntity.getDefaultImage()==null?images.get(0) : skuEntity.getDefaultImage());
            }
            skuEntity.setSpuId(spuId);
            skuMapper.insert(skuEntity);
            Long skuId = skuEntity.getId();
            // 保存sku图片信息
            if (!CollectionUtils.isEmpty(images)){
                String defaultImage=images.get(0);
                List<SkuImagesEntity> skuImageses = images.stream().map(image -> {
                   SkuImagesEntity skuImagesEntity=new SkuImagesEntity();
                   skuImagesEntity.setDefaultStatus(StringUtils.equals(defaultImage,image) ? 1 : 0 );
                   skuImagesEntity.setSkuId(skuId);
                   skuImagesEntity.setSort(0);
                   skuImagesEntity.setUrl(image);
                   return skuImagesEntity;
                }).collect(Collectors.toList());
                skuImagesService.saveBatch(skuImageses);
            }
            //2.3 保存sku的规格参数(销售属性)
            List<SkuAttrValueEntity> saleAttrs = skuVo.getSaleAttrs();
            saleAttrs.forEach(saleattr -> {
                saleattr.setSort(0);
                saleattr.setSkuId(skuId);
            });
            this.skuAttrValueService.saveBatch(saleAttrs);
            //3. 保存营销相关信息,需要远程调用gmall-sms
            SkuSaleVo skusaleVo = new SkuSaleVo();
            BeanUtils.copyProperties(skuVo,skusaleVo);
            skusaleVo.setSkuId(skuId);
            smsClient.saveSkuSalueInfo(skusaleVo);
        });
    }

}