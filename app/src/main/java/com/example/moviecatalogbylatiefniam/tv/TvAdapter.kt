package com.example.moviecatalogbylatiefniam.tv

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



class TvAdapter: PagedListAdapter<TvEntity, TvAdapter.ViewHolder>(DIFF_CALLBAK) {

    companion object{
        private val DIFF_CALLBAK = object : DiffUtil.ItemCallback<TvEntity>(){
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.tvId ==newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem==newItem
            }

        }
    }
    class ViewHolder(private val binding: ItemContentBinding):RecyclerView.ViewHolder
        (binding.root){
            fun bind (tvs: TvEntity){
                with(binding){
                    title.text = tvs.tvTitle
                    genre.text = tvs.tvGenre
                    duration.text = tvs.tvDuration
                    Glide.with(itemView.context)
                        .load(tvs.tvPicture)
                        .into(image)
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailContentActivity::class.java)
                        intent.putExtra(DetailContentActivity.EXTRA_TVSHOW,tvs.tvId)
                        itemView.context.startActivity(intent)
                    }
                }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContentBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvs = getItem(position)
        if(tvs != null) {
            holder.bind(tvs)
        }
    }


}