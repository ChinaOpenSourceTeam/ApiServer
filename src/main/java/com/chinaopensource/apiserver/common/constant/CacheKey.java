package com.chinaopensource.apiserver.common.constant;

/**
 * 缓存的key
 * create by lzl ON 2017/12/05
 */
public enum CacheKey {

    TOKEN(30,TimeUtils.MINUTE),
    ;

    CacheKey(Integer amounts,TimeUtils timeUtils){
        this.amounts = amounts;
        this.timeUtils = timeUtils;
    }

//    次数
    private Integer amounts;
//
    private TimeUtils timeUtils;

    private Integer ttl;

    public TimeUtils getTimeUtils() {
        return timeUtils;
    }

    public void setTimeUtils(TimeUtils timeUtils) {
        this.timeUtils = timeUtils;
    }

    public Integer getAmounts() {
        return amounts;
    }

    public void setAmounts(Integer amounts) {
        this.amounts = amounts;
    }

    public Integer getTtl() {
        return ttl = amounts * timeUtils.getTime();
    }

    private void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    private enum TimeUtils{
        MINUTE(60),HOUR(60*60),DAY(24*60*60),WEEK(7*24*60*60),MONTH(60 * 60 * 24 * 30);

        private Integer time;

        TimeUtils(Integer time) {
            this.time = time;
        }

        private Integer getTime() {
            return time;
        }

        private void setTime(Integer time) {
            this.time = time;
        }
    }

}
