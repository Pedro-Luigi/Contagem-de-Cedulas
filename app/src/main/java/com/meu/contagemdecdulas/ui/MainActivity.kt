package com.meu.contagemdecdulas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meu.contagemdecdulas.R
import com.meu.contagemdecdulas.data.Money
import com.meu.contagemdecdulas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val listMoney = arrayListOf<Money>()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val adapter by lazy { MoneyCardAdapter(listMoney) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnContar.setOnClickListener {
            binding.rvResult.adapter = adapter
            listMoney.removeAll(listMoney.toSet())
            val valor = binding.tilValor.editText?.text.toString().toDouble()
            contar(valor+0.01)
        }
    }

    private fun contar(troco: Double) {
        val notas: DoubleArray = doubleArrayOf(200.0, 100.0, 50.0, 20.0, 10.0, 5.0, 2.0, 1.0, 0.5, 0.25,
            0.10, 0.05)
        var valor = troco

        for (i in notas.indices){

            if (valor >= notas[i]) {
                val money = Money(
                    valor = if (notas[i] > 0.99) "${notas[i].toInt()} reais" else "${(notas[i]*100).toInt()} centavos",
                    image = when (notas[i]) {
                        200.0 -> R.drawable.duzentos
                        100.0 -> R.drawable.cem
                        50.0 -> R.drawable.cinquenta
                        20.0 -> R.drawable.vinte
                        10.0 -> R.drawable.dez
                        5.0 -> R.drawable.cinco
                        2.0 -> R.drawable.dois
                        1.0 -> R.drawable.umreal
                        0.5 -> R.drawable.cinquentamoeda
                        0.25 -> R.drawable.vinteecincomoeda
                        0.10 -> R.drawable.dezmoeda
                        else -> R.drawable.cincomoeda
                    },
                    quantidade = (valor/notas[i]).toInt()
                )
                listMoney.add(money)
            }
            valor %= notas[i]
        }
    }
}

