package wang.mycroft.disney.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import wang.mycroft.disney.ApplicationContext

actual class DriverFactory actual constructor(private val applicationContext: ApplicationContext) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, applicationContext.application, "test.db")
    }
}
