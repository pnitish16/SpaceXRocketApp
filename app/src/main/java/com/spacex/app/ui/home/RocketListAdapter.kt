package com.spacex.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spacex.app.databinding.ItemRocketListBinding
import com.spacex.app.domain.model.Rocket
import com.spacex.app.util.PicassoCache
import com.spacex.app.util.imageloader.ImageLoader

class RocketListAdapter(
    private val imageLoader: ImageLoader,
    private val onItemClick: (rocket: Rocket) -> Unit
) : RecyclerView.Adapter<RocketListAdapter.ViewHolder>() {

    private val rocketList: ArrayList<Rocket> = arrayListOf()

    class ViewHolder(private val binding: ItemRocketListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rocket: Rocket) {
            binding.rocket = rocket
            PicassoCache.getPicassoInstance(binding.root.context).load(rocket.flickerImage).into(binding.ivFlickerImage)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRocketListBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        rocketList[position].let { rocketItem ->
            holder.bind(rocketItem)
            holder.itemView.setOnClickListener {
                onItemClick.invoke(rocketItem)
            }
        }
    }

    override fun getItemCount(): Int = rocketList.size

    fun setData(newList: List<Rocket>) {
        val diffCallback = DiffCallback(rocketList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        rocketList.clear()
        rocketList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    private class DiffCallback(
        private val oldList: List<Rocket>,
        private val newList: List<Rocket>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}