package com.sachin.workindiaassessment.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.workindiaassessment.R
import com.sachin.workindiaassessment.databinding.FragmentFirstBinding
import com.sachin.workindiaassessment.databinding.FragmentSecondBinding
import com.sachin.workindiaassessment.ui.adapters.GridItemAdapter
import com.sachin.workindiaassessment.ui.adapters.LinearItemAdapter
import com.sachin.workindiaassessment.ui.viewmodels.ItemViewModel
import com.sachin.workindiaassessment.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ItemViewModel by viewModels()
    var gridItemAdapter: GridItemAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        viewModel.getAllItems()

        viewModel.getItemData().observe(viewLifecycleOwner, Observer {itemResponse ->

            when(itemResponse){
                is Resource.Success ->{
                    hideProgressbar()
                    itemResponse.data?.let {
                        gridItemAdapter = GridItemAdapter(it.data.items)

                        binding.apply {
                            rvItemList.layoutManager = GridLayoutManager(activity, 3)
                            rvItemList.adapter = gridItemAdapter
                        }
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


        return binding.root
    }

    private fun showProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.progressbar.visibility = View.GONE
    }

}