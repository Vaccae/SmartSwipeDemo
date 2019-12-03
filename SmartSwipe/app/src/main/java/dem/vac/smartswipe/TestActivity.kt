package dem.vac.smartswipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.billy.android.swipe.SmartSwipe
import com.billy.android.swipe.consumer.ActivityDoorBackConsumer
import com.billy.android.swipe.consumer.ActivityShuttersBackConsumer
import com.billy.android.swipe.consumer.ActivitySlidingBackConsumer

class TestActivity : AppCompatActivity() {

    var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        type = intent.getIntExtra("type", 0)

        when (type) {
            0 -> SmartSwipe.wrap(this).addConsumer(ActivitySlidingBackConsumer(this))
                //设置联动系数
                .setRelativeMoveFactor(1.0f)
                //设置返回方向，这是所有方向
                .enableAllDirections()
            1 -> SmartSwipe.wrap(this).addConsumer(ActivityDoorBackConsumer(this))
                .enableAllDirections()

        }

    }
}
