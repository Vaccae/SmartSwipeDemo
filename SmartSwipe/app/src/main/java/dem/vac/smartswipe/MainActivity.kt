package dem.vac.smartswipe

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.billy.android.swipe.SmartSwipe
import com.billy.android.swipe.SmartSwipeRefresh
import com.billy.android.swipe.SmartSwipeWrapper
import com.billy.android.swipe.SwipeConsumer
import com.billy.android.swipe.consumer.*
import com.billy.android.swipe.listener.SimpleSwipeListener
import com.billy.android.swipe.listener.SwipeListener
import com.billy.android.swipe.support.SmartSwipeWrapperSupport

class MainActivity : AppCompatActivity() {

    lateinit var layout: LinearLayout
    lateinit var childlayout: RelativeLayout
    lateinit var tv1: TextView
    lateinit var tv2: TextView
    lateinit var tv3: TextView
    lateinit var tv4: TextView
    lateinit var tv5: TextView
    lateinit var tv6: TextView
    lateinit var tv7: TextView
    lateinit var tv8: TextView
    lateinit var tv9: TextView
    lateinit var tv10: TextView
    lateinit var tv11: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initcontrol()

        //增加滑动抽屉效果
        SmartSwipe.wrap(layout).addConsumer(DrawerConsumer())
            .setAllDirectionDrawerView(childlayout)
            .setScrimColor(Color.GRAY)
            .setShadowColor(Color.RED)

        //仿iOS的弹性留白效果
        //侧滑时表现为弹性留白效果，结束后自动恢复
        SmartSwipe.wrap(tv1).addConsumer(SpaceConsumer())
            .enableHorizontal()   //工作方向：横向


        //仿MIUI的弹性拉伸效果：
        //侧滑时表现为弹性拉伸效果，结束后自动恢复
        SmartSwipe.wrap(tv2).addConsumer(StretchConsumer())
            .enableHorizontal()   //工作方向：横向

        //增加滑动抽屉联动效果
        SmartSwipe.wrap(tv3).addConsumer(SlidingConsumer())
            .setHorizontalDrawerView(childlayout)

        //侧滑透明效果，使用贝塞尔曲线效果
        var str4 = tv4.text.toString()
        SmartSwipe.wrap(tv4).addConsumer(BezierBackConsumer())
            .enableHorizontal()
            .addListener(object : SimpleSwipeListener() {
                override fun onSwipeOpened(
                    wrapper: SmartSwipeWrapper?,
                    consumer: SwipeConsumer?,
                    direction: Int
                ) {
                    //删除效果
//                    val viewparent = wrapper?.parent
//                    if (viewparent is ViewGroup) {
//                        viewparent.removeView(wrapper)
//                    }
                    var directionstr = ""
                    when (direction) {
                        1 -> directionstr = "右"
                        2 -> directionstr = "左"
                        8 -> directionstr = "上"
                        4 -> directionstr = "下"
                        else -> directionstr = "无"
                    }
                    tv4.text = str4 + directionstr

                }
            })

        //侧滑手势识别功能
        SmartSwipe.wrap(tv5).addConsumer(StayConsumer())
            .enableAllDirections()
            .addListener(object : SimpleSwipeListener() {
                override fun onSwipeOpened(
                    wrapper: SmartSwipeWrapper?,
                    consumer: SwipeConsumer?,
                    direction: Int
                ) {
                    var directionstr = ""
                    when (direction) {
                        1 -> directionstr = "右"
                        2 -> directionstr = "左"
                        8 -> directionstr = "上"
                        4 -> directionstr = "下"
                        else -> directionstr = "无"
                    }
                    var oldtext: String = tv5.text.toString()
                    tv5.text = oldtext + directionstr
                }
            })

        //实现开门效果
        SmartSwipe.wrap(tv6).addConsumer(DoorConsumer())
            .enableHorizontal()
            .addListener(object : SimpleSwipeListener() {
                override fun onSwipeOpened(
                    wrapper: SmartSwipeWrapper?,
                    consumer: SwipeConsumer?,
                    direction: Int
                ) {
                    tv6.text = tv6.text.toString() + "Vaccae"
                }
            })

        //Activity百叶窗返回效果
        SmartSwipe.wrap(tv7).addConsumer(ShuttersConsumer())
            .enableHorizontal()
            .addListener(object : SimpleSwipeListener() {
                override fun onSwipeOpened(
                    wrapper: SmartSwipeWrapper?,
                    consumer: SwipeConsumer?,
                    direction: Int
                ) {
                    var intent = Intent(applicationContext, TestActivity::class.java)
                    intent.putExtra("type", 0)
                    startActivity(intent)
                }
            })

        //Activity开门返回效果
        SmartSwipe.wrap(tv8).addConsumer(StretchConsumer())
            .enableHorizontal()
            .addListener(object : SimpleSwipeListener() {
                override fun onSwipeOpened(
                    wrapper: SmartSwipeWrapper?,
                    consumer: SwipeConsumer?,
                    direction: Int
                ) {
                    var intent = Intent(applicationContext, TestActivity::class.java)
                    intent.putExtra("type", 1)
                    startActivity(intent)
                }
            })

        //下拉框刷新
        //xxxMode第二个参数为false，表示工作方向为纵向：下拉刷新&上拉加载更多
        //如果第二个参数设置为true，则表示工作方向为横向：右拉刷新&左拉加载更多
        var refcount9 = 0;
        var str9 = tv9.text.toString()
        SmartSwipeRefresh.drawerMode(tv9, false).dataLoader = object :
            SmartSwipeRefresh.SmartSwipeRefreshDataLoader {
            override fun onLoadMore(ssr: SmartSwipeRefresh?) {
                refcount9--
                tv9.text = str9 + refcount9
                ssr?.finished(true)
            }

            override fun onRefresh(ssr: SmartSwipeRefresh?) {
                ssr?.finished(true)
                refcount9++
                tv9.text = str9 + refcount9
            }
        }

        //下拉框刷新
        //xxxMode第二个参数为false，表示工作方向为纵向：下拉刷新&上拉加载更多
        //如果第二个参数设置为true，则表示工作方向为横向：右拉刷新&左拉加载更多
        var refcount10 = 0;
        var str10 = tv10.text.toString()
        SmartSwipeRefresh.behindMode(tv10, true).dataLoader = object :
            SmartSwipeRefresh.SmartSwipeRefreshDataLoader {
            override fun onLoadMore(ssr: SmartSwipeRefresh?) {
                refcount10--
                tv10.text = str10 + refcount10
                ssr?.finished(true)
            }

            override fun onRefresh(ssr: SmartSwipeRefresh?) {
                ssr?.finished(true)
                refcount10++
                tv10.text = str10 + refcount10

            }
        }

        //下拉框刷新
        //xxxMode第二个参数为false，表示工作方向为纵向：下拉刷新&上拉加载更多
        //如果第二个参数设置为true，则表示工作方向为横向：右拉刷新&左拉加载更多
        var refcount11 = 0;
        var str11 = tv11.text.toString()
        SmartSwipeRefresh.scaleMode(tv11, false).dataLoader = object :
            SmartSwipeRefresh.SmartSwipeRefreshDataLoader {
            override fun onLoadMore(ssr: SmartSwipeRefresh?) {
                refcount11--
                tv11.text = str11 + refcount11
                ssr?.finished(true)
            }

            override fun onRefresh(ssr: SmartSwipeRefresh?) {
                ssr?.finished(true)
                refcount11++
                tv11.text = str11 + refcount11

            }
        }

        //下拉框刷新
        //xxxMode第二个参数为false，表示工作方向为纵向：下拉刷新&上拉加载更多
        //如果第二个参数设置为true，则表示工作方向为横向：右拉刷新&左拉加载更多
        SmartSwipeRefresh.translateMode(tv11, true).dataLoader = object :
            SmartSwipeRefresh.SmartSwipeRefreshDataLoader {
            override fun onLoadMore(ssr: SmartSwipeRefresh?) {
                refcount11--
                tv11.text = str11 + refcount11
                ssr?.finished(true)
            }

            override fun onRefresh(ssr: SmartSwipeRefresh?) {
                ssr?.finished(true)
                refcount11++
                tv11.text = str11 + refcount11

            }
        }
    }

    //初始化控件
    private fun initcontrol() {
        layout = findViewById(R.id.mainlayout)
        childlayout = findViewById(R.id.childlayout)
        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)
        tv4 = findViewById(R.id.tv4)
        tv5 = findViewById(R.id.tv5)
        tv6 = findViewById(R.id.tv6)
        tv7 = findViewById(R.id.tv7)
        tv8 = findViewById(R.id.tv8)
        tv9 = findViewById(R.id.tv9)
        tv10 = findViewById(R.id.tv10)
        tv11 = findViewById(R.id.tv11)
    }
}
