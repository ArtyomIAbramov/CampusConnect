package dev.cremenb.campus_connect.ui.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.cremenb.api.models.Event
import dev.cremenb.campus_connect.R
import dev.cremenb.utilities.DateFormatter

class EventCatalogAdapter(private val context: Context, private val dataList: List<Event>) : RecyclerView.Adapter<EventCatalogAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_catalog_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.titleTextView.text = data.name
        holder.timeTextView.text = DateFormatter.formatDDMMHHmm(data.date!!)
        holder.placeTextView.text = data.place?.name
        holder.titleTextView.text = data.name
        //holder.imageView.setImageResource(data.thumbnail)

        holder.takePartButton.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val timeTextView: TextView = itemView.findViewById(R.id.textViewTime)
        val placeTextView: TextView = itemView.findViewById(R.id.textViewPlace)
        val takePartButton: Button = itemView.findViewById(R.id.buttonTakePart)

    }
}
