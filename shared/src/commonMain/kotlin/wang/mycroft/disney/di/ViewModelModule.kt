package wang.mycroft.disney.di

import androidx.lifecycle.SavedStateHandle
import org.koin.dsl.module
import wang.mycroft.disney.ui.disney.viewmodel.DisneyDetailViewModel
import wang.mycroft.disney.ui.disney.viewmodel.DisneyViewModel

fun ViewModelModule() = module {
    factory {
        DisneyViewModel(get(), get(), get())
    }
    factory { (handle: SavedStateHandle) ->
        DisneyDetailViewModel(get(), get(), handle)
    }
}