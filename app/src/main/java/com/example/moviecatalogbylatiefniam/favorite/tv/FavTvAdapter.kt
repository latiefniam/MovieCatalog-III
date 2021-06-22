package com.example.moviecatalogbylatiefniam.favorite.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.databinding.ItemContentBinding
import com.example.moviecatalogbylatiefniam.detail.DetailContentActivity

class FavTvAdapter:PagedListAdapter<TvEntity, FavTvAdapter.TvViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>(){
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
               return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem==newItem
            }

        }
    }
    fun swipeData(swipePositions: Int): TvEntity? = getItem(swipePositions)

    class TvViewHolder(private val itemBinding: ItemContentBinding):
    RecyclerView.ViewHolder(itemBinding.root){
        fun bind(tv: TvEntity){
            with(itemBinding){
                title.text = tv.tvTitle
                duration.text = tv.tvDuration
                genre.text = tv.tvGenre
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailContentActivity::class.java)
                    intent.putExtra(DetailContentActivity.EXTRA_TVSHOW, tv.tvId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context).load(tv.tvPicture)
                    .into(image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemBinding = ItemContentBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return TvViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
      val tv = getItem(position)
        if(tv != null){
            holder.bind(tv)
        }
    }

}