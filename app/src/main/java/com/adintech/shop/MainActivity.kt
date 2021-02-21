package com.adintech.shop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.adintech.shop.databinding.ActivityMainBinding
import com.adintech.shop.fragment.HomeFragment
import com.adintech.shop.fragment.ProfileFragment
import com.adintech.shop.fragment.WishlistFragment
import com.fxn.OnBubbleClickListener
import java.io.IOException
import java.nio.charset.Charset

/**
 * https://github.com/akshay2211/BubbleTabBar
 * */

class MainActivity : AppCompatActivity() {

    private val mHomeFragment = HomeFragment()
    private val mWishlistFragment = WishlistFragment()
    private val mProfileFragment = ProfileFragment()

    private val fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = mHomeFragment

    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setup binding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportActionBar?.hide()

        //on bottom navigation click
        mBinding.bubbleTabBar.addBubbleListener(
            (object : OnBubbleClickListener {
                override fun onBubbleClick(id: Int) {
                    when (id) {
                        R.id.home -> { fragmentManager.beginTransaction().hide(activeFragment).show(mHomeFragment).commit()
                        activeFragment = mHomeFragment }
                        R.id.wishlist -> {
                            fragmentManager.beginTransaction().hide(activeFragment).show(mWishlistFragment).commit()
                            activeFragment = mWishlistFragment }
                        R.id.profile -> {
                            fragmentManager.beginTransaction().hide(activeFragment).show(mProfileFragment).commit()
                            activeFragment = mProfileFragment  }
                    }
                }
            })
        )

        fragmentManager.beginTransaction().apply {
            add(R.id.container, mProfileFragment, "home").hide(mProfileFragment)
            add(R.id.container, mWishlistFragment, "wishlist").hide(mWishlistFragment)
            add(R.id.container, mHomeFragment, "profile")
        }.commit()
    }
}