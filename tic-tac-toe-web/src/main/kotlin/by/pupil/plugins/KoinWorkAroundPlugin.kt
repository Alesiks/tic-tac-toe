package by.pupil.plugins

import io.ktor.routing.*
import io.ktor.server.application.*
import io.ktor.util.*
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.KoinAppDeclaration

// took workaround from here https://gist.github.com/MarkusNowotny/c245b5f493bfdde09d7b9853ef91fb55
// to make koin:3.1.6 and ktor:2.0.0 work together

inline fun <reified T : Any> ApplicationCall.inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy { get<T>(qualifier, parameters) }

inline fun <reified T : Any> ApplicationCall.get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = getKoin().get<T>(qualifier, parameters)

fun <T : Any> ApplicationCall.getProperty(key: String) = getKoin().getProperty<T>(key)

fun ApplicationCall.getProperty(key: String, defaultValue: String) = getKoin().getProperty(key) ?: defaultValue

fun ApplicationCall.getKoin() = GlobalContext.get()

val KoinApplicationStarted = EventDefinition<KoinApplication>()
val KoinApplicationStopPreparing = EventDefinition<KoinApplication>()
val KoinApplicationStopped = EventDefinition<KoinApplication>()

fun Application.getKoin(): Koin = GlobalContext.get()

inline fun <reified T : Any> Application.inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy { get<T>(qualifier, parameters) }

inline fun <reified T : Any> Application.get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = getKoin().get<T>(qualifier, parameters)

fun <T : Any> Application.getProperty(key: String) = getKoin().getProperty<T>(key)

fun Application.getProperty(key: String, defaultValue: String) = getKoin().getProperty(key) ?: defaultValue

inline fun <reified T : Any> Route.inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy { get<T>(qualifier, parameters) }

inline fun <reified T : Any> Route.get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = getKoin().get<T>(qualifier, parameters)

fun <T : Any> Route.getProperty(key: String) = getKoin().getProperty<T>(key)

fun Route.getProperty(key: String, defaultValue: String) = getKoin().getProperty(key) ?: defaultValue

fun Route.getKoin() = GlobalContext.get()

inline fun <reified T : Any> Routing.inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy { get<T>(qualifier, parameters) }

inline fun <reified T : Any> Routing.get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = getKoin().get<T>(qualifier, parameters)

fun <T : Any> Routing.getProperty(key: String) = getKoin().getProperty<T>(key)

inline fun <reified T> Routing.getProperty(key: String, defaultValue: T) = getKoin().getProperty(key) ?: defaultValue

fun Routing.getKoin() = GlobalContext.get()

class KoinConfig {
    internal var modules: ArrayList<org.koin.core.module.Module> = ArrayList()

    operator fun org.koin.core.module.Module.unaryPlus() {
        modules.add(this)
    }

    operator fun List<org.koin.core.module.Module>.unaryPlus() {
        modules.addAll(this)
    }
}

val Koin = createApplicationPlugin(name = "Koin", createConfiguration = ::KoinConfig) {
    println("Koin is installed!")
    val declaration: KoinAppDeclaration = {
        modules(pluginConfig.modules)
    }
    val koinApplication = startKoin(appDeclaration = declaration)
    environment?.monitor?.let { monitor ->
        monitor.raise(KoinApplicationStarted, koinApplication)
        monitor.subscribe(ApplicationStopped) {
            monitor.raise(KoinApplicationStopPreparing, koinApplication)
            stopKoin()
            monitor.raise(KoinApplicationStopped, koinApplication)
        }
    }
}
