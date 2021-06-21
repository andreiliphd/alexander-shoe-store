package com.udacity.shoestore.viewModel.shoeList

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.model.Shoe
import timber.log.Timber

class ShoeListViewModel: ViewModel() {
    private val _shoe: MutableLiveData<Shoe> = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe> = _shoe
    private val _shoes: MutableLiveData<ArrayList<Shoe>> = MutableLiveData<ArrayList<Shoe>>()
    val shoes: LiveData<ArrayList<Shoe>> = _shoes
    init {
        _shoes.value = ArrayList()
        _shoe.value = Shoe("", 0.0, "","", ArrayList<String>())
    }

    fun addShoe() {
        Timber.d("Inside ShoeListViewModel")
        _shoes.value?.add(shoe.value!!)
        _shoes.value = _shoes.value
    }

    fun changeShoe(changedShoe: Shoe) {
        _shoe.value = changedShoe
    }

    fun changeName(name: Editable) {
        _shoe.value?.name = name.toString()
    }

    fun changeCompany(company: Editable) {
        _shoe.value?.company = company.toString()
    }

    fun changeSize(size: Editable) {
        _shoe.value?.size = size.toString().toDouble()
    }
    fun changeDescription(description: Editable) {
        _shoe.value?.description = description.toString()
    }


}