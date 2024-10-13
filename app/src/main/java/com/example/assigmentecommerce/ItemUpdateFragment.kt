
package com.example.assigmentecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.assigmentecommerce.Item
import com.example.assigmentecommerce.databinding.FragmentItemUpdateBinding

class ItemUpdateFragment : Fragment() , View.OnClickListener  {

    private var _binding : FragmentItemUpdateBinding ?= null
    val binding get() = _binding
    var viewModel : ItemVM ?=  null
    private val args : ItemUpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentItemUpdateBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(ItemVM::class.java)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.edItemName?.setText(args.item.name)
        binding?.edItemPrice?.setText(args.item.price.toString())
        binding?.edItemDesc?.setText(args.item.description)

        registerClicks()
    }

    private fun registerClicks() {
        binding?.ButtonUpdate?.setOnClickListener(this)
        binding?.buttonBack?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v ?.id){
            R.id.ButtonUpdate->{
                viewModel?.updateItem(
                    Item(
                        id = args.item.id,
                        name = binding?.edItemName?.text.toString(),
                        price = binding?.edItemPrice?.text.toString().toInt(),
                        description = binding?.edItemDesc?.text.toString()
                    )
                )
                findNavController().navigate(ItemUpdateFragmentDirections.actionItemUpdateFragmentToItemListFragment())
            }
            R.id.buttonBack->{
                findNavController().navigateUp()
            }
        }
    }

}