package wang.mycroft.disney.data

import app.cash.sqldelight.db.SqlDriver
import wang.mycroft.disney.ApplicationContext

expect class DriverFactory(applicationContext: ApplicationContext) {
    fun createDriver(): SqlDriver
}
