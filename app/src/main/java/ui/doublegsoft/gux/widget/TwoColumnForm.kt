package ui.doublegsoft.gux.widget

import android.app.DatePickerDialog
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import java.util.*

class TwoColumnForm @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  style: Int = 0
) : LinearLayoutCompat(context, attrs, style) {

  init {
    orientation = VERTICAL

    setPadding(16, 16, 16, 16)
  }

  fun setFields(fields: List<Map<String, Object>>) {
    removeAllViews();
    fields.forEach { field ->
      val input = field["input"] as String
      val linearLayout = LinearLayout(context).apply {
        orientation = LinearLayout.HORIZONTAL
      }
      val textView = TextView(context).apply {
        text = (field["title"] as String) + "："
        layoutParams = LinearLayout.LayoutParams(
          200,
          ViewGroup.LayoutParams.WRAP_CONTENT
        )
      }

      val editText = EditText(context).apply {
        id = (field["name"] as String).hashCode()
        hint = field["hint"] as String
        layoutParams = LinearLayout.LayoutParams(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.WRAP_CONTENT
        )

        when (input) {
          "text" -> inputType = InputType.TYPE_CLASS_TEXT
          "email" -> inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
          "number" -> inputType = InputType.TYPE_CLASS_NUMBER
          "mobile" -> inputType = InputType.TYPE_CLASS_PHONE
        }
      }
      linearLayout.addView(textView)
      linearLayout.addView(editText)

      if (input.equals("date")) {
        editText.isFocusable = false
        editText.isCursorVisible = false
        editText.setOnClickListener {
          val calendar = Calendar.getInstance()
          val year = calendar.get(Calendar.YEAR)
          val month = calendar.get(Calendar.MONTH)
          val day = calendar.get(Calendar.DAY_OF_MONTH)

          val datePickerDialog = DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            editText.setText(selectedDate)
          }, year, month, day)
          datePickerDialog.show()
        }
      }

      addView(linearLayout)
    }
  }

//  fun getFormData(): Map<Int, String> {
//    val formData = mutableMapOf<Int, String>()
//    for (i in 0 until childCount) {
//      val view = getChildAt(i)
//      if (view is EditText) {
//        formData[view.id] = view.text.toString()
//      }
//    }
//    return formData
//  }

}