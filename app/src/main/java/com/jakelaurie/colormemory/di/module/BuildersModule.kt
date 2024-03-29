package com.jakelaurie.colormemory.di.module

import com.jakelaurie.colormemory.di.scope.ActivityScope
import com.jakelaurie.colormemory.ui.game.GameActivity
import com.jakelaurie.colormemory.ui.game.di.GameFragmentProvider
import com.jakelaurie.colormemory.ui.highscore.HighscoreActivity
import com.jakelaurie.colormemory.ui.highscore.di.HighscoreFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(
            modules = [
                GameActivityModule::class,
                GameFragmentProvider::class
            ]
    )
    abstract fun contributeGameActivity(): GameActivity

    @ActivityScope
    @ContributesAndroidInjector(
            modules = [
                HighscoreActivityModule::class,
                HighscoreFragmentProvider::class
            ]
    )
    abstract fun contributeHighscoreActivity(): HighscoreActivity
}