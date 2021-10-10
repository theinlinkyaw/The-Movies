package com.tlk.android.themovies.ui.upcomingandpopularmovies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.bumptech.glide.Glide
import com.tlk.android.themovies.R
import com.tlk.android.themovies.databinding.UpcomingAndPopularMoviesFragmentBinding
import com.tlk.android.themovies.di.ViewModelFactory
import com.tlk.android.themovies.domain.models.MovieWithFavorite
import com.tlk.android.themovies.ui.moviedetails.MovieDetailsFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UpcomingAndPopularMoviesFragment : DaggerFragment() {

    companion object {
        const val TAG: String = "UpcomingAndPopularMoviesFragment"

        fun newInstance() = UpcomingAndPopularMoviesFragment()
    }

    private lateinit var binding: UpcomingAndPopularMoviesFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: UpcomingAndPopularMoviesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(UpcomingAndPopularMoviesViewModel::class.java)
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
        return inflater.inflate(R.layout.upcoming_and_popular_movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UpcomingAndPopularMoviesFragmentBinding.bind(view)
        initView()
        upcomingList()
        popularList()
        loadingState()
        errorState()
        if (savedInstanceState == null) {
            viewModel.getUpcomingMovies()
            viewModel.getPopularMovies()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    private fun initView() {
        binding.content.visibility = View.INVISIBLE
        binding.swipeRefresh.isEnabled = false;
        binding.swipeRefresh.isRefreshing = false;
    }

    private fun upcomingList() {
        val upcomingAdapter = MoviesAdapter(
            glide = Glide.with(this),
            itemClickListener =  { v, it ->
                navigate(v, it)
            },
            toggleFavoriteListener = {
                viewModel.toggleFavorite(it)
            }
        )

        binding.upcomingRecyclerView.adapter = upcomingAdapter

        viewModel.upcoming.observe(viewLifecycleOwner, {
            binding.content.visibility = View.VISIBLE
            upcomingAdapter.update(it)
        })
    }

    private fun popularList() {
        val popularAdapter = MoviesAdapter(
            glide = Glide.with(this),
            itemClickListener =  { v, it ->
                navigate(v, it)
            },
            toggleFavoriteListener = {
                viewModel.toggleFavorite(it)
            }
        )

        binding.popularRecyclerView.adapter = popularAdapter

        viewModel.popular.observe(viewLifecycleOwner, {
            binding.content.visibility = View.VISIBLE
            popularAdapter.update(it)
        })
    }

    private fun navigate(view: View, movie: MovieWithFavorite) {
        val extras = FragmentNavigatorExtras(
            view to "posterImage")
        Navigation.findNavController(requireView()).navigate(
            R.id.action_upcomingAndPopularMoviesFragment_to_movieDetailsFragment,
            MovieDetailsFragment.createArguments(movie), null, extras
        )
    }

    private fun loadingState() {
        viewModel.loading.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = it
        })
    }

    private fun errorState() {
        viewModel.error.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }
}