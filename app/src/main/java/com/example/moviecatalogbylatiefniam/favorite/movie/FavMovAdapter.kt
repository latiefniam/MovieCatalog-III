package com.example.moviecatalogbylatiefniam.favorite.movie

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

class FavMovAdapter:PagedListAdapter<MovieEntity, FavMovAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
               return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem==newItem
            }

        }
    }
    fun swipeData(swipePositions: Int): MovieEntity? = getItem(swipePositions)

    class MovieViewHolder(private val itemBinding: ItemContentBinding):
    RecyclerView.ViewHolder(itemBinding.root){
        fun bind(movie: MovieEntity){
            with(itemBinding){
                title.text = movie.movieTitle
                duration.text = movie.movieDuration
                genre.text = movie.movieGenre
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailContentActivity::class.java)
                    intent.putExtra(DetailContentActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context).load(movie.moviePicture)
                    .into(image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding = ItemContentBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
      val movie = getItem(position)
        if(movie != null){
            holder.bind(movie)
        }
    }

}