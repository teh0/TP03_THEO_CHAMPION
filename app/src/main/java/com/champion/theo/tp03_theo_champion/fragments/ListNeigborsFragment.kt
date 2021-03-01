package com.champion.theo.tp03_theo_champion.fragments

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.champion.theo.tp03_theo_champion.R
import com.champion.theo.tp03_theo_champion.contracts.EventCrudable
import com.champion.theo.tp03_theo_champion.adapters.ListNeighborsAdapter
import com.champion.theo.tp03_theo_champion.contracts.NavigationListener
import com.champion.theo.tp03_theo_champion.databinding.ListNeighborsFragmentBinding
import com.champion.theo.tp03_theo_champion.di.DI
import com.champion.theo.tp03_theo_champion.models.Neighbor
import com.champion.theo.tp03_theo_champion.repositories.NeighborRepository
import com.champion.theo.tp03_theo_champion.viewmodels.NeighborViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ListNeigborsFragment : Fragment(), EventCrudable<Neighbor> {
    /**
     * View model
     */
    private lateinit var viewModel: NeighborViewModel

    /**
     * View binding
     */
    private var _binding: ListNeighborsFragmentBinding? = null
    private val binding get() = _binding!!

    /**
     * Thread executor
     */
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    /**
     * On create
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
    }

    /**
     * On attach
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) {
            context.updateTitle(R.string.title_neighbor_list)
        }
    }

    /**
     * On create view
     */
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

    /**
     * On view created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

        this.initEvents()
    }

    /**
     * On destroy view
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * On delete
     */
    override fun onDelete(model: Neighbor) {
        executorService.execute {
            DI.repository.delete(model)
        }
    }

    /**
     * Init events
     */
    private fun initEvents() {
        this.onClickAddNeighborButton()
    }

    /**
     * Init data
     */
    private fun setData() {
        val adapter = ListNeighborsAdapter(this)
        binding.neighborsList.adapter = adapter

        viewModel.neighbors.observe(
            viewLifecycleOwner,
            Observer<List<Neighbor>> { t ->
                adapter.setNeighbours(t)
            }
        )
    }

    /**
     * Click event handler for add neighbour button
     */
    private fun onClickAddNeighborButton() {
        this.binding.addNeighborButton.setOnClickListener(View.OnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
            }
        })
    }
}