package com.wwm.trackappsyncapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.wwm.trackappsyncapp.databinding.FragmentItemBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemBinding
    private lateinit var viewModel: ItemFragmentViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val viewModelFactory = ItemFragmentViewModelFactory()
        binding = FragmentItemBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ItemFragmentViewModel::class.java)
        binding.viewModel = viewModel
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_done).setOnClickListener {
            val item = TrackItemModel(
                binding.userIdInput.text.toString(),
                binding.pinInput.text.toString()
            )
            viewModel.addItem(item)
            hideKeyboard()
            findNavController().navigate(R.id.action_ItemFragment_to_HomeFragment)
        }
    }

    private fun hideKeyboard() {
        activity?.let {
            val inputMethodManager = it.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            it.currentFocus?.let { currentFocus ->
                inputMethodManager.hideSoftInputFromWindow(currentFocus.applicationWindowToken, 0)
            }
        }
    }
}
