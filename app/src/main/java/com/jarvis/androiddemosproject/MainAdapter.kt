package com.jarvis.androiddemosproject

import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jarvis.androiddemosproject.databinding.ItemHomeNormalBinding

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/25
 */
class MainAdapter(data: MutableList<ItemsBean>) :
    BaseDelegateMultiAdapter<ItemsBean, BaseViewHolder>(data) {

    companion object {
        const val TYPE_NORMAL = 1
    }

    init {
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<ItemsBean>() {

            override fun getItemType(data: List<ItemsBean>, position: Int): Int {
                return data[position].type
            }

        })

        getMultiTypeDelegate()?.addItemType(TYPE_NORMAL, R.layout.item_home_normal)

    }

    override fun convert(holder: BaseViewHolder, item: ItemsBean) {
        when (holder.itemViewType) {
            TYPE_NORMAL -> {
                val binding = DataBindingUtil.bind<ItemHomeNormalBinding>(holder.itemView)
                binding?.apply {
                    textview.text = item.name
                }

            }
        }
    }
}