package wang.mycroft.disney.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module
import wang.mycroft.disney.ApplicationContext

@ComponentScan("wang.mycroft.disney")
@Module
class AppModule

fun appModule(applicationContext: ApplicationContext) = listOf(
    module {
        single<ApplicationContext> {
            applicationContext
        }
    },
    AppModule().module,
    CoroutinesDispatcherModule().module,
    NetworkModule().module,
    DatabaseModule().module,
    RepositoryModule().module,
)