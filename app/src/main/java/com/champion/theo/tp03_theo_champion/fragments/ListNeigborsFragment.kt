package com.champion.theo.tp03_theo_champion.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.champion.theo.tp03_theo_champion.MainActivity
import com.champion.theo.tp03_theo_champion.R
import com.champion.theo.tp03_theo_champion.contracts.EventCrudable
import com.champion.theo.tp03_theo_champion.adapters.ListNeighborsAdapter
import com.champion.theo.tp03_theo_champion.contracts.NavigationListener
import com.champion.theo.tp03_theo_champion.databinding.ListNeighborsFragmentBinding
import com.champion.theo.tp03_theo_champion.models.Neighbor
import com.champion.theo.tp03_theo_champion.repositories.NeighborRepository

class ListNeigborsFragment : Fragment(), EventCrudable<Neighbor> {
    private var _binding: ListNeighborsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) {
            context.updateTitle(R.string.title_neighbor_list)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ListNeighborsFragmentBinding.inflate(inflater, container, false)
        binding.neighborsList.layoutManager = LinearLayoutManager(context)
        binding.neighborsList.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().all()
        val adapter = ListNeighborsAdapter(neighbors, this)
        binding.neighborsList.adapter = adapter

        this.initEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDelete(model: Neighbor) {
        NeighborRepository.getInstance().delete(model)
    }

    private fun initEvents() {
        this.onClickAddNeighborButton()
    }

    private fun onClickAddNeighborButton() {
        this.binding.addNeighborButton.setOnClickListener(View.OnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
            }
        })
    }
}