package com.muharlyam.g2048.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muharlyam.g2048.R
import com.muharlyam.g2048.databinding.ItemBinding
import com.muharlyam.g2048.service.NumbersService


class NumberAdapter : RecyclerView.Adapter<NumberAdapter.NumberHolder>() {

    private val numbers = NumbersService.addNewNumber()
    private val numbersService = NumbersService

    class NumberHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = ItemBinding.bind(item)

        var res: Resources = Resources.getSystem()

        @SuppressLint("ResourceType")
        fun bind(number: String) {
            binding.value.text = number

            when (binding.value.text) {

                "" -> binding.value.setBackgroundResource(R.drawable.circle)
                "2" -> binding.value.setBackgroundResource(R.drawable.circle2)
                "4" -> binding.value.setBackgroundResource(R.drawable.circle4)
                "8" -> binding.value.setBackgroundResource(R.drawable.circle8)
                "16" -> binding.value.setBackgroundResource(R.drawable.circle16)
                "32" -> binding.value.setBackgroundResource(R.drawable.circle32)
                "64" -> binding.value.setBackgroundResource(R.drawable.circle64)
                "128" -> binding.value.setBackgroundResource(R.drawable.circle128)
                "256" -> binding.value.setBackgroundResource(R.drawable.circle256)
                "512" -> binding.value.setBackgroundResource(R.drawable.circle512)
                "1024" -> binding.value.setBackgroundResource(R.drawable.circle1024)
                "2048" -> binding.value.setBackgroundResource(R.drawable.circle2048)
                "4096" -> binding.value.setBackgroundResource(R.drawable.circle4096)
                "8192" -> binding.value.setBackgroundResource(R.drawable.circle8192)
                "16384" -> binding.value.setBackgroundResource(R.drawable.circle16384)
                "32768" -> binding.value.setBackgroundResource(R.drawable.circle32768)
                "65536" -> binding.value.setBackgroundResource(R.drawable.circle65536)
                "131072" -> binding.value.setBackgroundResource(R.drawable.circle131072)

            }
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

    @SuppressLint("NotifyDataSetChanged")
    fun onCancel() {
        numbers.clear();
        numbers.addAll(numbersService.cancel())
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onReset() {
        numbers.clear();
        numbers.addAll(numbersService.reset())
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