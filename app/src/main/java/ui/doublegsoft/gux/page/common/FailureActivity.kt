package ui.doublegsoft.gux.page.common

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import ui.doublegsoft.gux.MainActivity
import ui.doublegsoft.gux.R

class FailureActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_failure)

    val failureImageView: ImageView = findViewById(R.id.failureImageView)
    val inputStream = assets.open("images/common/failure.png")
    // Decode the input stream into a Bitmap
    val bitmap = BitmapFactory.decodeStream(inputStream)
    bitmap?.let {
      failureImageView.setImageBitmap(it)
    }

    supportActionBar?.hide()

    val buttonBackToHome: Button = findViewById(R.id.buttonBackToHome)

    val shape = GradientDrawable()
    shape.setColor(Color.parseColor("#FF5722"))
    shape.cornerRadius = 128f
    buttonBackToHome.background = shape

    buttonBackToHome.setOnClickListener({
      val intent = Intent(this, MainActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
      startActivity(intent)
    })
  }
}

