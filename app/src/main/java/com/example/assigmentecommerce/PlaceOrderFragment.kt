package com.example.assigmentecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assigmentecommerce.databinding.FragmentPlaceOrderBinding


class PlaceOrderFragment : Fragment() , View.OnClickListener {

    private var _binding  : FragmentPlaceOrderBinding ?=null
    val binding get() = _binding
    private val args : PlaceOrderFragmentArgs by navArgs()
    var quantity : Int = 1
    var viewModel : OrderVM?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlaceOrderBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(OrderVM::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.getName?.text = args.item.name
        binding?.getPrice?.text = args.item.price.toString()

        registerClicks()
        initialize()
    }

    private fun initialize() {
        binding?.getQuantity?.text=quantity.toString()
    }

    private fun registerClicks() {
        binding?.buttonBackTwo?.setOnClickListener(this)
        binding?.buttonMinus?.setOnClickListener(this)
        binding?.buttonPlus?.setOnClickListener(this)
        binding?.buttonConfirmOrder?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var price = args.item.price

        when(v?.id){
            R.id.buttonBackTwo->{
                findNavController().navigateUp()
            }
            R.id.buttonMinus->{
                if(quantity > 1 ){
                    quantity = quantity-1
                    price =price*quantity
                    binding?.getQuantity?.text=quantity.toString()
                    binding?.getPrice?.text=price.toString()
                }
            }
            R.id.buttonPlus->{
                if(quantity < 3) {
                    quantity = quantity + 1
                    price = price * quantity
                    binding?.getQuantity?.text = quantity.toString()
                    binding?.getPrice?.text = price.toString()
                }
            }
            R.id.buttonConfirmOrder->{
                viewModel?.addOrder(
                    Order(
                        0,
                        binding?.getName?.text.toString(),
                        binding?.getPrice?.text.toString().toInt(),
                        binding?.getQuantity?.text.toString().toInt()
                    )
                )
                findNavController().navigate(PlaceOrderFragmentDirections.actionPlaceOrderFragmentToItemListFragment())
            }
            }

    }


}