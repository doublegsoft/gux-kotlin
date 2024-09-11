package ui.doublegsoft.gux.page.navigable

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import ui.doublegsoft.gux.R
import ui.doublegsoft.gux.Styles
import ui.doublegsoft.gux.page.TwoColumnFormActivity
import ui.doublegsoft.gux.page.common.FailureActivity
import ui.doublegsoft.gux.page.common.SuccessActivity

class IndexFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    Log.d("", "index")
    val root = inflater.inflate(R.layout.fragment_index, container, false)
    val rootLayout = root.findViewById<LinearLayout>(R.id.rootLayout)

    rootLayout.addView(buildTile(root.context, "操作成功", "流程化页面操作的成功提示页", SuccessActivity::class.java))
    rootLayout.addView(buildDivider(root.context))
    rootLayout.addView(buildTile(root.context, "操作失败", "流程化页面操作的失败提示页", FailureActivity::class.java))
    rootLayout.addView(buildDivider(root.context))
    rootLayout.addView(buildTile(root.context, "编辑表单", "需要用户输入信息的页面", TwoColumnFormActivity::class.java))
    rootLayout.addView(buildDivider(root.context))

    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onDestroyView() {
    super.onDestroyView()
  }

  fun buildTile(context: Context, title: String, subtitle: String, activityClass: Class<out AppCompatActivity>) : LinearLayout {

    // 创建外层 LinearLayout
    val outerLinearLayout = LinearLayout(context).apply {
      layoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        ConstraintLayout.LayoutParams.WRAP_CONTENT
      )
      orientation = LinearLayout.HORIZONTAL
      setPadding(Styles.dpToPx(context, 16f))
      isClickable = true
      isFocusable = true
    }
    
    // 创建内层 LinearLayout
    val innerLinearLayout = LinearLayout(context).apply {
      layoutParams = LinearLayout.LayoutParams(
        0,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        1f
      )
      orientation = LinearLayout.VERTICAL
      isClickable = true
    }

    innerLinearLayout.setOnClickListener {
      val intent = Intent(context, activityClass)
      startActivity(intent)
    }

    // 创建第一个 TextView
    val firstTextView = TextView(context).apply {
      layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      )
      textSize = 18f
      setTextColor(resources.getColor(android.R.color.black))
      text = title
    }

    // 创建第二个 TextView
    val secondTextView = TextView(context).apply {
      layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      )
      textSize = 14f
      setTextColor(resources.getColor(android.R.color.darker_gray))
      text = subtitle
    }

    // 创建第三个 TextView
    val thirdTextView = TextView(context).apply {
      layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
      )
      textSize = 14f
      setTextColor(resources.getColor(android.R.color.darker_gray))
      setPadding(Styles.dpToPx(context, 16f))
    }

    // 将 TextView 添加到内层 LinearLayout
    innerLinearLayout.addView(firstTextView)
    innerLinearLayout.addView(secondTextView)

    // 将内层 LinearLayout 和第三个 TextView 添加到外层 LinearLayout
    outerLinearLayout.addView(innerLinearLayout)
    outerLinearLayout.addView(thirdTextView)

    return outerLinearLayout
  }

  fun buildDivider(context: Context) : View? {
    return View(context).apply {
      layoutParams = ConstraintLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        Styles.dpToPx(context, 1f)
      )
      setBackgroundColor(0xFFCCCCCC.toInt())
    }
  }

}