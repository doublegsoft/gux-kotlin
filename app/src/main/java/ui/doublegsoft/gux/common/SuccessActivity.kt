package ui.doublegsoft.gux.common

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ui.doublegsoft.gux.R

class SuccessActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_common_success)

    val successImageView: ImageView = findViewById(R.id.successImageView)
    val inputStream = assets.open("images/common/success.png")
    // Decode the input stream into a Bitmap
    val bitmap = BitmapFactory.decodeStream(inputStream)
    bitmap?.let {
      successImageView.setImageBitmap(it)
    }

    supportActionBar?.hide()
  }
}

