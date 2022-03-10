package com.jarvis.demos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jarvis.demos.calendar.CalendarTestActivity
import com.jarvis.demos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var dataList = arrayListOf(
        "日历",
        "测试1",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)

        binding.recyclerview.adapter = ItemAdapter(this, dataList) {
            when (it) {
                dataList[0] -> jumpToCalendarTest()
            }

        }
    }

    private fun jumpToCalendarTest() {

        startActivity(Intent(this, CalendarTestActivity::class.java))

    }


    inner class ItemAdapter(
        var context: Context,
        var data: ArrayList<String>,
        var onClick: (String) -> Unit
    ) :
        RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(layoutInflater.inflate(R.layout.item_main_list, parent, false))
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bindViewHolder(data[position])
        }

        override fun getItemCount(): Int {
            return data.size
        }


        inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindViewHolder(item: String) {
//                itemView.textview.text = item
//                itemView.textview.setOnClickListener {
//                    onClick(item)
//                }
            }

        }
    }


}


