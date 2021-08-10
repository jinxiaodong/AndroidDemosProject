package com.jarvis.kotlindemo.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.annotation.IdRes


/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val viewHashMap = HashMap<Int, View>()


    protected open fun <T : View?> getView(@IdRes id: Int): T? {
        var view = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }

        return view as T?
    }

    protected open fun setText(@IdRes id: Int, text: String?) {
        (getView<View>(id) as TextView).text = text
    }
}