package by.pupil.domain

import java.time.LocalDateTime

data class User(
    val id: Int,
    val name: String,
    val lastActivityDateTime: LocalDateTime,
)
