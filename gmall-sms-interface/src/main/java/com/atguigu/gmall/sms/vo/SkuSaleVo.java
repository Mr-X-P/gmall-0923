package com.atguigu.gmall.sms.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * author:Mr.xiao
 */
@Data
public class SkuSaleVo {
    /**
     * author:Mr.xiao
     */
        private Long skuId;

        // 积分活动
        private BigDecimal growBounds;
        private BigDecimal buyBounds;
        /**
         * 优惠生效情况[1111（四个状态位，从右到左）;
         * 0 - 无优惠，成长积分是否赠送;
         * 1 - 无优惠，购物积分是否赠送;
         * 2 - 有优惠，成长积分是否赠送;
         * 3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
         */
        private List<Integer> work;

        // 满减活动
        private BigDecimal fullPrice;
        private BigDecimal reducePrice;
        private Integer fullAddOther;

        private Integer fullCount;
        private BigDecimal discount;
        /**
         * 是否叠加其他优惠[0-不可叠加，1-可叠加]
         */
        private Integer addOther;

        public Long getSkuId() {
            return skuId;
        }

        public void setSkuId(Long skuId) {
            this.skuId = skuId;
        }

        public BigDecimal getGrowBounds() {
            return growBounds;
        }

        public void setGrowBounds(BigDecimal growBounds) {
            this.growBounds = growBounds;
        }

        public BigDecimal getBuyBounds() {
            return buyBounds;
        }

        public void setBuyBounds(BigDecimal buyBounds) {
            this.buyBounds = buyBounds;
        }

        public List<Integer> getWork() {
            return work;
        }

        public void setWork(List<Integer> work) {
            this.work = work;
        }

        public BigDecimal getFullPrice() {
            return fullPrice;
        }

        public void setFullPrice(BigDecimal fullPrice) {
            this.fullPrice = fullPrice;
        }

        public BigDecimal getReducePrice() {
            return reducePrice;
        }

        public void setReducePrice(BigDecimal reducePrice) {
            this.reducePrice = reducePrice;
        }

        public Integer getFullAddOther() {
            return fullAddOther;
        }

        public void setFullAddOther(Integer fullAddOther) {
            this.fullAddOther = fullAddOther;
        }

        public Integer getFullCount() {
            return fullCount;
        }

        public void setFullCount(Integer fullCount) {
            this.fullCount = fullCount;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }

        public Integer getAddOther() {
            return addOther;
        }

        public void setAddOther(Integer addOther) {
            this.addOther = addOther;
        }


}
