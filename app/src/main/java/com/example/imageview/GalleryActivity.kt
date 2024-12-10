package com.example.imageview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imageview.databinding.ActivityGalleryBinding

class GalleryActivity : AppCompatActivity() {
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imagesArray = intArrayOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
        )
        index = savedInstanceState?.getInt(KEY_INDEX) ?: 0
        binding.apply {
            photoIV.setImageResource(imagesArray[index])
            nextBTN.setOnClickListener {
                if (index < 4) {
                    photoIV.setImageResource(imagesArray[++index])
                } else {
                    val intent = Intent(this@GalleryActivity, FinalActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, index)
    }

    companion object {
        const val KEY_INDEX = "key index"
    }
}