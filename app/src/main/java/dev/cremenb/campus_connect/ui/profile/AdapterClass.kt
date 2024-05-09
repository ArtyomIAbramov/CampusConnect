import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import dev.cremenb.campus_connect.R
import dev.cremenb.campus_connect.ui.profile.MyData

class EventAdapter(private val context: Context, private val dataList: List<MyData>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.titleTextView.text = data.title
        holder.imageView.setImageResource(data.imageResource)

        holder.cardView.setOnClickListener {
            TODO("подумать, как будем переходить на детальную карточку события")
        }

        holder.itemOneView.setOnClickListener {
            TODO("подумать, как будем переходить на детальную карточку события")
        }

        holder.itemTwoView.setOnClickListener {
            TODO("подумать, как будем переходить на детальную карточку события")
        }

        holder.itemThreeView.setOnClickListener {
            TODO("подумать, как будем переходить на детальную карточку события")
        }

        holder.itemFourView.setOnClickListener {
            TODO("подумать, как будем переходить на детальную карточку события")
        }

        holder.itemFiveView.setOnClickListener {
            TODO("подумать, как будем переходить на детальную карточку события")
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val cardView: CardView = itemView.findViewById(R.id.card_id)
        val itemOneView: CardView = itemView.findViewById(R.id.card_id)
        val itemTwoView: CardView = itemView.findViewById(R.id.card_id)
        val itemThreeView: CardView = itemView.findViewById(R.id.card_id)
        val itemFourView: CardView = itemView.findViewById(R.id.card_id)
        val itemFiveView: CardView = itemView.findViewById(R.id.card_id)
    }

}
