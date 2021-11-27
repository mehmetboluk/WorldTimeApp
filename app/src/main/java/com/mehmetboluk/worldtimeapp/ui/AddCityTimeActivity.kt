package com.mehmetboluk.worldtimeapp.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mehmetboluk.worldtimeapp.R
import com.mehmetboluk.worldtimeapp.database.Model
import com.mehmetboluk.worldtimeapp.databinding.ActivityAddCityTimeBinding
import com.mehmetboluk.worldtimeapp.model.CityTime


class AddCityTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCityTimeBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainCityTime : CityTime

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.makeApiCall()
        mainViewModel.getCityTimeObserver().observe(this, Observer {
            mainCityTime = it
            binding.spCity.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,it.cities)
            binding.spTimeBackgroundColor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it.colors)
            binding.spTimeDialColor.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it.colors)
        })


        binding.ivCancel.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.ivOk.setOnClickListener {
            val selectedCity = mainCityTime.cities.get(binding.spCity.selectedItemPosition)
            val selectedTimeZone = mainCityTime.timezone.get(binding.spCity.selectedItemPosition)
            val selectedCBackColor = mainCityTime.colorscode.get(binding.spTimeBackgroundColor.selectedItemPosition)
            val selectedDialColor = mainCityTime.colorscode.get(binding.spTimeDialColor.selectedItemPosition)
            val model = Model(selectedCity,selectedTimeZone,selectedCBackColor,selectedDialColor)
            mainViewModel.upsertData(this,model)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
