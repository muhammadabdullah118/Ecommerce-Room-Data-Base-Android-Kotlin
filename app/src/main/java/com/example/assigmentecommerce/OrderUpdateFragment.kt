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
import com.example.assigmentecommerce.databinding.FragmentOrderUpdateBinding

class OrderUpdateFragment : Fragment() , View.OnClickListener {

    private var _binding : FragmentOrderUpdateBinding ? = null
    val binding get() = _binding
    var viewModel : OrderVM?=null
    private val args : OrderUpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      _binding= FragmentOrderUpdateBinding.inflate(inflater, container,false)
      viewModel= ViewModelProvider(this).get(OrderVM::class.java)
      return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.getOrderName?.text = args.order.order_name
        binding?.getOrderPrice?.text= args.order.order_price.toString()
        binding?.getOrderQuantity?.text= args.order.order_quantity.toString()

        registerClicks()
    }

    private fun registerClicks() {
        binding?.buttonBackTwo?.setOnClickListener(this)
        binding?.buttonUpdateOrder?.setOnClickListener(this)
        binding?.buttonOrderMinus?.setOnClickListener(this)
        binding?.buttonOrderPlus?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //values from layout
        var quantity = binding?.getOrderQuantity?.text.toString().toInt()
        var price = binding?.getOrderPrice?.text.toString().toInt()

        when(v?.id){
            R.id.buttonBackTwo->{
                findNavController().navigateUp()
            }
            R.id.buttonOrderMinus->{
                if(quantity > 1 ){
                    val new_price=price/quantity
                    quantity = quantity-1
                    price =price-new_price
                    binding?.getOrderQuantity?.text=quantity.toString()
                    binding?.getOrderPrice?.text=price.toString()
            }
            }
            R.id.buttonOrderPlus->{
                if(quantity < 3 ) {
                    val new_price=price/quantity
                    quantity = quantity + 1
                    price = price + new_price
                    binding?.getOrderQuantity?.text = quantity.toString()
                    binding?.getOrderPrice?.text = price.toString()
                }
            }
            R.id.buttonUpdateOrder->{
                viewModel?.updateOrder(
                    Order(
                        order_id = args.order.order_id,
                        order_name = binding?.getOrderName?.text.toString(),
                        order_price = binding?.getOrderPrice?.text.toString().toInt(),
                        order_quantity = binding?.getOrderQuantity?.text.toString().toInt()
                    )
                )
                findNavController().navigate(OrderUpdateFragmentDirections.actionOrderUpdateFragmentToOrderHistoryFragment())
            }
        }
    }

}