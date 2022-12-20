package dgtic.unam.juego

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

fun main() {
    //println(Thread.currentThread().name)
    //coroutines()
    dispatchers()
}

/*
fun coroutines() {
    GlobalScope.launch {
        while (true) {
            println("Codigo de la rutina ${Thread.currentThread().name} ejecutando")
        }
    }
    Thread.sleep(1000)
}*/

/*fun coroutines() {
    runBlocking {
        val result = async {
            println("Inicia ${Thread.currentThread().name}")
            println("Codigo en ejecucion")
            delay(3000)
            println("Termina ${Thread.currentThread().name}")

            // Devolucion de resultados
            "Resultado de datos"
        }

        println("Esperando resultado")
        println("Resultado = ${result.await()}")
        delay(2000)
        println("Termina ${Thread.currentThread().name}")
    }
}*/

/*
fun coroutines() {
    runBlocking {
        val job = launch {
            println("Inicia 1 ${Thread.currentThread().name}\n")
            delay(6000)
            println("Ejecutando instrucciones")
            println("Termina 1 ${Thread.currentThread().name}")
        }

        launch {
            while (true) {
                delay(1000)
                println("Esta activo: ${job.isActive}")
                println("Esta cancelado: ${job.isCancelled}")
                println("Esta completado: ${job.isCompleted}\n")
                // Codigo para terminar
                if ( (1 .. 5).shuffled().first() == 3 ) {
                    println("Cancelar job")
                    job.cancel()
                }
                if (job.isCompleted || job.isCancelled)
                    return@launch
            }
        }
    }
}*/

/*
fun coroutines() {
    runBlocking {
        val deferred = async {
            println("Inicia 1 ${Thread.currentThread().name}\n")
            delay(6000)
            println("Ejecutando instrucciones")
            println("Termina 1 ${Thread.currentThread().name}")

            45
        }

        launch {
            while (true) {
                delay(1000)
                println("Esta activo: ${deferred.isActive}")
                println("Esta cancelado: ${deferred.isCancelled}")
                println("Esta completado: ${deferred.isCompleted}\n")
                // Codigo para terminar
                if ( (1 .. 5).shuffled().first() == 3 ) {
                    println("Cancelar job")
                    deferred.cancel()
                }
                // Obtencion del valor
                if ( (1 .. 3).shuffled().first() == 1 ) {
                    println("Esperando valor ${deferred.await()}")
                }

                if (deferred.isCompleted || deferred.isCancelled)
                    return@launch
            }
        }
    }
}
*/

/*fun coroutines() {
    runBlocking {
        launch {
            println("Ejecutando otro subproceso")
        }
        launch(newSingleThreadContext("Personalizado")) {
            println("Rutina: ${Thread.currentThread().name}")
        }

        // Otra forma de hacerlo:
        newSingleThreadContext("Contexto 2").use { contexto ->
            launch(contexto) {
                println("Rutina: ${Thread.currentThread().name}")
            }
        }
    }
}*/

/*fun coroutines() {
    runBlocking {
        println("Contexto: ${Thread.currentThread().name}")
        withContext(newSingleThreadContext("Nuevo contexto")) {
            delay(200)
            println("Contexto: ${Thread.currentThread().name}")
        }
    }
}*/

fun dispatchers() {
    println("Secuencias")
    /*crearSecuencias().forEach {
        println("$it datos regresados")
    }*/
    flow()
}

fun crearSecuencias(): Sequence<Int> {
    return sequence {
        (1 .. 6).forEach { resultado ->
            println("Elementos procesados")
            Thread.sleep(2000)
            yield(resultado + Random.nextInt(10, 50))
        }
    }
}

fun flow() {
    runBlocking {
        launch {
            crearSecuenciasFlow().collect() {
                println("datos $it")
            }
        }
        launch {
            (1 .. 10).forEach {
                delay(300)
                println("procesos")
            }
        }
    }
}

fun crearSecuenciasFlow() : Flow<Int> {
    return flow {
        println("Procesando...")
        delay(2000)
        //emit()
    }
}