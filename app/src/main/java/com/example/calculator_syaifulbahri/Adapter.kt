package com.example.calculator_syaifulbahri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val dataset: MutableList<Data>):
        RecyclerView.Adapter<Adapter.DataHolder>(){
            class DataHolder(view: View) : RecyclerView.ViewHolder(view){
                val textSoal = view.findViewById<TextView>(R.id.txtInput)
                val textJawaban = view.findViewById<TextView>(R.id.txtOutput)
                val btnHapus = view.findViewById<ImageView>(R.id.btn_hapus)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_adapter, parent, false
        )
        return DataHolder(view)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = dataset[position]
        holder.textSoal.text = dataset[position].text1
        holder.textJawaban.text = dataset[position].text2

        //tombol hapus
        holder.btnHapus.setOnClickListener{
            dataset.removeAt(position)
           notifyItemRangeRemoved(position, dataset.size)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return  dataset.size
    }
}