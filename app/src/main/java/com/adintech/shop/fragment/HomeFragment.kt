package com.adintech.shop.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adintech.shop.R
import com.adintech.shop.adapter.CategoriesAdapter
import com.adintech.shop.adapter.SpecialOffersAdapter
import com.adintech.shop.adapter.TopSellingAdapter
import com.adintech.shop.databinding.FragmentHomeBinding
import com.adintech.shop.model.category.pojo.Category
import com.adintech.shop.model.special_offers.pojo.SpecialOffers
import com.adintech.shop.model.top_selling.pojo.TopSelling
import com.adintech.shop.utils.APIResponse
import com.adintech.shop.utils.APIResponseHandler
import com.adintech.shop.utils.AppConstant
import com.adintech.shop.utils.Status
import com.adintech.shop.viewmodel.HomeViewModel
import com.adintech.shop.viewmodel.SpecialOfferViewModel
import com.adintech.shop.viewmodel.TopSellingProductViewModel
import com.practice.samplekotlinapp.utils.Utility
import com.practice.samplekotlinapp.utils.Utility.hide
import com.practice.samplekotlinapp.utils.Utility.show
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), APIResponseHandler {

    lateinit var mBinding: FragmentHomeBinding
    private lateinit var mViewModel: HomeViewModel
    private lateinit var mTopSellingProductViewModel: TopSellingProductViewModel
    private lateinit var mSpecialOfferViewModel: SpecialOfferViewModel
    var mCategoriesList = ArrayList<Category>()
    var mTopSellingList = ArrayList<TopSelling>()
    var mSpecialOffersList = ArrayList<SpecialOffers>()
    var mBannerList: ArrayList<String> = ArrayList()
    var currentPage = 0

    var sampleImage = intArrayOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3
    )

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

        mTopSellingProductViewModel =
            ViewModelProvider(this).get(TopSellingProductViewModel::class.java)
        activity?.let { mTopSellingProductViewModel.init(it.applicationContext) }

        mSpecialOfferViewModel =
            ViewModelProvider(this).get(SpecialOfferViewModel::class.java)
        activity?.let { mSpecialOfferViewModel.init(it.applicationContext) }

        initialise()
        return mBinding.root
    }


    @SuppressLint("WrongConstant")
    fun initialise() {


        /*  //json fetch cardview images
        try {
            JSONObject obj = new JSONObject(Utility.loadJSONFromAsset(getContext(), "PlaceList.json"));
            JSONArray PlaceList = obj.getJSONArray("PlaceList");
            for (int i = 0; i < PlaceList.length(); i++) {
                JSONObject placeObj = PlaceList.getJSONObject(i);
                placeName.add(placeObj.getString("placeName"));
                placeIcon.add(placeObj.getString("placeIcon"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        //json fetch ViewPager Images
        try {
            val obj = JSONObject(
                Utility.loadJSONFromAsset(
                    requireContext(),
                    "doGetBannerList.json"
                )
            )
            val imageObj: JSONArray = obj.getJSONArray("bannerList")
            for (i in 0 until imageObj.length()) {
                //imageObj.getJSONArray();
                mBannerList.add(imageObj.getString(i))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        //set banner in image slider
        mBinding.carouselView.pageCount = mBannerList.size

        mBinding.carouselView.setImageListener { position, imageView ->
            Picasso.get()
                .load(mBannerList.get(position))
                .placeholder(R.drawable.no_image_icon)
                .error(R.drawable.no_image_icon)
                .into(imageView)
        }

        //--------
        mBinding.card.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                context,
                "Click On Card",
                Toast.LENGTH_SHORT
            ).show()
        })

        //--------
        mViewModel.apiResponseMutableLiveData!!.observe(this,
            { apiResponse -> onAPIResponseHandler(apiResponse) })

        mBinding.categoriesRecycleView.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

        mViewModel.doGetCategoryList(AppConstant.API_REQUEST_1001)

        //--------
        mTopSellingProductViewModel.apiResponseMutableLiveData!!.observe(this,
            { apiResponse -> onAPIResponseHandler(apiResponse) })

        mBinding.topSellingRecycleview.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

        mTopSellingProductViewModel.doGetTopSellingList(AppConstant.API_REQUEST_1002)

        //--------
        mSpecialOfferViewModel.apiResponseMutableLiveData!!.observe(this,
            { apiResponse -> onAPIResponseHandler(apiResponse) })

        mBinding.specialOfferRecycleview.layoutManager =
            LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)

        mSpecialOfferViewModel.doGetSpecialOffersList(AppConstant.API_REQUEST_1003)
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
                            setCategoriesAdaptor()
                        }
                    }

                    AppConstant.API_REQUEST_1002 -> {
                        mTopSellingList = apiResponse.data as ArrayList<TopSelling>

                        if (mTopSellingList != null) {
                            setTopSellingAdaptor()
                        }
                    }

                    AppConstant.API_REQUEST_1003 -> {
                        mSpecialOffersList = apiResponse.data as ArrayList<SpecialOffers>

                        if (mSpecialOffersList != null) {
                            setSpecialOffersAdaptor()
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
    private fun setCategoriesAdaptor() {
        val categoriesAdapter = CategoriesAdapter(requireContext(), mCategoriesList)
        mBinding.categoriesRecycleView.adapter = categoriesAdapter
    }

    private fun setTopSellingAdaptor() {
        val topSellingAdapter = TopSellingAdapter(requireContext(), mTopSellingList)
        mBinding.topSellingRecycleview.adapter = topSellingAdapter
    }


    private fun setSpecialOffersAdaptor() {
        val specialOffersAdapter = SpecialOffersAdapter(requireContext(), mSpecialOffersList)
        mBinding.specialOfferRecycleview.adapter = specialOffersAdapter
    }
}