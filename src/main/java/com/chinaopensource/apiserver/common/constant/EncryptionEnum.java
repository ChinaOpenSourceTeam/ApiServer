package com.chinaopensource.apiserver.common.constant;

/**
 * 加密类型的枚举类
 * create by lzl ON 2017/12/20
 */
public enum EncryptionEnum {
    MD5("MD5","md5加密"),
    SHA_1("SHA_1","SHA-1加密");
    EncryptionEnum(String type,String description){
        this.type = type;
        this.description = description;
    }

    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
}
