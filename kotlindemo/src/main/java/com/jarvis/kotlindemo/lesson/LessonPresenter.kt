package com.jarvis.kotlindemo.lesson

import com.jarvis.kotlindemo.http.EntityCallback

import com.google.gson.reflect.TypeToken

import com.jarvis.kotlindemo.LessonActivity
import com.jarvis.kotlindemo.Utils.Utils
import com.jarvis.kotlindemo.http.HttpClient
import java.lang.reflect.Type


/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */
class LessonPresenter {


    companion object {
        //编译器常量
        const val LESSON_PATH = "lessons"
    }


    private var activity: LessonActivity? = null

    constructor(activity: LessonActivity?) {
        this.activity = activity
    }

    private var lessons: MutableList<Lesson> = mutableListOf()

    private val type: Type = object : TypeToken<List<Lesson?>?>() {}.type

    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<MutableList<Lesson>> {
            override fun onFailure(message: String) {
                activity!!.runOnUiThread {
                    Utils.toast(message)
                }
            }

            override fun onSuccess(entity: MutableList<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity!!.runOnUiThread { activity!!.showResult(lessons) }
            }
        })
    }

    fun showPlayback() {
//        val playbackLessons: MutableList<Lesson> = ArrayList()
//        lessons.forEach {
//            if (it.state === Lesson.State.PLAYBACK) {
//                playbackLessons.add(it)
//            }
//        }
        activity!!.showResult(lessons.filter { it.state === Lesson.State.PLAYBACK })
    }
}