package com.hencesimplified.mvvmretrofitsample.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hencesimplified.mvvmretrofitsample.R
import com.hencesimplified.mvvmretrofitsample.databinding.FragmentDetailBinding
import com.hencesimplified.mvvmretrofitsample.model.Animal
import com.hencesimplified.mvvmretrofitsample.model.AnimalPalette

class DetailFragment : Fragment() {

    var animal: Animal? = null
    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        //return inflater.inflate(R.layout.fragment_detail, container, false) Implemented Data binding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }

/*
        context?.let {
            dataBinding.animalDetailImage.loadImage(animal?.imageUrl, getProgressDrawable(it))
        }
*/

        /*
        Data Binding has been implemented
        animalDetailName.text = animal?.name
        animalDetailLocation.text = animal?.location
        animalDetailLifespan.text = animal?.lifeSpan
        animalDetailDiet.text = animal?.diet
         */

        animal?.imageUrl?.let {
            setupBackgroundColor(it)
        }

        dataBinding.animal = animal


    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate() { palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                            //dataBinding.animalDetailLayout.setBackgroundColor(intColor) Databinding
                            dataBinding.palette = AnimalPalette(intColor)
                        }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
    }

}