package com.jarvis.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jarvis.kotlindemo.Utils.CacheUtils
import com.jarvis.kotlindemo.Utils.Utils
import com.jarvis.kotlindemo.base.BaseView
import com.jarvis.kotlindemo.lesson.Lesson
import com.jarvis.kotlindemo.lesson.LessonAdapter
import com.jarvis.kotlindemo.lesson.LessonPresenter
import kotlin.reflect.KProperty

class LessonActivity : AppCompatActivity(), BaseView<LessonPresenter>,
    Toolbar.OnMenuItemClickListener {

    override val presenter by lazy {
        LessonPresenter(this)
    }

    private val lessonPresenter = LessonPresenter(this)

    private lateinit var refreshlayout: SwipeRefreshLayout

    private val lessonAdapter = LessonAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        findViewById<RecyclerView>(R.id.list).run {
            layoutManager = LinearLayoutManager(this@LessonActivity)
            adapter = lessonAdapter
            addItemDecoration(DividerItemDecoration(this@LessonActivity, LinearLayout.VERTICAL))
        }

        refreshlayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout).apply {
            refreshlayout.setOnRefreshListener(presenter::fetchData)
            refreshlayout.isRefreshing = true
        }


        lessonPresenter.fetchData()


    }

    internal fun showResult(lessons: List<Lesson>) {
        Utils.toast("success")

        lessonAdapter.updateAndNotify(lessons);
        refreshlayout.isRefreshing = false;
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        lessonPresenter.showPlayback()
        return false
    }


    //    var token: String = ""
//        set(value) {
//            field = value
//            CacheUtils.save("token", value)
//        }
//        get() {
//            return CacheUtils.get("token")!!
//        }
    //委托
    var token: String by Saver("token")

    class Saver(var key: String) {
        operator fun getValue(lessonActivity: LessonActivity, property: KProperty<*>): String {


            return CacheUtils.get(key)!!
        }

        operator fun setValue(lessonActivity: LessonActivity, property: KProperty<*>, s: String) {
            CacheUtils.save(key, s)
        }

    }
}