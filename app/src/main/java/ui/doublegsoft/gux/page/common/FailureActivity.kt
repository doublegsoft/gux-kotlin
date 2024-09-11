package ui.doublegsoft.gux.page.common

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setPadding
import ui.doublegsoft.gux.MainActivity
import ui.doublegsoft.gux.R
import ui.doublegsoft.gux.Styles

class FailureActivity : AppCompatActivity() {

  val idFailureImageView: Int = 101

  val idButtonBackToHome: Int = 102

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // 创建 ConstraintLayout
    val constraintLayout = ConstraintLayout(this).apply {
      layoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        ConstraintLayout.LayoutParams.MATCH_PARENT
      )
      // 设置主题
      setTheme(R.style.AppTheme_NoActionBar)
    }

    // 创建 ImageView
    val failureImageView = ImageView(this).apply {
      id = idFailureImageView
      layoutParams = ConstraintLayout.LayoutParams(Styles.dpToPx(context, 300f), Styles.dpToPx(context, 300f))
    }

    val inputStream = assets.open("images/common/failure.png")
    // Decode the input stream into a Bitmap
    val bitmap = BitmapFactory.decodeStream(inputStream)
    bitmap?.let {
      failureImageView.setImageBitmap(it)
    }

    // 创建 Button
    val buttonBackToHome = Button(this).apply {
      id = idButtonBackToHome
      text = "回到首页"
      textSize = 22f
      setTextColor(android.graphics.Color.WHITE)
      setPadding(Styles.dpToPx(context, 16f))
      elevation = 4f
      layoutParams = ConstraintLayout.LayoutParams(Styles.dpToPx(context, 200f), Styles.dpToPx(context, 64f))
    }

    val shape = GradientDrawable()
    shape.setColor(Color.parseColor("#FF5722"))
    shape.cornerRadius = 128f
    buttonBackToHome.background = shape

    buttonBackToHome.setOnClickListener({
      val intent = Intent(this, MainActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
      startActivity(intent)
    })

    // 将 ImageView 和 Button 添加到 ConstraintLayout
    constraintLayout.addView(failureImageView)
    constraintLayout.addView(buttonBackToHome)

    // 使用 ConstraintSet 设置约束
    val constraintSet = ConstraintSet()
    constraintSet.clone(constraintLayout)

    // 设置 ImageView 的约束
    constraintSet.connect(
      failureImageView.id, ConstraintSet.TOP,
      ConstraintSet.PARENT_ID, ConstraintSet.TOP
    )
    constraintSet.connect(
      failureImageView.id, ConstraintSet.START,
      ConstraintSet.PARENT_ID, ConstraintSet.START
    )
    constraintSet.connect(
      failureImageView.id, ConstraintSet.END,
      ConstraintSet.PARENT_ID, ConstraintSet.END
    )
    constraintSet.connect(
      failureImageView.id, ConstraintSet.BOTTOM,
      buttonBackToHome.id, ConstraintSet.TOP
    )

    // 设置 Button 的约束
    constraintSet.connect(
      buttonBackToHome.id, ConstraintSet.TOP,
      failureImageView.id, ConstraintSet.BOTTOM
    )
    constraintSet.connect(
      buttonBackToHome.id, ConstraintSet.START,
      ConstraintSet.PARENT_ID, ConstraintSet.START
    )
    constraintSet.connect(
      buttonBackToHome.id, ConstraintSet.END,
      ConstraintSet.PARENT_ID, ConstraintSet.END
    )
    constraintSet.connect(
      buttonBackToHome.id, ConstraintSet.BOTTOM,
      ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM
    )

    // 应用约束
    constraintSet.applyTo(constraintLayout)

    // 设置内容视图
    setContentView(constraintLayout)

    supportActionBar?.hide()

  }

}

