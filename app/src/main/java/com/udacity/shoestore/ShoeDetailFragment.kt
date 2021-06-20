package com.udacity.shoestore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.udacity.shoestore.data.model.Shoe
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.view.shoeList.ShoeListViewModel
import kotlinx.android.synthetic.main.fragment_welcome.*
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentShoeDetailBinding
    private val shoesVM: ShoeListViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        binding.shoe = Shoe("Lizard",20.0, "ECCO","", ArrayList())
        binding.viewModel = shoesVM
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancel.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        binding.save.setOnClickListener {
            shoesVM.addShoe()
            Timber.i("Added shoe")
            Navigation.findNavController(it).navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                shoesVM.changeShoe(
                    Shoe(
                        binding.name.text.toString(),
                        binding.size.text.toString().toDoubleOrNull()?: 0.0,
                        binding.company.text.toString(),
                        binding.description.text.toString(),
                        ArrayList<String>()
                    )
                )
            }
        }
        binding.name.addTextChangedListener(afterTextChangedListener)
        binding.company.addTextChangedListener(afterTextChangedListener)
        binding.size.addTextChangedListener(afterTextChangedListener)
        binding.description.addTextChangedListener(afterTextChangedListener)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoeDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoeDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}