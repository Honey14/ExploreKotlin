package com.honey.timefighter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.view.View


class CustomView(context: Context) : View(context) {
    internal lateinit var paint: Paint
    internal var radius = 0

    init {
        init()
    }

    private fun init() {
        paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLUE
        paint.isAntiAlias = true
        paint.strokeWidth = 5f
    }

    fun updateView(radius: Int) {
        this.radius = radius
        invalidate()
    }

    protected override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(),
            radius.toFloat(), paint)
    }
}