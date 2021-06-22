package com.example.moviecatalogbylatiefniam.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogbylatiefniam.databinding.FragmentMoviesBinding
import com.example.moviecatalogbylatiefniam.viewmodel.ViewModelFactory
import com.example.moviecatalogbylatiefniam.vo.Status


class MoviesFragment: Fragment(){

    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val factoryMov = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this,factoryMov)[MoviesVm::class.java]

            val adapterMovie = MoviesAdapter()
            viewModel.getMovies().observe(viewLifecycleOwner, {
                movie ->
                if (movie!=null){
                    when(movie.status){
                        Status.SUCCESS -> {
                            adapterMovie.submitList(movie.data)
                            adapterMovie.notifyDataSetChanged()
                        } else ->Fragment()
                    }
                }
                adapterMovie.notifyDataSetChanged()
            })


            with(binding.recyclerviewmovie){
                layoutManager =LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapterMovie
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }

}