package wang.mycroft.disney.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val Dispatchers.App: CoroutineDispatcher
    get() = IO
