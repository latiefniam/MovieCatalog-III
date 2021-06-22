package com.example.moviecatalogbylatiefniam.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.databinding.ItemContentBinding
import com.example.moviecatalogbylatiefniam.detail.DetailContentActivity

class MoviesAdapter: PagedListAdapter<MovieEntity, MoviesAdapter.ViewHolder>(DIFF_CALLBAK) {

    companion object{
        private val DIFF_CALLBAK = object :DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId ==newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem==newItem
            }

        }
    }


    class ViewHolder(private val binding: ItemContentBinding):RecyclerView.ViewHolder
        (binding.root){
            fun bind (movies: MovieEntity){
                with(binding){
                    title.text = movies.movieTitle
                    genre.text = movies.movieGenre
                    duration.text = movies.movieDuration
                    Glide.with(itemView.context)
                        .load(movies.moviePicture)
                        .into(image)
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailContentActivity::class.java)
                        intent.putExtra(DetailContentActivity.EXTRA_MOVIE,movies.movieId)
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
        val movies = getItem(position)
        if (movies != null){
        holder.bind(movies)
    }
    }


}