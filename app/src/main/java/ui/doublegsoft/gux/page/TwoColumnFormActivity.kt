package ui.doublegsoft.gux.page

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import ui.doublegsoft.gux.R
import ui.doublegsoft.gux.widget.TwoColumnForm

class TwoColumnFormActivity : AppCompatActivity() {

  private lateinit var content: ScrollView
  private lateinit var form: TwoColumnForm

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.actvitity_two_column_form)

    content = findViewById(R.id.content)
    form = findViewById(R.id.form)

    content.viewTreeObserver.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        content.viewTreeObserver.removeOnGlobalLayoutListener(this)
        val remainingHeight = content.rootView.height;
        content.layoutParams.height = remainingHeight
        content.setPadding(16, 16, 16, 16)
        content.requestLayout()
      }
    })

    // Example list of form fields
    val formFields = listOf(
      mapOf("name" to "text", "input" to "text", "title" to "名称", "hint" to ""),
      mapOf("name" to "email", "input" to "email", "title" to "邮箱", "hint" to ""),
      mapOf("name" to "number", "input" to "number", "title" to "年龄", "hint" to ""),
      mapOf("name" to "mobile", "input" to "mobile", "title" to "电话", "hint" to ""),
      mapOf("name" to "date", "input" to "date", "title" to "生日", "hint" to "")
    )

    // Generate the form fields
    form.setFields(formFields as List<Map<String,Object>>);
  }

}