package com.example.myapplication.utility.di

import com.example.myapplication.viewModel.TaskEntryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    ///   private val loginAndRegisterViewModel by viewModel<LoginAndRegisterViewModel>()  --> inject activity or fragment and so on
    viewModel { TaskEntryListViewModel() }
}