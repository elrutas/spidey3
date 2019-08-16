package com.example.lucas.spidey3.ui.features.comicdetail

import android.view.GestureDetector
import android.view.MotionEvent

class FlingDetector(val swipeRightAction: () -> Unit, val swipeLeftAction: () -> Unit): GestureDetector.SimpleOnGestureListener() {

    private val SWIPE_MIN_DISTANCE = 180;
    private val SWIPE_THRESHOLD_VELOCITY = 200;
    private val SWIPE_MAX_OFF_PATH = 120

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        if (Math.abs(e1.y - e2.y) > SWIPE_MAX_OFF_PATH) {
            return false // dismiss vertical flings
        }

        if (e1.x - e2.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            swipeLeftAction.invoke()
        } else if (e2.x - e1.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            swipeRightAction.invoke()
        }

        return true;
    }
}