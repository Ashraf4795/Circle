package com.ango.circle.views.home.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ango.circle.core.data.model.Circle
import com.ango.circle.databinding.CircleCardItemBinding
import java.text.SimpleDateFormat

class CirclesAdapter(private var circlesList: List<Circle>) :
    RecyclerView.Adapter<CirclesAdapter.CircleViewHolder>() {


    class CircleViewHolder(val binding: CircleCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Circle) {
            binding.circle = item
            binding.dateTxtId.text=updateCircleDate(item)
        }
        fun updateCircleDate(item: Circle):String{
            val formatDay=SimpleDateFormat("dd")
            val formatMonth=SimpleDateFormat("MMM")
            val date=item.created?.toDate()
            return "${formatDay.format(date)}\n${formatMonth.format(date)}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CircleCardItemBinding.inflate(inflater,parent,false)
        return CircleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CircleViewHolder, position: Int) =
        holder.bind(circlesList[position])

    override fun getItemCount(): Int {
        return circlesList.size
    }

    fun updateList(list: List<Circle>) {
        circlesList = list
        notifyDataSetChanged()
    }
}