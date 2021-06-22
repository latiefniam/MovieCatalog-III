package com.example.moviecatalogbylatiefniam.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogbylatiefniam.R

import com.example.moviecatalogbylatiefniam.databinding.ActivityFavoriteBinding


class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.favorit)

        val favSectionsPager = FavSectionPager(this,supportFragmentManager)
        binding.viewPagerFav.adapter = favSectionsPager
        binding.tabsFav.setupWithViewPager(binding.viewPagerFav)

        supportActionBar?.elevation=0f
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}