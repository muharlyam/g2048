package com.muharlyam.g2048

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muharlyam.g2048.adapter.NumberAdapter
import com.muharlyam.g2048.databinding.ActivityMainBinding
import com.muharlyam.g2048.listener.OnSwipeTouchListener
import com.muharlyam.g2048.service.NumbersService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val numberAdapter: NumberAdapter = NumberAdapter()
    private val numberService: NumbersService = NumbersService

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = findViewById(R.id.rcView)
        recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 4)
        recyclerView.adapter = numberAdapter

        val score: TextView = findViewById(R.id.score)

        recyclerView.setOnTouchListener(object: OnSwipeTouchListener() {
            override fun onSwipeLeft() {
                numberAdapter.onSwipeLeft()
                score.text = numberService.getScore().toString()
            }

            override fun onSwipeRight() {
                numberAdapter.onSwipeRight()
                score.text = numberService.getScore().toString()
            }

            override fun onSwipeTop() {
                numberAdapter.onSwipeTop()
                score.text = numberService.getScore().toString()
            }

            override fun onSwipeBottom() {
                numberAdapter.onSwipeBottom()
                score.text = numberService.getScore().toString()
            }
        })
    }
}