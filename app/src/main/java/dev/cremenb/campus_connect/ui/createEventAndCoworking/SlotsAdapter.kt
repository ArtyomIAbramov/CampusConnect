package dev.cremenb.campus_connect.ui.createEventAndCoworking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.cremenb.api.models.BookingSlot
import dev.cremenb.campus_connect.R
import java.text.SimpleDateFormat
import java.util.Locale

class SlotsAdapter (
    private val context: Context,
    private var dataList: List<BookingSlot>,
) : RecyclerView.Adapter<SlotsAdapter.ViewHolder>() {

    var selectedTimePosition : Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_slot_cell, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        holder.slotTextView.text =  dateFormat.format(data.from) + " - " + dateFormat.format(data.to)

        holder.cardView.setOnClickListener {
            holder.cardView.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.light_green));
            selectedTimePosition = position
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val slotTextView: TextView = itemView.findViewById(R.id.slot_text)
        val cardView: CardView = itemView.findViewById(R.id.slot_layout)
    }
}
