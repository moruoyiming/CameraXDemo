package com.example.android.cameraxextensions.model

import android.view.GestureDetector
import android.view.MotionEvent
import kotlin.math.abs

class SwipeGestureDetector : GestureDetector.SimpleOnGestureListener() {
    companion object {
        private const val MIN_SWIPE_DISTANCE_X = 100
    }

    var swipeCallback: SwipeCallback? = null

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        swipeCallback?.onScroll(e1, e2, distanceX, distanceY)
        return true;
//        return super.onScroll(e1, e2, distanceX, distanceY)

    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val deltaX = e1.x - e2.x
        val deltaXAbs = abs(deltaX)

        if (deltaXAbs >= MIN_SWIPE_DISTANCE_X) {
            if (deltaX > 0) {
                swipeCallback?.onLeftSwipe()
            } else {
                swipeCallback?.onRightSwipe()
            }
        }

        return true
    }

    interface SwipeCallback {
        fun onLeftSwipe()

        fun onRightSwipe()

        fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        )
    }

    fun setSwipeCallback(left: () -> Unit = {}, right: () -> Unit = {}, scroll: () -> Unit = {}) {
        swipeCallback = object : SwipeCallback {
            override fun onLeftSwipe() {
                left()
            }

            override fun onRightSwipe() {
                right()
            }

            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ) {
                scroll()
            }
        }
    }
}
