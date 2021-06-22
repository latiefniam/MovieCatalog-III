package com.example.moviecatalogbylatiefniam.detail

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogbylatiefniam.R
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.databinding.ActivityDetailContentBinding
import com.example.moviecatalogbylatiefniam.databinding.ContentDetailBinding
import com.example.moviecatalogbylatiefniam.viewmodel.ViewModelFactory
import com.example.moviecatalogbylatiefniam.vo.Status

class DetailContentActivity : AppCompatActivity() {
    


    companion object{
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
    }
    private lateinit var bindingContentDetail: ContentDetailBinding
    private lateinit var viewModel: DetailVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailContentBinding.inflate(layoutInflater)
        bindingContentDetail= binding.detailContent
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(this,factory)[
                DetailVM::class.java]

        val extra = intent.extras
        if(extra != null){
            val movId = extra.getString(EXTRA_MOVIE)
            val tvId = extra.getString(EXTRA_TVSHOW)
            if (movId!=null){
                viewModel.movSelected(movId)
                viewModel.movie.observe(this, {
                    movie -> when (movie.status){
                        Status.LOADING -> bindingContentDetail.progressBar
                            .visibility = View.VISIBLE
                        Status.SUCCESS -> if (movie.data !=null){
                            bindingContentDetail.progressBar.visibility =
                                View.GONE
                            populatedMov(movie.data)
                        }
                        Status.ERROR ->{
                            bindingContentDetail.progressBar.visibility = View.GONE

                        }
                    }
                })

            }
            if (tvId!=null){
                viewModel.tvSelected(tvId)
                viewModel.tvShow.observe(this, {
                        tvShow -> when (tvShow.status){
                    Status.LOADING -> bindingContentDetail.progressBar
                        .visibility = View.VISIBLE
                    Status.SUCCESS -> if (tvShow.data !=null){
                        bindingContentDetail.progressBar.visibility =
                            View.GONE
                        populatedTv(tvShow.data)
                    }
                    Status.ERROR ->{
                        bindingContentDetail.progressBar.visibility = View.GONE

                    }
                }
                })

            }

        }

    }
    private fun populatedMov(data: MovieEntity){
        var statusMovie = data.movieFav
        favState(statusMovie)
        bindingContentDetail.title.text = data.movieTitle
        bindingContentDetail.descriptionContent.text= data.movieDescription
        bindingContentDetail.genre.text=data.movieGenre
        bindingContentDetail.duration.text=data.movieDuration
        Glide.with(this).load(data.moviePicture)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(
                R.drawable.ic_baseline_error_24
            ))
            .into(bindingContentDetail.image)
        bindingContentDetail.favButton.setOnClickListener{
            statusMovie = !statusMovie
            favState(statusMovie)
            viewModel.setFavMovTv()
        }
    }
    private fun populatedTv(data: TvEntity){
        var statusTv =data.tvFav
        favState(statusTv)
        bindingContentDetail.title.text = data.tvTitle
        bindingContentDetail.descriptionContent.text= data.tvDescription
        bindingContentDetail.genre.text=data.tvGenre
        bindingContentDetail.duration.text=data.tvDuration
        Glide.with(this).load(data.tvPicture)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(
                R.drawable.ic_baseline_error_24
            ))
            .into(bindingContentDetail.image)
        bindingContentDetail.favButton.setOnClickListener {
            statusTv = !statusTv
            favState(statusTv)
            viewModel.setFavMovTv()
        }
    }

    private fun favState(state: Boolean){
        if (state){
            bindingContentDetail.favButton.setImageResource(R.drawable.favorited)
        } else{
            bindingContentDetail.favButton.setImageResource(R.drawable.fav)
        }
    }


}