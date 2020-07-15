package com.example.exercise5.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.exercise5.Communicator
import com.example.exercise5.R
import kotlinx.android.synthetic.main.fragment_gallery.*

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val sharedViewModel by lazy {
        ViewModelProvider(this).get(Communicator::class.java)
    }

    private val pagerAdapter by lazy {
        GalleryPagerAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        vpagerGallery.adapter = pagerAdapter
    }

    private fun setObserver() {
        sharedViewModel.listMovies.observe(viewLifecycleOwner, Observer {
            pagerAdapter.movies = it
            val arguments = GalleryFragmentArgs.fromBundle(requireArguments())
            vpagerGallery.setCurrentItem(arguments.position, false)
        })
    }
}