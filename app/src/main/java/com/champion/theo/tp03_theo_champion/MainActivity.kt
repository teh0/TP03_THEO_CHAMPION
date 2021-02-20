package com.champion.theo.tp03_theo_champion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.champion.theo.tp03_theo_champion.contracts.NavigationListener
import com.champion.theo.tp03_theo_champion.databinding.ActivityMainBinding
import com.champion.theo.tp03_theo_champion.databinding.ListNeighborsFragmentBinding
import com.champion.theo.tp03_theo_champion.fragments.ListNeigborsFragment

class MainActivity : AppCompatActivity(), NavigationListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    //private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        //toolbar = binding.toolbar
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        showFragment(ListNeigborsFragment())
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        binding.toolbar.setTitle(title)
    }
}