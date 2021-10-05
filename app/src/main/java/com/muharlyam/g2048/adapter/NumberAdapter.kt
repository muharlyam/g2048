package com.muharlyam.g2048.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muharlyam.g2048.R
import com.muharlyam.g2048.service.NumbersService
import com.muharlyam.g2048.databinding.ItemBinding

class NumberAdapter: RecyclerView.Adapter<NumberAdapter.NumberHolder>() {

    private val numbers = NumbersService.addNewNumbers()
    private val numbersService = NumbersService

    class NumberHolder(item: View): RecyclerView.ViewHolder(item) {

        private val binding = ItemBinding.bind(item)

        fun bind(number: String) {
            binding.value.text = number
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSwipeTop() {
        numbers.clear();
        numbers.addAll(numbersService.onSwipeTop())
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSwipeBottom() {
        numbers.clear();
        numbers.addAll(numbersService.onSwipeBottom())
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSwipeLeft() {
        numbers.clear();
        numbers.addAll(numbersService.onSwipeLeft())
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSwipeRight() {
        numbers.clear();
        numbers.addAll(numbersService.onSwipeRight())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return NumberHolder(view)
    }

    override fun onBindViewHolder(holder: NumberHolder, position: Int) {
        holder.bind(numbers.elementAt(position))
    }

    override fun getItemCount(): Int {
        return numbers.size
    }

}