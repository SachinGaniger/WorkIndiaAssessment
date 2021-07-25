package com.sachin.workindiaassessment.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.workindiaassessment.R
import com.sachin.workindiaassessment.databinding.FragmentFirstBinding
import com.sachin.workindiaassessment.ui.adapters.LinearItemAdapter
import com.sachin.workindiaassessment.ui.viewmodels.ItemViewModel
import com.sachin.workindiaassessment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {

    lateinit var binding: FragmentFirstBinding
    private val viewModel: ItemViewModel  by viewModels()
    var linearItemAdapter: LinearItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        viewModel.getAllItems()

        viewModel.getItemData().observe(viewLifecycleOwner, Observer {itemResponse ->

            when(itemResponse){
                is Resource.Success ->{
                    hideProgressbar()
                    itemResponse.data?.let {
                        linearItemAdapter = LinearItemAdapter(it.data.items)
                        binding.rvItemList.adapter = linearItemAdapter

                    }

                }

                is Resource.Loading ->{
                    showProgressBar()
                }

                is Resource.Error ->{
                    hideProgressbar()
                    Toast.makeText(activity, itemResponse.message, Toast.LENGTH_SHORT).show()
                }
            }

        })

        binding.rvItemList.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

    private fun showProgressBar() {
        binding.progressbar.visibility = VISIBLE
    }

    private fun hideProgressbar() {
            binding.progressbar.visibility = GONE
    }


}