package com.example.assigmentecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assigmentecommerce.databinding.FragmentItemDetailsBinding
import com.example.assigmentecommerce.databinding.FragmentItemUpdateBinding


class ItemDetailsFragment : Fragment() , View.OnClickListener{

    private var _binding : FragmentItemDetailsBinding ?=null
    val binding get() = _binding
    private val args : ItemDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentItemDetailsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.getName?.text = args.item.name
        binding?.getPrice?.text = args.item.price.toString()
        binding?.getDescription?.text = args.item.description

        registerClicks()
    }

    private fun registerClicks() {
        binding?.buttonBack?.setOnClickListener(this)
        binding?.buttonPlaceOrder?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonBack->{
                findNavController().navigateUp()
            }
            R.id.buttonPlaceOrder->{
                findNavController().navigate(ItemDetailsFragmentDirections.actionItemDetailsFragmentToPlaceOrderFragment(args.item))
            }
        }
    }

}