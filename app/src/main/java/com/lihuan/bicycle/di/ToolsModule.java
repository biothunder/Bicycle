package com.lihuan.bicycle.di;

import android.content.Context;

import com.google.gson.Gson;
import com.lihuan.bicycle.App;
import com.lihuan.bicycle.model.Bicycle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ToolsModule {
	@Provides
    Context provideContext(App application) {
		return application.getApplicationContext();
	}

	@Singleton
	@Provides
	Gson provideGson() {
		return new Gson();
	}
}
