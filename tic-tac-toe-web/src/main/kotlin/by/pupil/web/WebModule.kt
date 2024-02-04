package by.pupil.web

import by.pupil.web.converter.RequestConverter
import org.koin.dsl.module

val webModule = module(createdAtStart = true) {
    single { RequestConverter() }
}
