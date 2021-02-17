package com.adintech.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager


class HomeFragment : Fragment() {

    lateinit var mBinding: HomeFragment
    private lateinit var categoryAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

        var data: MutableList<DataObject> = ArrayList()
        var linearLayoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        for (i: Int in 1..10) {
            data.add(DataObject("title $i"))
        }

        categoryAdapter = CategoriesAdapter(data)

/*
        val imageList = ArrayList<SlideModel>() // Create image list

        mBinding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE) // for all images

        imageList.add(SlideModel("https://bit.ly/2YoJ77H", ""))
        imageList.add(SlideModel("https://bit.ly/2BteuF2", ""))
        imageList.add(SlideModel("https://bit.ly/3fLJf72", ""))

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        imageSlider.startSliding(3000) // with new period
        imageSlider.startSliding()
        imageSlider.stopSliding()*/
    }

}