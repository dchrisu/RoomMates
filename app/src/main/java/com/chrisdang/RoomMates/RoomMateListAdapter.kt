package com.chrisdang.RoomMates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RoomMateListAdapter : ListAdapter<RoomMates, RoomMateListAdapter.RoomMatesViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomMatesViewHolder {
        return RoomMatesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RoomMatesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.WholeName)
    }

    class RoomMatesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): RoomMatesViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return RoomMatesViewHolder(view)
            }
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<RoomMates>() {
        override fun areItemsTheSame(oldItem: RoomMates, newItem: RoomMates): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: RoomMates, newItem: RoomMates): Boolean {
            return oldItem.WholeName == newItem.WholeName
        }
    }
}