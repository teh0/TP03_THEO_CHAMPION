package com.champion.theo.tp03_theo_champion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.champion.theo.tp03_theo_champion.databinding.ActivityMainBinding
import com.champion.theo.tp03_theo_champion.databinding.ListNeighborsFragmentBinding
import com.champion.theo.tp03_theo_champion.fragments.ListNeigborsFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(ListNeigborsFragment())
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }
}