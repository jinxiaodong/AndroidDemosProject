package com.jarvis.androiddemosproject

import com.jarvis.lib.ktx.getDrawable

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/25
 */
object DataUtil {
    fun getMainData(): MutableList<ItemsBean> {
        val data = arrayListOf<ItemsBean>()
        data.add(ItemsBean("MaterialDesign", getDrawable(R.mipmap.icon_1)))
        data.add(ItemsBean("JetPack", getDrawable(R.mipmap.icon_2)))
        data.add(ItemsBean("CustomerView", getDrawable(R.mipmap.icon_3)))

        return data
    }
}