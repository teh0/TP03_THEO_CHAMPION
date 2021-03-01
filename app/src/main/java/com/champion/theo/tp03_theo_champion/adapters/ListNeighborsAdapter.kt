package com.champion.theo.tp03_theo_champion.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.champion.theo.tp03_theo_champion.R
import com.champion.theo.tp03_theo_champion.contracts.EventCrudable
import com.champion.theo.tp03_theo_champion.databinding.NeighborItemBinding
import com.champion.theo.tp03_theo_champion.models.Neighbor
import com.champion.theo.tp03_theo_champion.repositories.NeighborRepository

class ListNeighborsAdapter(private val eventHandler: EventCrudable<Neighbor>) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {

    private var mNeighbours: List<Neighbor> = ArrayList<Neighbor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NeighborItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.fillViewHolderData(holder, position)
        this.onClickDeleteButton(holder, position)
    }

    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    private fun fillViewHolderData(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        holder.binding.itemListName.text = neighbour.name
        Glide.with(holder.binding.root.context)
            .load(neighbour.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .skipMemoryCache(false)
            .into(holder.binding.itemListAvatar)
    }

    private fun onClickDeleteButton(holder: ViewHolder, position: Int) {
        val deletedNeighbor = this.mNeighbours[position]
        holder.binding.itemListDeleteButton.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(holder.binding.root.context)
            builder.setMessage(R.string.delete_confirm)
                .setCancelable(false)
                .setPositiveButton(R.string.yes) { _, _ ->
                    this.eventHandler.onDelete(deletedNeighbor)
                    notifyDataSetChanged()
                }
                .setNegativeButton(R.string.no) { dialog, _ ->
                    dialog.dismiss()
                }
            val alertDialog = builder.create()
            alertDialog.show()
        })
    }

    fun setNeighbours(neighbours: List<Neighbor>) {
        this.mNeighbours = neighbours
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: NeighborItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

}