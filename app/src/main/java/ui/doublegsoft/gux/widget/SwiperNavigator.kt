package ui.doublegsoft.gux.widget

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide


class ImageAdapter(private val imageUrls: List<String>, private val viewPager: ViewPager2) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

  class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imageView: ImageView = ImageView(itemView.context).apply {
      layoutParams = LinearLayout.LayoutParams(
        ConstraintLayout.LayoutParams.MATCH_PARENT,
        ConstraintLayout.LayoutParams.MATCH_PARENT
      )
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
    return ImageViewHolder(viewPager)
  }

  override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
    val imageUrl = imageUrls[position]
    Log.d("", imageUrl)
    Glide.with(holder.imageView.context)
      .load(imageUrl)
      .into(holder.imageView)
  }

  override fun getItemCount(): Int {
    return imageUrls.size
  }
}


class SwiperNavigator @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  style: Int = 0
) : LinearLayoutCompat(context, attrs, style) {

  lateinit var navigator: ViewPager2;

  override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
    navigator = ViewPager2(context).apply {
      layoutParams = LinearLayout.LayoutParams(
        300,
        300
      )
    }

    val adapter = ImageAdapter(listOf(
      "https://picsum.photos/500/300", "https://picsum.photos/500/300", "https://picsum.photos/500/300"
    ), navigator)
    navigator.adapter = adapter
    navigator.offscreenPageLimit = 3
    navigator.clipToPadding = false
    navigator.clipChildren = false

    addView(navigator)
  }

}