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
import com.adintech.shop.viewmodel.HomeViewModel


@Suppress("UNREACHABLE_CODE")
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
        mBinding.recycleview.layoutManager =
                LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

        mViewModel.doGetCategoryList(1001)
    }

    override fun onAPIResponseHandler(apiResponse: APIResponse?) {
        if (apiResponse?.requestID == 1001) {
            mCategoriesList = apiResponse.data as ArrayList<Category>

            if (mCategoriesList != null) {
                setAdaptor()
            }
        }
    }

    fun setAdaptor() {
        //creating our adapter
        val adapter = CategoriesAdapter(mCategoriesList)

        //now adding the adapter to recyclerview
        mBinding.recycleview.adapter = adapter
    }
}
