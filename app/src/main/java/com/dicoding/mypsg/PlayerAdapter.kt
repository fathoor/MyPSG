package com.dicoding.mypsg

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlayerAdapter(private val listPlayer: ArrayList<Player>) : RecyclerView.Adapter<PlayerAdapter.ListViewHolder>() {
    private lateinit var onCardClickCallback: OnCardClickCallback

    interface OnCardClickCallback {
        fun onCardClicked(data: Player)
    }

    fun setOnCardClickCallback(onCardClickCallback: OnCardClickCallback) {
        this.onCardClickCallback = onCardClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPosition: TextView = itemView.findViewById(R.id.tv_player_position)
        val tvNumber: TextView = itemView.findViewById(R.id.tv_player_number)
        val ivPhoto: ImageView = itemView.findViewById(R.id.img_player)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_player, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (_, _, position, number, _, _, photo) = listPlayer[position]
        holder.tvPosition.text = position
        holder.tvNumber.text = number.toString()
        Glide.with(holder.itemView.context).load(photo).into(holder.ivPhoto)

        holder.itemView.setOnClickListener { onCardClickCallback.onCardClicked(listPlayer[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listPlayer.size
}