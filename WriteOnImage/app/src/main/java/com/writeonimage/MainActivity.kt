package com.writeonimage

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.*
import android.text.TextUtils


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val mCAMERAPERMISSION = 12
    var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_capture.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            button_capture -> {
                name = edit_watermark_name.text.toString()
                if (!TextUtils.isEmpty(name) && !edit_watermark_name.text.equals(""))
                    setUpPermission()
                else
                    edit_watermark_name.setError("Please Enter value")
            }
        }
    }

    private fun setUpPermission() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (Build.VERSION.SDK_INT >= 23) {
            if (permission != PackageManager.PERMISSION_GRANTED) {
                makeRequest()
            } else {
                openCamera()
            }
        } else {
            openCamera()
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, 13);
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            mCAMERAPERMISSION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            mCAMERAPERMISSION -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    makeRequest()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                13 -> {
                    val extras = data?.extras
                    val imageBitmap = extras?.get("data") as Bitmap
                    val point = Point(60, 230)
                    val bitmap =
                        mark(imageBitmap, name, point, Color.WHITE, 150, 12, false)
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }

    fun mark(
        src: Bitmap,
        watermark: String,
        location: Point, // Point
        color: Int,
        alpha: Int,
        size: Int,
        underline: Boolean
    ): Bitmap {
        val w = src.width
        val h = src.height
        val result = Bitmap.createBitmap(w, h, src.config)

        val canvas = Canvas(result)
        canvas.drawBitmap(src, 0f, 0f, null)

        val paint = Paint()
        paint.setColor(color)
        paint.alpha = alpha
        paint.textSize = size.toFloat()
        paint.isAntiAlias = true
        paint.isUnderlineText = underline
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC))
        canvas.drawText(watermark, location.x.toFloat(), location.y.toFloat(), paint)
        return result
    }
}