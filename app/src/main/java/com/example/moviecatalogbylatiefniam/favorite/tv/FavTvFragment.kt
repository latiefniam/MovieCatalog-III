package com.example.moviecatalogbylatiefniam.favorite.tv

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.example.moviecatalogbylatiefniam.databinding.FragmentFavTvBinding
import com.example.moviecatalogbylatiefniam.favorite.FavoriteViewModel


import com.example.moviecatalogbylatiefniam.viewmodel.ViewModelFactory


class FavTvFragment : Fragment(){
    private lateinit var binding: FragmentFavTvBinding
    private lateinit var adapter: FavTvAdapter
    private lateinit var viewModel: FavoriteViewModel



    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null){
                val swipePositions = viewHolder.adapterPosition
                val tvEntity = adapter.swipeData(swipePositions)
                tvEntity?.let { viewModel.setTvFav(it) }
            }
        }

    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavTvBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewtvfav)

        if (activity !=null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            adapter = FavTvAdapter()
            viewModel.getTvFav().observe(viewLifecycleOwner, {tv->
                adapter.submitList(tv)
            })
            binding.recyclerviewtvfav.layoutManager = LinearLayoutManager(context)
            binding.recyclerviewtvfav.setHasFixedSize(true)
            binding.recyclerviewtvfav.adapter = adapter
        }
    }
}