package ui.doublegsoft.gux.page.navigable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ui.doublegsoft.gux.R
import ui.doublegsoft.gux.widget.SwiperNavigator

class LoopingPageTransformer(private val itemCount: Int) : ViewPager2.PageTransformer {

  override fun transformPage(page: View, position: Float) {
    val pageWidth = page.width
    val pageHeight = page.height

    when {
      position < -1 -> { // [-Infinity,-1)
        page.translationX = (pageWidth * -1).toFloat()
      }
      position <= 1 -> { // [-1,1]
        page.translationX = pageWidth * -position
      }
      else -> { // (1,+Infinity]
        page.translationX = pageWidth.toFloat()
      }
    }
  }
}


class HomeFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    Log.d("", "home")
    val root = inflater.inflate(R.layout.fragment_home, container, false)
    val swiperNavigator = root.findViewById<SwiperNavigator>(R.id.swiperNavigator)


//    // 设置初始位置
//    viewPager.setCurrentItem(adapter.itemCount / 2, false)
//
//    // 设置 PageTransformer
//    viewPager.setPageTransformer(LoopingPageTransformer(3))
    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)


  }

  override fun onDestroyView() {
    super.onDestroyView()
  }

}