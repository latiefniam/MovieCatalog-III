package com.example.moviecatalogbylatiefniam.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogbylatiefniam.data.source.local.entity.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.local.entity.TvEntity
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.vo.Resource

class DetailVM(private val repository: Repository): ViewModel() {

    private  val movId= MutableLiveData<String>()
    private val  tvId = MutableLiveData<String>()

    fun movSelected (movId: String){
        this.movId.value= movId
    }
    fun tvSelected(tvId: String){
        this.tvId.value = tvId
    }

    var movie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movId){
            mMovId -> repository.getMovieId(mMovId)
        }
    var tvShow: LiveData<Resource<TvEntity>> =
        Transformations.switchMap(tvId){
                mTvId -> repository.getTvId(mTvId)
        }
    fun setFavMovTv(){
        val movFav = movie.value
        val tvFav = tvShow.value

        if (movFav != null){
            val movData = movFav.data

            if(movData != null){
                val newState = !movData.movieFav
                repository.setMovFav(movData, newState)
            }
        }
        if (tvFav != null){
            val tvData = tvFav.data
            if (tvData != null){
                val newState = !tvData.tvFav
                repository.setTvFav(tvData, newState)
            }
        }
    }
}