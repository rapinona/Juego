package dgtic.unam.juego

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dgtic.unam.juego.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {

        super.onStart()

        CoroutineScope(Dispatchers.Default).launch {
            var count = 0

            while (true) {
                delay(1000)
                withContext(Dispatchers.Main) {
                    binding.textContador.text = count.toString()
                }
                count += 1
            }
        }

        GlobalScope.launch {
            var count = 0

            while (true) {
                delay(1000)
                withContext(Dispatchers.Main) {
                    binding.textContador.text = count.toString()
                }
                count += 1
            }
        }



    }
}