package com.example.codigocodemanagement.utility

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DirectDI
import org.kodein.di.instanceOrNull

class ViewModelFactory(private val direct: DirectDI) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return direct.instanceOrNull<ViewModel>(modelClass.simpleName) as T?
            ?: modelClass.newInstance()
    }
}