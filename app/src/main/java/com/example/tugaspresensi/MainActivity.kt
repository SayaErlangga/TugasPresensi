package com.example.tugaspresensi

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.tugaspresensi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val alasan = arrayOf(
            "Hadir Tepat Waktu",
            "Sakit",
            "Terlambat",
            "Izin"
        )
        val bulan = arrayOf(
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
        )
        var selectedTime = ""
        var selectedDate = ""
        with(binding){
            timePicker.setOnTimeChangedListener{ _, hourOfDay, minute ->
                selectedTime = "$hourOfDay:$minute"
            }
            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) {_, year, month, dayOfMonth ->
                val selectedMonth = bulan.get(month)
                selectedDate = "$dayOfMonth ${selectedMonth} $year"
            }
            val adapterAlasan = ArrayAdapter<String>(this@MainActivity, R.layout.simple_spinner_item, alasan)
            spinnerAlasan.adapter = adapterAlasan

            spinnerAlasan.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View, position: Int, id: Long
                    ) {
                        val selectedItem = parent.getItemAtPosition(position).toString()

                        if(selectedItem == "Hadir Tepat Waktu"){
                            deskripsi.visibility = View.INVISIBLE
                        } else {
                            deskripsi.visibility = View.VISIBLE
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }
                }

            btnSubmit.setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "Presensi Berhasil $selectedDate Jam $selectedTime", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}