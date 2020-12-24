package io.odbs.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.odbs.movie.R
import io.odbs.movie.model.SearchResult
import io.odbs.movie.util.SearchAdapterInteractionListener

class SearchListAdapter(
    private val searchResult : SearchResult,
    private val mListener : SearchAdapterInteractionListener
) : RecyclerView.Adapter<SearchListAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_search, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.movieTitle.text = searchResult.search[position].title
        holder.movieYear.text = searchResult.search[position].year
    }

    override fun getItemCount(): Int {
        return searchResult.search.size
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieTitle : TextView = itemView.findViewById(R.id.movie_title)
        val movieYear : TextView = itemView.findViewById(R.id.movie_year)

    }
}