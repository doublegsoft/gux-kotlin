package ui.doublegsoft.gux

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_welcome)

    val imageView: ImageView = findViewById(R.id.imageView)
    val inputStream = assets.open("images/logo.png")
    // Decode the input stream into a Bitmap
    val bitmap = BitmapFactory.decodeStream(inputStream)
    bitmap?.let {
      imageView.setImageBitmap(it)
    }

    val buttonStart: Button = findViewById(R.id.buttonStart)

    val shape = GradientDrawable()
    shape.setColor(Color.parseColor("#FF5722"))
    shape.cornerRadius = 128f
    buttonStart.background = shape

    buttonStart.setOnClickListener {
      val intent = Intent(this, MainActivity::class.java)
      startActivity(intent)
    }
  }
}