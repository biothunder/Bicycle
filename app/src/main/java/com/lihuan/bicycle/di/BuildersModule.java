package com.lihuan.bicycle.di;


import com.lihuan.bicycle.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
	@ActivityScope
	@ContributesAndroidInjector(modules = MainModule.class)
	abstract MainActivity bindMainModule();
}
