package com.ilyamarvin.pizzamobileapp.ui.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//class MenuViewModel(application: Application, val testText: String) : AndroidViewModel(application) {
//
//    private val _text = MutableLiveData<String>().apply {
//        value = "This is Menu Fragment"
//    }
//    val text: LiveData<String> = _text
//}

class MenuViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is contacts Fragment"
    }
    val text: LiveData<String> = _text

}