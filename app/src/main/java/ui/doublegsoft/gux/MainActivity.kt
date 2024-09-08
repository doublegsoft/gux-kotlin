package ui.doublegsoft.gux

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import ui.doublegsoft.gux.page.common.FailureActivity
import ui.doublegsoft.gux.page.common.SuccessActivity

class MainActivity : AppCompatActivity() {

  private lateinit var listView: ListView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val buttonGotoSuccess: LinearLayout = findViewById(R.id.buttonGotoSuccess)

    // Set an OnClickListener to handle clicks
    buttonGotoSuccess.setOnClickListener {
      val intent = Intent(this, SuccessActivity::class.java)
      startActivity(intent)
    }

    val buttonGotoFailure: LinearLayout = findViewById(R.id.buttonGotoFailure)

    // Set an OnClickListener to handle clicks
    buttonGotoFailure.setOnClickListener {
      val intent = Intent(this, FailureActivity::class.java)
      startActivity(intent)
    }
  }

  override fun onBackPressed() {

  }
}