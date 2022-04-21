package com.example.englanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.englanguage.adapter.ViewPagerAdapterFragment123
import com.example.englanguage.adapter.ViewPagerAdapterFragment456
import com.google.android.material.bottomnavigation.BottomNavigationView

class FlipCardActivity2 : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager
    private var navigationView: BottomNavigationView? = null
    private var imgBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card2)
        navigationView = findViewById(R.id.bottom_nav)
        mViewPager = findViewById<ViewPager>(R.id.view_pager)
        imgBack = findViewById(R.id.imgBack)
        imgBack?.setOnClickListener {
            var intent = Intent(this, FlipCardActivity1::class.java)
            startActivity(intent)
        }
        setUpViewPager()

    }

    private fun setUpViewPager() {
        val viewPagerAdapter =
            ViewPagerAdapterFragment456(
                supportFragmentManager,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> navigationView?.getMenu()?.findItem(R.id.call_api)?.setChecked(true)
                    1 -> navigationView?.getMenu()?.findItem(R.id.pos_api)?.setChecked(true)
                    2 -> navigationView?.getMenu()?.findItem(R.id.view_pager)?.setChecked(true)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }
}