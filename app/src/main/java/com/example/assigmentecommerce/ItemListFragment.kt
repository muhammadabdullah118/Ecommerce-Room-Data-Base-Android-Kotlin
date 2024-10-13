package com.example.assigmentecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assigmentecommerce.databinding.FragmentItemListBinding


class ItemListFragment : Fragment() , View.OnClickListener , ItemListener {

    private var _binding : FragmentItemListBinding ?= null
    val binding get() = _binding
    var viewModel : ItemVM?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentItemListBinding.inflate(inflater, container , false)
        viewModel=ViewModelProvider(this).get(ItemVM::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvItem?.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ItemListAdapter(requireContext(), this)
        binding?.rvItem?.adapter = adapter

        viewModel?.readAllData?.observe(viewLifecycleOwner, { item ->
            adapter.setData(item)
        })

        registerClick()
    }

    private fun registerClick() {
        binding?.buttonInsert?.setOnClickListener(this)
        binding?.buttonOrderHistory?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonInsert->{
                findNavController().navigate(ItemListFragmentDirections.actionItemListFragmentToAddItemFragment())
            }
            R.id.buttonOrderHistory->{
                findNavController().navigate(ItemListFragmentDirections.actionItemListFragmentToOrderHistoryFragment())
            }
        }
    }

    override fun onUpdateClick(item: Item) {
        findNavController().navigate(ItemListFragmentDirections.actionItemListFragmentToItemUpdateFragment(item))
    }

    override fun onDeleteClick(item: Item) {
        viewModel?.deleteItem(item)
    }

    override fun onItemClick(item: Item) {
        findNavController().navigate(ItemListFragmentDirections.actionItemListFragmentToItemDetailsFragment(item))
    }


}