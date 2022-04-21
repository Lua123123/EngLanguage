package com.example.englanguage.fragmentflipcard1

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.englanguage.R
import com.example.englanguage.databinding.FragmentFlipCard2Binding
import com.example.englanguage.model.vocabulary.SuccessVocabulary
import com.example.englanguage.model.vocabulary.Vocabulary
import com.example.englanguage.network.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FlipCardFragment2 : Fragment() {
    lateinit var front_anim: AnimatorSet
    lateinit var behind_anim: AnimatorSet
    var isFront = true;
    private lateinit var binding: FragmentFlipCard2Binding
    var vocabulary: Vocabulary? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flip_card2, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickGetVocabulary()
        flipCart()


    }

    fun clickGetVocabulary(): List<SuccessVocabulary?>? {
        API.api.getVocabulary(1, "").enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                vocabulary = response.body()
                val successVocabulary = SuccessVocabulary(vocabulary?.success?.get(1)?.word.toString(),
                    vocabulary?.success?.get(1)?.mean.toString(), vocabulary?.success?.get(1)?.example.toString())
                binding.setVocabularyViewModel(successVocabulary)
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
                Toast.makeText(context, "Call api failed", Toast.LENGTH_SHORT).show()
            }
        })
        return null
    }

    private fun flipCart() {
        val scale: Float? = activity?.resources?.displayMetrics?.density
        //3
        binding.cartFront3?.cameraDistance = 8000 * scale!!
        binding.cartBehind3?.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(
            activity,
            R.animator.front_animator
        ) as AnimatorSet
        behind_anim = AnimatorInflater.loadAnimator(
            activity,
            R.animator.behind_animation
        ) as AnimatorSet

        //3
        binding.flipLayout3?.setOnClickListener {
            if (isFront) {
                front_anim.setTarget(binding.cartFront3)
                behind_anim.setTarget(binding.cartBehind3)
                front_anim.start()
                behind_anim.start()
                isFront = false
            } else {
                front_anim.setTarget(binding.cartBehind3)
                behind_anim.setTarget(binding.cartFront3)
                behind_anim.start()
                front_anim.start()
                isFront = true
            }
        }
    }
}