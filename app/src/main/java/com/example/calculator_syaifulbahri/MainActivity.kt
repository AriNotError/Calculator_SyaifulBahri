package com.example.calculator_syaifulbahri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var tambahdata: Button
    private lateinit var soal: TextView
    private lateinit var jawaban: TextView
    private lateinit var recyclerView : RecyclerView
    private lateinit var recyclerAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tambahdata = findViewById(R.id.button_simpan)
        soal = findViewById(R.id.input)
        jawaban= findViewById(R.id.output)
        recyclerView = findViewById(R.id.listdata)

        val data = mutableListOf<Data>()
        viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerAdapter =  Adapter(data)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = viewManager

        tambahdata.setOnClickListener{
            val soal = soal.text.toString()
            val jawaban = jawaban.text.toString()

            val dataSiswa = Data(soal,jawaban)
            data.add(dataSiswa)
            recyclerAdapter.notifyDataSetChanged()
        }

        button_hapus.setOnClickListener{
            input.text = ""
            output.text = "0"
        }

        button_0.setOnClickListener {
            input.text = addToInputText("0")
        }

        button_1.setOnClickListener {
            input.text = addToInputText("1")
        }

        button_2.setOnClickListener {
            input.text = addToInputText("2")
        }

        button_3.setOnClickListener {
            input.text = addToInputText("3")
        }

        button_4.setOnClickListener {
            input.text = addToInputText("4")
        }

        button_5.setOnClickListener {
            input.text = addToInputText("5")
        }

        button_6.setOnClickListener {
            input.text = addToInputText("6")
        }

        button_7.setOnClickListener {
            input.text = addToInputText("7")
        }

        button_8.setOnClickListener {
            input.text = addToInputText("8")
        }

        button_9.setOnClickListener {
            input.text = addToInputText("9")
        }

        button_tambah.setOnClickListener {
            input.text = addToInputText("+")
        }

        button_kurang.setOnClickListener {
            input.text = addToInputText("-")
        }

        button_kali.setOnClickListener {
            input.text = addToInputText("×")
        }

        button_bagi.setOnClickListener {
            input.text = addToInputText("÷")
        }

        button_hasil.setOnClickListener {
            showResult()
        }
    }

    private fun addToInputText(buttonValue : String): String{
        return "${input.text}$buttonValue"
    }

    private fun getInputExpression(): String{
        var expression = input.text.replace(Regex("÷"),"/")
        expression = expression.replace(Regex("×"),"*")
        return expression
    }

    private fun showResult(){
        try{
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()){
                //show error message
                output.text = "Error"
                output.setTextColor(ContextCompat.getColor(this,R.color.red))
            } else {
                //show result
                output.text = DecimalFormat("= 0.######").format(result).toString()
                output.setTextColor(ContextCompat.getColor(this,R.color.green))
            }
        } catch (e: Exception){
            output.text = "Error"
            output.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }
}