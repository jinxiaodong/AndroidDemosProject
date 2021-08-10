package com.jarvis.kotlindemo.lesson

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.kotlindemo.R
import com.jarvis.kotlindemo.base.BaseViewHolder


/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */
class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {


    private val list = mutableListOf<Lesson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun updateAndNotify(lessons: List<Lesson>) {
        this.list.addAll(lessons)
        Log.i("III", lessons.toString())
        notifyDataSetChanged()
    }


    class LessonViewHolder internal constructor(itemView: View) : BaseViewHolder(itemView) {

        companion object {
            fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_lesson, parent, false)
                )
            }


        }

        internal fun onBind(lesson: Lesson) {
            setText(R.id.tv_date, lesson.date ?: "日期待定")
            setText(R.id.tv_content, lesson.content)
            lesson.state?.let {
                setText(R.id.tv_state, it.stateName())
                val colorRes = when (it) {
                    Lesson.State.PLAYBACK -> R.color.playback
                    Lesson.State.LIVE -> R.color.live
                    Lesson.State.WAIT -> R.color.wait
                }
                val color = itemView.context.getColor(colorRes)
                getView<View>(R.id.tv_state)?.setBackgroundColor(color)
            }


        }
    }
}