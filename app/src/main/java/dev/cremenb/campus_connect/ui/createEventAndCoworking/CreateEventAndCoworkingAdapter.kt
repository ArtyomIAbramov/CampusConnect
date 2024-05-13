package dev.cremenb.campus_connect.ui.createEventAndCoworking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.cremenb.api.models.PlaceAndSlot
import dev.cremenb.campus_connect.R

class CreateEventAndCoworkingAdapter(private val context: Context, private var dataList: List<PlaceAndSlot>) : RecyclerView.Adapter<CreateEventAndCoworkingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.textViewName.text = data.place.name
        holder.textViewAddress.text = data.place.address
        holder.textViewCapacity.text = data.place.capacity.toString()

        holder.buttonTakePart.setOnClickListener {
            // Создаем BottomSheetDialog
            val dialog = BottomSheetDialog(holder.itemView.context)
            // Инфлейтим разметку для BottomSheetDialog
            //val bottomSheetView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.item_place, null)
            // Здесь вы можете инициализировать элементы вашей разметки и загрузить данные
            // Например:
            // val textViewData = bottomSheetView.findViewById<TextView>(R.id.textViewData)
            // textViewData.text = "Загруженные данные"

            // Устанавливаем разметку для BottomSheetDialog
            //dialog.setContentView(bottomSheetView)
            // Показываем BottomSheetDialog
            dialog.show()
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
