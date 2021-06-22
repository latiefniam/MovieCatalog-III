package com.example.moviecatalogbylatiefniam.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.moviecatalogbylatiefniam.R

import com.example.moviecatalogbylatiefniam.SectionsPagerAdapter
import com.example.moviecatalogbylatiefniam.databinding.ActivityHomeBinding
import com.example.moviecatalogbylatiefniam.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPager = SectionsPagerAdapter(this, supportFragmentManager)
        binding.pager.adapter = sectionPager
        binding.tabs.setupWithViewPager(binding.pager)
        supportActionBar?.setTitle(R.string.appname)

        supportActionBar?.elevation=0f


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favButtonItem){
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        return super.onCreateOptionsMenu(menu)
    }
}