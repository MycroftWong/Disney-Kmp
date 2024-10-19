package wang.mycroft.disney.di

import org.koin.dsl.module
import wang.mycroft.disney.ui.disney.viewmodel.DisneyDetailViewModel
import wang.mycroft.disney.ui.disney.viewmodel.DisneyViewModel

fun ViewModelModule() = module {
    factory {
        DisneyViewModel(get(), get(), get())
    }
    factory { (characterId: Long) ->
        DisneyDetailViewModel(get(), get(), characterId)
    }
}