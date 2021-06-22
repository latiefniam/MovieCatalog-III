package com.example.moviecatalogbylatiefniam.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviecatalogbylatiefniam.databinding.FragmentTvBinding
import com.example.moviecatalogbylatiefniam.viewmodel.ViewModelFactory
import com.example.moviecatalogbylatiefniam.vo.Status


class TvFragment: Fragment(){
    private lateinit var binding: FragmentTvBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBinding.inflate(inflater,container,false)
        return binding.root  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val factoryTv = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this,factoryTv)[TvVm::class.java]

            val tvAdapter= TvAdapter()

            viewModel.getTvShows().observe(viewLifecycleOwner,
                {tvShow->
                    if (tvShow!=null){
                        when(tvShow.status){
                            Status.SUCCESS->{
                                tvAdapter.submitList(tvShow.data)
                                tvAdapter.notifyDataSetChanged()
                            }
                            else -> Fragment()
                        }

                    }

                })



            with(binding.recyclerviewtv){
                layoutManager =LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding
    }

}
