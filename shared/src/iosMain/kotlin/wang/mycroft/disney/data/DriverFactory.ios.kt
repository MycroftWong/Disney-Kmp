package wang.mycroft.disney.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import wang.mycroft.disney.ApplicationContext

actual class DriverFactory actual constructor(applicationContext: ApplicationContext) {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "test.db")
    }
}