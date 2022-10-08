package com.example.flixsterplus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixsterplus.R.id

/**
 * [RecyclerView.Adapter] that can display a [Television] and makes a call to the
 * specified [TelevisionOnListFragmentInteractionListener].
 */
class TelevisionRecyclerViewAdapter(
    private val series: List<Television>,
    private val tListener: TelevisionFragment
)
    : RecyclerView.Adapter<TelevisionRecyclerViewAdapter.TelevisionViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TelevisionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_television, parent, false)
        return TelevisionViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class TelevisionViewHolder(val tView: View) : RecyclerView.ViewHolder(tView) {
        var tItem: Television? = null
        val tSeriesTitle: TextView = tView.findViewById<View>(id.television_title) as TextView
        //val tSeriesPopularity: TextView = tView.findViewById<View>(id.series_popularity) as TextView
        val tSeriesPoster: ImageView = tView.findViewById(id.television_poster) as ImageView

        override fun toString(): String {
            return tSeriesTitle.toString() //+ " '" + tSeriesPopularity.text + "'"
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: TelevisionViewHolder, position: Int) {
        val television = series[position]

        holder.tItem = television
        holder.tSeriesTitle.text = television.name
        //holder.tSeriesPopularity.text = television.popularity.toString()
        Glide.with(holder.tView)
            .load("https://image.tmdb.org/t/p/w500"+television.posterUrl)
            .centerInside()
            .into(holder.tSeriesPoster)

        holder.tView.setOnClickListener {
            holder.tItem?.let { television ->
                tListener?.onItemClick(television)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return series.size
    }
}