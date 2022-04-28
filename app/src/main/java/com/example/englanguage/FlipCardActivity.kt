package com.example.englanguage

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.englanguage.adapter.ViewPagerAdapterFragment123
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.network.API
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlipCardActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    private var navigationView: BottomNavigationView? = null
    private var imgNext: ImageView? = null
    private var imgBack: ImageView? = null
    private var content_frame: FrameLayout? = null
    private var layoutConstraint: ConstraintLayout? = null
    private lateinit var successVocabulary: SuccessVocabulary
    private var vocabulary: Vocabulary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card)

        navigationView = findViewById(R.id.bottom_nav)
        mViewPager = findViewById<ViewPager>(R.id.view_pager)
        content_frame = findViewById<FrameLayout>(R.id.content_frame)
        layoutConstraint = findViewById<ConstraintLayout>(R.id.layoutConstraint)

//        imgNext = findViewById(R.id.imgNext)
//        imgNext?.setOnClickListener {
//            val intent = Intent(this, VocabularyOfTopicActivity::class.java)
//            startActivity(intent)
//        }

        imgBack = findViewById(R.id.imgBack)
        imgBack?.setOnClickListener {
            var intent = Intent(this, TopicActivity::class.java)
            startActivity(intent)
        }
        setUpViewPager()
//        clickGetVocabulary()

        val successVocabulary = SuccessVocabulary(vocabulary?.success?.get(0)?.word.toString(),
            vocabulary?.success?.get(0)?.mean.toString(), vocabulary?.success?.get(0)?.example.toString())

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

    ////////////////
    fun clickGetVocabulary(): List<SuccessVocabulary?>? {
        API.api.getVocabulary(1, "").enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                vocabulary = response.body()
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
                Toast.makeText(this@FlipCardActivity, "Call api failed", Toast.LENGTH_SHORT).show()
            }
        })
        return null
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