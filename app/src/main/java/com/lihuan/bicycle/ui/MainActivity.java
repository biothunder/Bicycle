package com.lihuan.bicycle.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lihuan.bicycle.R;
import com.lihuan.bicycle.databinding.MainActivityBinding;
import com.lihuan.bicycle.model.Bicycle;
import com.lihuan.bicycle.viewmodel.MainViewModel;
import com.lihuan.bicycle.viewmodel.MainViewModelFactory;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {
    @Inject
    MainViewModelFactory mainViewModelFactory;
    MainViewModel mainViewModel;

    private MainActivityBinding binding;
    private Bicycle bicycle = Bicycle.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setBicycle(bicycle);

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory)
                .get(MainViewModel.class);

        mainViewModel.speed().observe(this, bicycle::setSpeed);
        mainViewModel.powerAssisted().observe(this, bicycle::setPowerAssisted);
        mainViewModel.slope().observe(this, bicycle::setSlope);
    }
}
