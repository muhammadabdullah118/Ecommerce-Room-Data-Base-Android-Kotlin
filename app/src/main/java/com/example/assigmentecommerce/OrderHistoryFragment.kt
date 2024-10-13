package com.example.assigmentecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assigmentecommerce.databinding.FragmentOrderHistoryBinding


class OrderHistoryFragment : Fragment() , View.OnClickListener , OrderListener {

    private var _binding : FragmentOrderHistoryBinding ?=null
    val binding get() = _binding
    var viewModel : OrderVM ?=  null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderHistoryBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(OrderVM::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvOrderHistory?.layoutManager = LinearLayoutManager(requireContext())
        val adapter = OrderAdapter(requireContext(), this)
        binding?.rvOrderHistory?.adapter = adapter

        viewModel?.readAllData?.observe(viewLifecycleOwner, { order ->
            adapter.setData(order)
        })

        registerClicks()
    }

    private fun registerClicks() {
        binding?.buttonBack?.setOnClickListener(this)
        binding?.imageDelete?.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id ){
            R.id.buttonBack->{
                findNavController().navigateUp()
            }
            R.id.imageDelete->{
                viewModel?.deleteAllOrderHistroy()
            }
        }
    }
    override fun onUpdateClick(order: Order) {
        findNavController().navigate(OrderHistoryFragmentDirections.actionOrderHistoryFragmentToOrderUpdateFragment(order))
    }

}