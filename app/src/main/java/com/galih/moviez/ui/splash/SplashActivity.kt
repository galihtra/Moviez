package com.galih.moviez.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.galih.moviez.databinding.ActivitySplashBinding
import com.galih.moviez.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper())
            .postDelayed({
                Intent(this, HomeActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }, TIME)
    }

    companion object{
        private const val TIME = 900L
    }
}