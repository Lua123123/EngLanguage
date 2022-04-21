package com.example.englanguage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.englanguage.adapter.ViewPagerAdapterFragment123
import com.example.englanguage.fragmentflipcard1.FlipCardFragment1
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.viewmodel.VocabularyViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class FlipCardActivity1 : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private var navigationView: BottomNavigationView? = null
    private var imgNext: ImageView? = null
    private var imgBack: ImageView? = null
    private var content_frame: FrameLayout? = null
    private var layoutConstraint: ConstraintLayout? = null
    private lateinit var successVocabulary: SuccessVocabulary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card1)

        navigationView = findViewById(R.id.bottom_nav)
        mViewPager = findViewById<ViewPager>(R.id.view_pager)
        content_frame = findViewById<FrameLayout>(R.id.content_frame)
        layoutConstraint = findViewById<ConstraintLayout>(R.id.layoutConstraint)

        imgNext = findViewById(R.id.imgNext)
        imgNext?.setOnClickListener {
            val intent = Intent(this, FlipCardActivity2::class.java)
            startActivity(intent)
        }

        imgBack = findViewById(R.id.imgBack)
        imgBack?.setOnClickListener {
            var intent = Intent(this, TopicActivity::class.java)
            startActivity(intent)
        }
        setUpViewPager()

        //LẤY DATA TỪ VOCABULARY ACTIVITY
//        val bundle = intent.extras ?: return
//        successVocabulary = (bundle["object"] as SuccessVocabulary?)!!
//
//        val word: String = successVocabulary.word
//        val mean: String = successVocabulary.mean
//        val flipCardFragment1 = FlipCardFragment1()
//        val bundleFragment1 = Bundle()
//        bundleFragment1.putString("word", word)
//        bundleFragment1.putString("mean", mean)
//        flipCardFragment1.arguments = bundleFragment1

//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.content_frame, flipCardFragment1)
//        fragmentTransaction.commit()

//        content_frame?.setOnClickListener {
//            mViewPager?.setVisibility(View.GONE)
//            content_frame?.setVisibility(View.VISIBLE)
//        }
//
//        layoutConstraint?.setOnClickListener {
//            mViewPager?.setVisibility(View.VISIBLE)
//            content_frame?.setVisibility(View.GONE)
//        }
    }

    private fun setUpViewPager() {
        val viewPagerAdapter =
            ViewPagerAdapterFragment123(
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