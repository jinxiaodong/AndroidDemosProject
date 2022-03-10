package com.jarvis.lib.network.config

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/17
 */
fun getBaseHost(): String {

    return HOST_PRODUCT
}


private const val SP_KEY_BASE_HOST = "sp_key_base_host"

const val HOST_DEV = "https://course.api.cniao5.com/"//开发环境下的host配置
const val HOST_QA = "https://qa.course.api.cniao5.com/"//qa环境的host配置
const val HOST_PRODUCT = "https://release.course.api.cniao5.com/"//正式配置host
