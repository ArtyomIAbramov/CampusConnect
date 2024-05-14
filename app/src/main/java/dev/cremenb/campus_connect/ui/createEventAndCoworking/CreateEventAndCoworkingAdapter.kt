package dev.cremenb.campus_connect.ui.createEventAndCoworking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.cremenb.api.models.BookingSlot
import dev.cremenb.api.models.Place
import dev.cremenb.api.models.PlaceAndSlot
import dev.cremenb.campus_connect.R
import dev.cremenb.utilities.EdgeItemDecoration


class CreateEventAndCoworkingAdapter(
    private val context: Context,
    private var dataList: List<PlaceAndSlot>?,
    private var eventList: List<Place>?,
    private val listener: SlotSelectionListener,
    private val one_is_coworking_two_is_event : Int,
) : RecyclerView.Adapter<CreateEventAndCoworkingAdapter.ViewHolder>()  {

    var selectedTime : BookingSlot? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(dataList != null) {
            val data = dataList!![position]

            holder.textViewName.text = data.place?.name
            holder.textViewAddress.text = data.place.adress
            holder.textViewCapacity.text = data.place.capacity.toString()

            if (one_is_coworking_two_is_event == 1) {
                holder.buttonTakePart.setOnClickListener {

                    val dialog = BottomSheetDialog(holder.itemView.context)
                    val bottomSheetView = LayoutInflater.from(holder.itemView.context)
                        .inflate(R.layout.item_slots, null)
                    val slotsRecyclerView =
                        bottomSheetView.findViewById<RecyclerView>(R.id.slotsRecyclerView)

                    slotsRecyclerView.addItemDecoration(EdgeItemDecoration(10))
                    slotsRecyclerView.layoutManager = GridLayoutManager(context, 2)
                    val slotsAdapter = SlotsAdapter(context, dialog, data.bookingSlot)
                    slotsRecyclerView.adapter = slotsAdapter

                    val closeButton = bottomSheetView.findViewById<Button>(R.id.button_select)
                    closeButton.setOnClickListener {
                        selectedTime = data.bookingSlot[slotsAdapter.selectedTimePosition!!]
                        dialog.dismiss()
                        listener.slotSelected(data.place.id!!, selectedTime!!)
                    }
                    dialog.setContentView(bottomSheetView)
                    dialog.show()
                }
            }
        }
        else
        {
            val data = eventList!![position]
            holder.textViewName.text = data.name
            holder.textViewAddress.text = data.adress
            holder.textViewCapacity.text = data.capacity.toString()

            holder.buttonTakePart.text = "Создать"
            holder.buttonTakePart.setOnClickListener {
                listener.eventPlaceSelected(data.id!!)
            }
        }
    }

    override fun getItemCount(): Int {
        if(dataList != null)
        {
            return dataList!!.size
        }
        else
        {
            return eventList!!.size
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewAddress: TextView = itemView.findViewById(R.id.textViewAddress)
        val textViewCapacity: TextView = itemView.findViewById(R.id.textViewCapacity)
        val buttonTakePart: Button = itemView.findViewById(R.id.buttonTakePart)
    }
}
