package com.mehmetboluk.worldtimeapp.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mehmetboluk.worldtimeapp.R
import com.mehmetboluk.worldtimeapp.database.Model

class CityTimeAdapter(private val cityTime : ArrayList<Model>) : RecyclerView.Adapter<CityTimeAdapter.CityViewHolder>() {


    class CityViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bind(city: Model){
            itemView.findViewById<TextView>(R.id.rowCityName).text = city.cityName
            itemView.findViewById<TextView>(R.id.rowCityZone).text = city.cityTimeZone
            itemView.findViewById<ImageView>(R.id.rowImage).setColorFilter(Color.parseColor(city.backgroundColor))
            itemView.findViewById<ImageView>(R.id.rowImage).setBackgroundColor(Color.parseColor(city.dialColor))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycr_row_column,parent,false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cityTime.get(position))
    }

    override fun getItemCount(): Int {
       return cityTime.size
    }
}