package by.pupil.db

import org.jetbrains.exposed.sql.Database

object DbSettings {

    val db by lazy {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "org.h2.Driver")
    }
}
