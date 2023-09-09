package com.jujodevs.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.jujodevs.horoscapp.R
import com.jujodevs.horoscapp.databinding.FragmentLuckBinding
import com.jujodevs.horoscapp.ui.core.FragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

@AndroidEntryPoint
class LuckFragment() : FragmentBinding<FragmentLuckBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLuckBinding
        get() = FragmentLuckBinding::inflate

    override fun setup() {
        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.ivRoulette.setOnClickListener {
            spinRoulette()
        }
    }

    private fun spinRoulette(right: Boolean = true) {
        val random = Random()
        var degrees = random.nextInt(360) + 360
        if (!right){
            degrees *= -1
        }
        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard(){
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        slideUpAnimation.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.ivReverse.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.ivReverse.startAnimation(slideUpAnimation)
    }

    private fun growCard() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)
        growAnimation.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.ivReverse.isVisible = false
                showPremonitionView()
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.ivReverse.startAnimation(growAnimation)
    }

    private fun showPremonitionView() {
        val dissapearAnimation = AlphaAnimation(1.0f, 0.0f)
        dissapearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        dissapearAnimation.setAnimationListener(object : AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

        binding.preview.startAnimation(dissapearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }


}