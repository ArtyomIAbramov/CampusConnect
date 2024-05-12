package dev.cremenb.campus_connect.ui.createEventAndCoworking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.cremenb.api.models.Place
import dev.cremenb.campus_connect.R

class CreateEventAndCoworkingAdapter(private val context: Context, private var dataList: List<Place>) : RecyclerView.Adapter<CreateEventAndCoworkingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.textViewName.text = data.name
        holder.textViewAddress.text = data.address
        holder.textViewCapacity.text = data.capacity.toString()

        holder.buttonTakePart.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewAddress: TextView = itemView.findViewById(R.id.textViewAddress)
        val textViewCapacity: TextView = itemView.findViewById(R.id.textViewCapacity)
        val buttonTakePart: Button = itemView.findViewById(R.id.buttonTakePart)
    }
}
