package com.adintech.shop.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adintech.shop.adapter.CategoriesAdapter
import com.adintech.shop.DataObject
import com.adintech.shop.R
import com.adintech.shop.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var mBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            retainInstance = true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        initialise()
        return mBinding.root
    }

    @SuppressLint("WrongConstant")
    fun initialise() {
        mBinding.recycleview.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)


        //crating an arraylist to store users using the data class user
        val users = ArrayList<DataObject>()

        //adding some dummy data to the list
        users.add(
            DataObject(
                "Belal Khan",
                "https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png"
            )
        )
        users.add(
            DataObject(
                "Ramiz Khan",
                "https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png"
            )
        )
        users.add(
            DataObject(
                "Faiz Khan",
                "https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png"
            )
        )
        users.add(
            DataObject(
                "Yashar Khan",
                "https://www.talkwalker.com/images/2020/blog-headers/image-analysis.png"
            )
        )

        //creating our adapter
        val adapter = CategoriesAdapter(users)

        //now adding the adapter to recyclerview
        mBinding.recycleview.adapter = adapter

    }
}
