package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.android.synthetic.main.list_item_sleep_night.view.*

class SleepNightAdapter : RecyclerView.Adapter<ViewHolder>() {

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size
}

class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
    val sleepLength = itemView.sleep_length
    val quality = itemView.quality_string
    val qualityImage = itemView.quality_image

    companion object{
        fun from(parent: ViewGroup) : ViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
            return ViewHolder(view)
        }
    }

    fun bind(item: SleepNight) {
        val res = itemView.context.resources

        sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        quality.text = convertNumericQualityToString(item.sleepQuality, res)
        qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }
}