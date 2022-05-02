package by.pupil

import by.pupil.converter.RequestConverter
import org.koin.dsl.module

val webModule = module(createdAtStart = true) {
    single { RequestConverter() }
}
