package com.tlk.android.themovies.ui.moviedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tlk.android.themovies.R
import com.tlk.android.themovies.databinding.MovieDetailsFragmentBinding
import com.tlk.android.themovies.domain.models.MovieWithFavorite
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailsFragment : DaggerFragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()

        private const val MOVIE_ARG = "movie"

        fun createArguments(movie: MovieWithFavorite) = bundleOf(
            MOVIE_ARG to movie
        )
    }

    private lateinit var binding: MovieDetailsFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MovieDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MovieDetailsFragmentBinding.bind(view)
        setupView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    private fun setupView() {
        val glide = Glide.with(this)
        val movie = requireArguments().get(MOVIE_ARG) as MovieWithFavorite
        with(binding) {
            glide.load(movie.posterPath)
                .into(ivPoster)
            tvTitle.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvOverview.text = movie.overview
            updateFavorite(movie)
            btnFavorite.setOnClickListener {
                movie.favorite = !movie.favorite
                updateFavorite(movie)
                viewModel.toggleFavorite(movie)
            }
        }
    }

    private fun updateFavorite(movie: MovieWithFavorite) {
        binding.btnFavorite.setBackgroundResource(
            if (movie.favorite)
                R.drawable.outline_favorite_black_24
            else
                R.drawable.outline_favorite_border_black_24
        )
    }

}