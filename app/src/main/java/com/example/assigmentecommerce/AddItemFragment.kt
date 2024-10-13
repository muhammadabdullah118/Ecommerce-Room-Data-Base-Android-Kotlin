package com.example.assigmentecommerce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assigmentecommerce.databinding.FragmentAddItemBinding


class AddItemFragment : Fragment() , View.OnClickListener {

    private var _binding : FragmentAddItemBinding ?=null
    val binding get() = _binding
    var viewModel : ItemVM ?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      _binding=FragmentAddItemBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(ItemVM::class.java)
      return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerClick()
    }

    private fun registerClick() {
        binding?.buttonAddItem?.setOnClickListener(this)
        binding?.buttonBack?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){

            R.id.buttonAddItem->{
                val itemName = binding?.edItemName?.text.toString()
                val itemPrice = binding?.edItemPrice?.text.toString()
                if(itemName.isEmpty() || itemPrice.isEmpty()){
                    Toast.makeText(context,"Empty Fields",Toast.LENGTH_SHORT).show()
                }
                else {
                    viewModel?.addItem(
                        Item(
                            0,
                            binding?.edItemName?.text.toString(),
                            binding?.edItemPrice?.text.toString().toInt(),
                            binding?.edItemDesc?.text.toString()
                        )
                    )
                    findNavController().navigate(AddItemFragmentDirections.actionAddItemFragmentToItemListFragment())
                }
            }

            R.id.buttonBack->{
                findNavController().navigateUp()
            }
        }
    }
}