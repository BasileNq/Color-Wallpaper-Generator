package com.example.colorwallpapergenerator.modeList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.colorwallpapergenerator.R
import com.example.colorwallpapergenerator.datas.Mode
import com.example.colorwallpapergenerator.modeDetail.DetailModeActivity


class MainAdapter(private val dataSet: List<Mode>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = ItemView.findViewById(R.id.textView)
        val imageView: ImageView = ItemView.findViewById(R.id.imageview)

        init {
            // Define click listener for the ViewHolder's View.
                itemView.setOnClickListener {
                    val idImage: Int = dataSet[this.absoluteAdapterPosition].image
                    val context = textView.context
                    val intent = Intent(context, DetailModeActivity::class.java).apply {
                        putExtra("text", textView.text)
                        putExtra("image", idImage)
                    }
                    context.startActivity(intent)
                }

        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_view_design, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.textView.text = dataSet[position]
        val mode = dataSet[position]
        // sets the image to the imageview from our itemHolder class
        viewHolder.imageView.setImageResource(mode.image)
        // sets the text to the textview from our itemHolder class
        viewHolder.textView.text = mode.name



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = dataSet.size

}
