package com.muvidb.app.ui.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.muvidb.app.BuildConfig
import com.muvidb.app.databinding.ListItemMovieBinding
import com.muvidb.app.ui.viewparam.MovieViewParam

class PlayingMovieAdapter(private val listener: (View, MovieViewParam)-> Unit): RecyclerView.Adapter<PlayingMovieAdapter.PlayingMovieViewHolder>() {

    private val items = mutableListOf<MovieViewParam>()

    fun setItems(listMovie: List<MovieViewParam>){
        this.items.clear()
        this.items.addAll(listMovie)
        notifyDataSetChanged()
    }

    inner class PlayingMovieViewHolder(private val binding : ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(movieViewParam: MovieViewParam, listener: (View, MovieViewParam) -> Unit) {
            binding.itemMovieName.text = movieViewParam.title
            binding.itemMovieImage.load(BuildConfig.IMAGE_URL + movieViewParam.poster_path)
            binding.itemMovieCard.setOnClickListener {listener(it, movieViewParam)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingMovieViewHolder {
        return PlayingMovieViewHolder(ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlayingMovieViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}