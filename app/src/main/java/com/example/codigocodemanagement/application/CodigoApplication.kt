package com.example.codigocodemanagement.application

import android.app.Application
import com.example.codigocodemanagement.repository.MovieRepository
import com.example.codigocodemanagement.room.MovieDB
import com.example.codigocodemanagement.service.RetrofitObj
import com.example.codigocodemanagement.utility.ViewModelFactory
import com.example.codigocodemanagement.viewmodel.MovieViewModel
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class CodigoApplication : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@CodigoApplication))

        //vmFactory
        bindSingleton { ViewModelFactory(di.direct) }

        //apiservice
        bindSingleton { RetrofitObj.API_SERVICE }

        //database
        bindSingleton { MovieDB.getInstance(instance()) }

        //repositories
        bindSingleton { MovieRepository(instance(), instance(), instance()) }


        //viewmodels
        bind<MovieViewModel>(MovieViewModel::class.java.simpleName) with provider {
            MovieViewModel(
                instance()
            )
        }

    }
}