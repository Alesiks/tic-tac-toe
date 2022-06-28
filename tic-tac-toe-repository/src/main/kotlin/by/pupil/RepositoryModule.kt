package by.pupil

import by.pupil.db.DbSettings
import by.pupil.query.GamesRepository
import by.pupil.service.PersonToAIGameService
import by.pupil.service.PersonToPersonGameService
import org.koin.dsl.module

val repositoryModule = module(createdAtStart = true) {
    single { GamesRepository(DbSettings) }
    single { PersonToAIGameService(repository = get()) }
    single { PersonToPersonGameService(repository = get()) }
}
