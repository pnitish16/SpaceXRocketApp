package com.spacex.app.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spacex.app.databinding.ItemFlickerImageBinding
import com.spacex.app.util.PicassoCache
import com.spacex.app.util.imageloader.ImageLoader

class FlickerImagesAdapter(
    private val imageLoader: ImageLoader
) :
    RecyclerView.Adapter<FlickerImagesAdapter.ViewHolder>() {

    private val flickerImageList: ArrayList<String> = arrayListOf()

    class ViewHolder(private val binding: ItemFlickerImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val ivFlickerImage = binding.ivFlickerImage

        fun bind(flickerImagePath: String) {
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFlickerImageBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        flickerImageList[position].let { flickerImage ->
            viewHolder.bind(flickerImage)
//            imageLoader.load(flickerImage, viewHolder.ivFlickerImage)
            PicassoCache.getPicassoInstance(viewHolder.ivFlickerImage.context).load(flickerImage).into(viewHolder.ivFlickerImage)
        }
    }

    override fun getItemCount(): Int {
        return flickerImageList.size
    }

    fun setData(newList: List<String>) {
        val diffCallback = DiffCallback(flickerImageList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        flickerImageList.clear()
        flickerImageList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    private class DiffCallback(
        private val oldList: List<String>,
        private val newList: List<String>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
