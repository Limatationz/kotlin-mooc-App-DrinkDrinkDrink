package com.example.android.drinkdrinkdrink.fragments.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.drinkdrinkdrink.databinding.ListItemDrinkBinding
import com.example.android.drinkdrinkdrink.domain.Drink
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter() : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    /**
     * Liste der Daten, die die RecyclerView darstellen soll
     */
    var data = listOf<Drink>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Gibt die Anzahl der Einträge von [data] zurück
     */
    override fun getItemCount() = data.size

    /**
     * Verbindet den Eintrag an [position] von [data] mit einem [ViewHolder]
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    /**
     * Erstellt einen [ViewHolder]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemDrinkBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    /**
     * Ummantelt einen Eintrag der RecyclerView
     */
    class ViewHolder(private val binding: ListItemDrinkBinding): RecyclerView.ViewHolder(binding.root) {
        /**
         * Verbindet den übergebenen Eintrag mit dem Layout
         */
        fun bind(item: Drink) {
            val local = Locale("de")
            val fmtDatum = SimpleDateFormat("dd.MM.yyyy", local)
            val fmtZeit = SimpleDateFormat("HH:mm", local)

            binding.dataTextView.text = "${item.volume}ml am ${fmtDatum.format(item.time)} um ${fmtZeit.format(item.time)} Uhr"
        }
    }
}