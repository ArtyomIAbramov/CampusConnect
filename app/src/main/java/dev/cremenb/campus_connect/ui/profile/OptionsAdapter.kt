package dev.cremenb.campus_connect.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.cremenb.api.models.Option
import dev.cremenb.campus_connect.R

class OptionsAdapter (
    private var dataList: List<Option>,
    private val navController: NavController,
) : RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_option, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.imageView.setImageResource(data.imageResId)
        holder.titleTextView.text = data.name
        holder.constraintView.setOnClickListener {
            when (position) {
                0 -> navController.navigate(R.id.action_navigation_profile_to_navigation_events)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.profile_option_image)
        val titleTextView: TextView = itemView.findViewById(R.id.profile_option_text)
        val constraintView: View = itemView.findViewById(R.id.profile_option_constraint)
    }
}
