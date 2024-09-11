package ui.doublegsoft.gux

import android.content.Context
import android.util.TypedValue

class Styles {

  companion object {
    @JvmStatic
    fun dpToPx(context: Context, dp: Float): Int {
      return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
      ).toInt()
    }
  }
}