package ui.doublegsoft.gux.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide


class ImageAdapter(private val imageUrls: List<String>, private val viewPager: ViewPager2) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

  class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {

    }

    fun bind(imagePath: String) {
      Log.d("", "hello bind")
      val imageView = ImageView(itemView.context).apply {
        layoutParams = LinearLayout.LayoutParams(
          LinearLayout.LayoutParams.MATCH_PARENT,
          LinearLayout.LayoutParams.MATCH_PARENT
        )
      }
      imageView.scaleType = ImageView.ScaleType.FIT_XY
      (itemView as CardView).addView(imageView)

      Glide.with(imageView.context)
        .load(imagePath)
        .into(imageView)
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
    val itemView = CardView(parent.context).apply {
      layoutParams = LinearLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        ConstraintLayout.LayoutParams.MATCH_PARENT
      )
    }
    return ImageViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
    val imageUrl = imageUrls[position]
    holder.bind(imageUrl)
  }

  override fun getItemCount(): Int {
    return imageUrls.size
  }
}


class SwiperNavigator @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  style: Int = 0
) : LinearLayout(context, attrs, style) {

  private var navigator: ViewPager2 = ViewPager2(context).apply {
    layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
  }

  fun setImages(images: List<String>) {
    val imageSize = images.size
    val adapter = ImageAdapter(images, navigator)
    navigator.adapter = adapter
    navigator.offscreenPageLimit = imageSize
    navigator.clipToPadding = false
    navigator.clipChildren = false

    addView(navigator)

    navigator.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
      override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)

        if (state == ViewPager2.SCROLL_STATE_IDLE) {
          when (navigator.currentItem) {
            imageSize - 1 -> navigator.setCurrentItem(1, false)
            0 -> navigator.setCurrentItem(imageSize - 2, false)
          }
        }
      }

      override fun onPageSelected(position: Int) {
        super.onPageSelected(position)

        if (position != 0 && position != imageSize - 1) {
          // pageIndicatorView.setSelected(position-1)
        }
      }
    })
  }

}