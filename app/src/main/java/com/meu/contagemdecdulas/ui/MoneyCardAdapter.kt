package com.meu.contagemdecdulas.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meu.contagemdecdulas.data.Money
import com.meu.contagemdecdulas.databinding.ActivityMainBinding
import com.meu.contagemdecdulas.databinding.ItemMoneyBinding

class MoneyCardAdapter(private val money: List<Money>) : RecyclerView.Adapter<MoneyCardAdapter.MoneyViewHolder>(){

    inner class MoneyViewHolder (
        private val binding: ItemMoneyBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:Money){
            with(itemView) {
                binding.quantidade.text = item.quantidade.toString()
                binding.tvMoney.text = item.valor
                Glide.with(context).load(item.image).into(binding.ivMoney)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemMoneyBinding.inflate(inflater, parent, false)
        return MoneyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        holder.bind(money[position])
    }

    override fun getItemCount(): Int {
        return money.size
    }
}