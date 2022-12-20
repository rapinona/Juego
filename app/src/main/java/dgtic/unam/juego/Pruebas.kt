package dgtic.unam.juego

import kotlin.concurrent.thread
import kotlin.random.Random

fun main2() {
    // Version 1:
    funcionLamba(12, 45, {
         println(it)
     })

     funcionLamba(3, 4, { resultado ->
         println(resultado)
     })

     funcionLamba(3, 4) { variable ->
         println(variable)
     }

    hiloLamba(10, 20) { resultado ->
        println(resultado)
    }

    println("lineas del hilo main...")
}

fun funcionLamba(a: Int, b: Int, callback: (result: String) -> Unit) {
    callback((a + b).toString())
}

fun hilo (a: Int, b: Int): Int {
    var result = 0

    thread {
        //Thread.sleep(Random.nextLong(1000, 3000))
        result = a + b
    }

    return result
}

fun hiloLamba(a: Int, b: Int, callback: (result: Int) -> Unit) {
    var result = 0
    thread {
        Thread.sleep(Random.nextLong(1000, 3000))
        result = a + b
        callback(result)
    }

    println("Ejecuta mas lineas")
}


// Practica 13

// Funciones de tipo
/*
fun main() {
    //version uno
    funcionlambda(3,4,{regresa ->
        println(regresa)
    })
    //version dos
    funcionlambda(3,4){regresa ->
        println(regresa)
    }
}
fun funcionlambda(a:Int,b:Int,callback:(result:Int)->Unit) {
    callback(a+b)
}

// Hilos
fun main() {
    println(hilo(4,5))
}

fun hilo(a:Int,b:Int):Int{
    var result=0
    thread {
        Thread.sleep(Random.nextLong(1000,3000))
        result=a+b
    }
    //Thread.sleep(4000)
    return result
}

// Lambda + Hilos=Multitares
fun main() {
    hilolambda(4,7){
        println(it)
    }
}

fun hilolambda(a:Int,b:Int,callback:(result:Int)->Unit){
    var result=0
    thread {
        Thread.sleep(Random.nextLong(1000,3000))
        result=a+b
        callback(result)
    }
    println("Ejecuta más lineas")
}


// Corroutines

fun main() {
    coroutines()
}

fun coroutines() {
    runBlocking {
        (1..1000000).forEach {
            launch {
                delay(1000)
                print("0")
            }
        }
    }
}

// GlobalScope
fun main() {
    coroutines()
}
fun coroutines() {
    GlobalScope.launch {
        while(true){
            println("Código de la coroutina ${Thread.currentThread().name} ejecutando")
        }
    }
    Thread.sleep(2000)
}

// Constructor Coroutine RunBlocking
fun main() {
    bloque()
}
fun bloque() {
    runBlocking {
        println("inicia ${Thread.currentThread().name}")
        delay(2000)
        println("Ejecución de código")
        println("termina ${Thread.currentThread().name}")
    }
}

// Constructor Coroutine Launch

fun main() {
    launch()
}
fun launch() {
    runBlocking {
        launch {
            println("inicia ${Thread.currentThread().name}")
            delay(1000)
            println("Ejecución de código 1")
            println("termina ${Thread.currentThread().name}")
        }
        launch {
            println("inicia ${Thread.currentThread().name}")
            delay(3000)
            println("Ejecución de código 2")
            println("termina ${Thread.currentThread().name}")
        }
        println("inicia ${Thread.currentThread().name}")
        delay(2000)
        println("Ejecución de código 3")
        println("termina ${Thread.currentThread().name}")
    }
}

// Constructor Coroutine Async
fun main() {
    async()
}
fun async() {
    runBlocking {
        val result=async {
            println("inicia 1 ${Thread.currentThread().name}")
            delay(3000)
            println("Ejecución de código 1")
            println("termina 1 ${Thread.currentThread().name}")
            "regreso una cadena"
        }
        println("Esperando el resultado")
        println("Resultado:= ${result.await()}")
        println("inicia 2 ${Thread.currentThread().name}")
        delay(2000)
        println("Ejecución de código 2")
        println("termina 2 ${Thread.currentThread().name}")
    }
}

// Job
fun main() {
    job()
}
fun job() {
    runBlocking {
        val job=launch {
            println("inicia 1 ${Thread.currentThread().name}")
            delay(6000)
            println("Ejecución de código 1")
            println("termina 1 ${Thread.currentThread().name}")
        }
        launch {
            while(true){
                delay(1000)
                println("Esta activo: ${job.isActive}")
                println("Es cancelado: ${job.isCancelled}")
                println("Es completado: ${job.isCompleted}")
                //código para cancelar
                if((1..5).shuffled().first()==3){
                    println("Cancelar el job")
                    job.cancel()
                }
            }
        }
    }
}

// Deffered

fun main() {
    deffered()
}
fun deffered() {
    runBlocking {
        val deferred=async {
            println("inicia 1 ${Thread.currentThread().name}")
            delay(6000)
            println("Ejecución de código 1")
            println("termina 1 ${Thread.currentThread().name}")
            "regreso una cadena"
        }
        launch {
            while(true){
                delay(1000)
                println("Esta activo: ${deferred.isActive}")
                println("Es cancelado: ${deferred.isCancelled}")
                println("Es completado: ${deferred.isCompleted}")
                //código para cancelar
                if((1..5).shuffled().first()==3){
                    println("Cancelar el deferred")
                    deferred.cancel()
                }
                if((1..5).shuffled().first()==1){
                    println("Esperan el valor: ${deferred.await()}")
                }
            }
        }
    }
}

// Dispatchers IO

fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        launch {
            println("Otro subproceso")
        }
        launch(Dispatchers.IO) {
            println("IO")
        }
    }
}


// Dispatchers Default

fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        launch {
            println("Otro subproceso")
        }
        launch(Dispatchers.Default) {
            println("default")
        }
    }
}

// Dispatchers Main

fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        launch {
            println("Otro subproceso")
        }
        launch(Dispatchers.Main) {
            println("main")
        }
    }
}

// Dispatchers Personalizados

fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        launch {
            println("Otro subproceso")
        }
        launch(newSingleThreadContext("Personalizada")) {
            println("mi corrutina ${Thread.currentThread().name}")
        }
        newSingleThreadContext("segunda personalizada").use { contexto->
            launch(contexto) {
                println("corrutina ${Thread.currentThread().name}")
            }
        }
    }
}


// WithContext

fun main() {
    dispatchers()
}

fun dispatchers() {
    runBlocking {
        println("nombre corrutina ${Thread.currentThread().name}")
        withContext(newSingleThreadContext("personalizado con with context")) {
            println("WithContext")
            delay(2000)
            println("nombre corrutina: ${Thread.currentThread().name}")
            println("termina corrutina: ${Thread.currentThread().name}")
        }
        println("termina corrutina: ${Thread.currentThread().name}")
    }
}


// WithContext
fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        println("nombre corrutina ${Thread.currentThread().name}")
        withContext(newSingleThreadContext("personalizado con with context")) {
            println("WithContext")
            delay(2000)
            println("nombre corrutina: ${Thread.currentThread().name}")
            println("termina corrutina: ${Thread.currentThread().name}")
        }
        withContext(Dispatchers.Default) {
            println("WithContext")
            delay(1000)
            println("Uso del CPU: ${Thread.currentThread().name}")
            println("termina corrutina: ${Thread.currentThread().name}")
        }
        println("termina corrutina: ${Thread.currentThread().name}")
    }
}

// Secuencias
fun main() {
    dispatchers()
}

fun dispatchers() {
    println("Secuencias")
    crearSecuencias().forEach {
        println("$it datos regresados")
    }
}

fun crearSecuencias():Sequence<Int>{
    return sequence {
        (1..6).forEach {
            println("Emulando el procesamiento de datos")
            Thread.sleep(2000)
            yield(it+Random.nextInt(20,60))
        }
    }
}

// Flow
fun main() {
    flow()
}
fun flow() {
    println("Flow")
    runBlocking {
        launch {
            crearSecuenciasFlow().collect {
                println("datos $it")
            }
        }
        launch {
            (1..10).forEach {
                delay(300)
                println("proceso dos")
            }
        }
    }
}
fun crearSecuenciasFlow(): Flow<Int> {
    return flow {
        (1..6).forEach {
            println("Emulando el procesamiento de datos")
            delay(2000)
            emit(it + Random.nextInt(20, 60))
        }
    }
}*/