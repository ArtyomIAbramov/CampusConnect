package dev.cremenb.campus_connect.ui.eventComment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.cremenb.api.models.Comment
import dev.cremenb.campus_connect.R
import dev.cremenb.utilities.DateFormatter

class CommentAdapter(
    private val dataList: List<Comment>
) :  RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]

        holder.loginTextView.text = data.login
        holder.dateTextView.text = DateFormatter.formatDDMMHHmm(data.createdAt!!)
        holder.messageTextView.text = data.text
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val loginTextView: TextView = itemView.findViewById(R.id.login)
        val dateTextView: TextView = itemView.findViewById(R.id.date)
        val messageTextView: TextView = itemView.findViewById(R.id.message)
    }
}