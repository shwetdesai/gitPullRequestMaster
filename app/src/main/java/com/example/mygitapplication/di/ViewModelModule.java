package com.example.mygitapplication.di;

import androidx.lifecycle.ViewModel;

import com.example.mygitapplication.viewModel.MainFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel.class)
    abstract ViewModel bindMainFragmentViewModel(MainFragmentViewModel viewModel);
}
