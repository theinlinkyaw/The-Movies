package com.tlk.android.themovies.ui.upcomingandpopularmovies

import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.tlk.android.themovies.R
import com.tlk.android.themovies.databinding.ItemMovieBinding
import com.tlk.android.themovies.domain.models.MovieWithFavorite

class MoviesAdapter(
    private val movies: MutableList<MovieWithFavorite> = mutableListOf(),
    private val glide: RequestManager,
    private val itemClickListener: (View, MovieWithFavorite) -> Unit,
    private val toggleFavoriteListener: (MovieWithFavorite) -> Unit,
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovieBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            glide.load(movies[position].posterPath)
                .into(ivPoster)
            tvTitle.text = movies[position].title
            tvReleaseDate.text = movies[position].releaseDate
            btnFavorite.setBackgroundResource(
                if (movies[position].favorite)
                    R.drawable.outline_favorite_black_24
                else
                    R.drawable.outline_favorite_border_black_24
            )
            btnFavorite.setOnClickListener {
                movies[position].favorite = !movies[position].favorite
                notifyItemChanged(position)
                toggleFavoriteListener.invoke(movies[position])
            }
            root.setOnClickListener { itemClickListener.invoke(ivPoster, movies[position]) }
        }
    }

    override fun getItemCount() = movies.size

    fun update(newMovies: List<MovieWithFavorite>) {
        movies.clear()
        movies.addAll(newMovies)

        notifyDataSetChanged()
    }
}