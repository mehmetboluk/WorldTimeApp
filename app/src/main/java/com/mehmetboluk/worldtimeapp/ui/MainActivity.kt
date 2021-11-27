package com.mehmetboluk.worldtimeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.mehmetboluk.worldtimeapp.adapter.CityTimeAdapter
import com.mehmetboluk.worldtimeapp.database.Model
import com.mehmetboluk.worldtimeapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

   private lateinit var binding : ActivityMainBinding
   private lateinit var adapter: CityTimeAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvCityTime.layoutManager = GridLayoutManager(this@MainActivity ,2)



            mainViewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
            val list :ArrayList<Model> = ArrayList()
            list.addAll(mainViewModel.callDataFromRoom(this@MainActivity))
            Log.d("Cities", list.size.toString())

            adapter = CityTimeAdapter(list)
            adapter.notifyDataSetChanged()
            binding.rvCityTime.adapter = adapter




       binding.ivAdd.setOnClickListener {
           val intent = Intent(this, AddCityTimeActivity::class.java)
           startActivity(intent)
       }

    }
}