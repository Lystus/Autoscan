package com.example.autoscan
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

object AnimationUtils {
    fun expand(viewBeingAnimated: View)
    {
        viewBeingAnimated.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val targetHeight: Int = viewBeingAnimated.measuredHeight

        viewBeingAnimated.layoutParams.height = 1
        viewBeingAnimated.visibility = View.VISIBLE

        val expandAnimation: Animation = object : Animation()
        {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation?)
            {

                viewBeingAnimated.layoutParams.height =
                    if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()

                viewBeingAnimated.requestLayout()
            }

            override fun willChangeBounds() = true
        }


        expandAnimation.duration = (targetHeight / getViewDensity(viewBeingAnimated)).toLong()
        viewBeingAnimated.startAnimation(expandAnimation)
    }
    fun collapse(viewBeingAnimated: View)
    {

        val initialHeight: Int = viewBeingAnimated.measuredHeight

        val collapseAnimation: Animation = object : Animation()
        {

            override fun applyTransformation(interpolatedTime: Float, t: Transformation?)
            {
                if (interpolatedTime == 1f)
                    viewBeingAnimated.visibility = View.GONE
                else
                {
                    viewBeingAnimated.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()

                    viewBeingAnimated.requestLayout()
                }
            }
            override fun willChangeBounds() = true
        }

        collapseAnimation.duration = (initialHeight / getViewDensity(viewBeingAnimated)).toLong()
        viewBeingAnimated.startAnimation(collapseAnimation)
    }

    private fun getViewDensity(view: View) = view.context.resources.displayMetrics.density
}