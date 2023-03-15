package com.muvidb.app.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.muvidb.app.BuildConfig
import com.muvidb.app.databinding.ListItemMovieBinding
import com.muvidb.app.ui.viewparam.MovieViewParam

class PopularMovieAdapter : RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder>() {

    private val items = mutableListOf<MovieViewParam>()

    fun setItems(listMovie: List<MovieViewParam>){
        this.items.clear()
        this.items.addAll(listMovie)
        notifyDataSetChanged()
    }

    inner class PopularMovieViewHolder(private val binding : ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(movieViewParam: MovieViewParam) {
            binding.itemMovieName.text = movieViewParam.title
            binding.itemMovieImage.load(BuildConfig.IMAGE_URL + movieViewParam.poster_path)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        return PopularMovieViewHolder(ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}