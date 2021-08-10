package com.jarvis.kotlindemo.lesson

/**
 * @author jinxiaodong
 * @description：internal修饰符 是模块内访问限定
 * @date 2021/8/9
 */
internal class Lesson(
    var date: String?,
    var content: String?,
    var state: State?
) {

    enum class State {
        PLAYBACK {
            override fun stateName(): String {
                return "有回放"
            }
        },
        LIVE {
            override fun stateName(): String {
                return "正在直播"
            }
        },
        WAIT {
            override fun stateName(): String {
                return "等待中"
            }
        };

        abstract fun stateName(): String?
    }

}