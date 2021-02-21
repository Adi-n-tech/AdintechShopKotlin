package com.adintech.shop.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adintech.shop.R
import com.adintech.shop.adapter.CategoriesAdapter
import com.adintech.shop.databinding.FragmentHomeBinding
import com.adintech.shop.model.category.pojo.Category
import com.adintech.shop.utils.APIResponse
import com.adintech.shop.utils.APIResponseHandler
import com.adintech.shop.utils.AppConstant
import com.adintech.shop.utils.Status
import com.adintech.shop.viewmodel.HomeViewModel
import com.practice.samplekotlinapp.utils.Utility.hide
import com.practice.samplekotlinapp.utils.Utility.show

class HomeFragment : Fragment(), APIResponseHandler {

    lateinit var mBinding: FragmentHomeBinding
    private lateinit var mViewModel: HomeViewModel
    var mCategoriesList = ArrayList<Category>()

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

        //set view model
        mViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        activity?.let { mViewModel.init(it.applicationContext) }

        initialise()
        return mBinding.root
    }

    @SuppressLint("WrongConstant")
    fun initialise() {
        //--------
        mViewModel.apiResponseMutableLiveData!!.observe(this,
            { apiResponse -> onAPIResponseHandler(apiResponse) })

        mBinding.recycleview.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

        mViewModel.doGetCategoryList(1001)
    }

    override fun onAPIResponseHandler(apiResponse: APIResponse?) {
        when (apiResponse!!.status) {
            Status.LOADING -> mBinding.progressBar.show()
            Status.SUCCESS -> {
                mBinding.progressBar.hide()
                when (apiResponse!!.requestID) {
                    AppConstant.API_REQUEST_1001 -> {
                        mCategoriesList = apiResponse.data as ArrayList<Category>

                        if (mCategoriesList != null) {
                            setAdaptor()
                        }
                    }
                }
            }
            Status.ERROR -> {
                mBinding.progressBar.hide()
            }
        }
    }

    //set adapter
    fun setAdaptor() {
        val customAdapter = CategoriesAdapter(requireContext(), mCategoriesList)
        mBinding.recycleview.adapter = customAdapter
    }
}