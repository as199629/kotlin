package tw.tcnr23.m1004

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class M1003 : AppCompatActivity(), ViewSwitcher.ViewFactory, View.OnClickListener {
    private val intent01 = Intent()
    private var b001: ImageButton? = null
    private var b002: ImageButton? = null
    private var b003: ImageButton? = null
    private var t004: TextView? = null
    private var t005: TextView? = null
    private var user_select: String? = null
    private var answer: String? = null
    private var c001: ImageSwitcher? = null
    private var startMusic: MediaPlayer? = null
    private var mediaWin: MediaPlayer? = null
    private var mediaLose: MediaPlayer? = null
    private var mediaDraw: MediaPlayer? = null
    private var mediaBye: MediaPlayer? = null
    private val imgSwi: ImageSwitcher? = null
    private var r_layout: RelativeLayout? = null
    private var b004: Button? = null
    private var b005: Button? = null
    private var b006: Button? = null
    private var bwin = 0
    private var bdraw = 0
    private var blose = 0
//    private val intent = Intent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m1003)
        setupViewcomponent()
    }

    private fun setupViewcomponent() {
        b001 = findViewById<View>(R.id.m1003_b001) as ImageButton
        b002 = findViewById<View>(R.id.m1003_b002) as ImageButton
        b003 = findViewById<View>(R.id.m1003_b003) as ImageButton
        b004 = findViewById<View>(R.id.m1003_b004) as Button
        b005 = findViewById<View>(R.id.m1003_b005) as Button
        b006 = findViewById<View>(R.id.m1003_b006) as Button
        t004 = findViewById<View>(R.id.m1003_t004) as TextView //你選擇
        t005 = findViewById<View>(R.id.m1003_t005) as TextView //判定輸贏
        c001 = findViewById<View>(R.id.m1003_c001) as ImageSwitcher
        c001!!.setFactory(this) //有這行才能做動畫


        //開機動畫
        r_layout = findViewById<View>(R.id.m1003_r001) as RelativeLayout
        r_layout!!.setBackgroundResource(R.drawable.back01)
        r_layout!!.animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale_rotate_start)

        //設定imageButton初始值為透明
        u_setalpha()

        //open music
        startMusic =
            MediaPlayer.create(applicationContext, R.raw.guess) //getApplicationContext=this***
        startMusic!!.start()

        //設定音樂連結
        mediaWin = MediaPlayer.create(applicationContext, R.raw.vctory)
        mediaLose = MediaPlayer.create(applicationContext, R.raw.lose)
        mediaDraw = MediaPlayer.create(applicationContext, R.raw.haha)
        mediaBye = MediaPlayer.create(applicationContext, R.raw.byebye)

        //啟動監聽事件
        b001!!.setOnClickListener(b001on)
        b002!!.setOnClickListener(b001on)
        b003!!.setOnClickListener(b001on)
        b004!!.setOnClickListener(b001on)
        b005!!.setOnClickListener(b001on)
        b006!!.setOnClickListener(b001on)
    }

    private fun u_setalpha() {
        //imageButton背景為銀色且為全透明(0)
//        b001.setBackgroundColor(ContextCompat.getColor(this,R.color.Silver));
        b001!!.setBackgroundResource(R.drawable.circle_shape)
        b001!!.background.alpha = 0
        //        b002.setBackgroundColor(ContextCompat.getColor(this,R.color.Silver));
        b002!!.setBackgroundResource(R.drawable.ring)
        b002!!.background.alpha = 0
        //        b003.setBackgroundColor(ContextCompat.getColor(this,R.color.Silver));
        b003!!.setBackgroundResource(R.drawable.shape_ring)
        b003!!.background.alpha = 0
    }

    private val b001on: View.OnClickListener = object : View.OnClickListener {
        private val toast: Toast? = null
        override fun onClick(v: View) {
            val iComPlay =
                (Math.random() * 3 + 1).toInt() //要幾個值就*多少，沒加1的話就是0=剪刀 1=石頭 2=布
            when (v.id) {
                R.id.m1003_b001 -> {
                    user_select = getString(R.string.m1003_t004) + getString(R.string.m1003_b001)
                    u_setalpha()
                    b001!!.background.alpha = 130 //透明度設定0~255,XML:0~1
                    when (iComPlay) {
                        1 -> {
                            c001!!.setImageResource(R.drawable.scissors)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f003)
                            t005!!.setTextColor(resources.getColor(R.color.Yellow, null))
                            music(2) //自定義方法
                        }
                        2 -> {
                            c001!!.setImageResource(R.drawable.stone)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f002)
                            t005!!.setTextColor(resources.getColor(R.color.Red, null))
                            music(3) //自定義方法
                        }
                        3 -> {
                            c001!!.setImageResource(R.drawable.net)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f001)
                            t005!!.setTextColor(resources.getColor(R.color.Lime, null))
                            music(1) //自定義方法
                        }
                    }
                }
                R.id.m1003_b002 -> {
                    user_select = getString(R.string.m1003_t004) + getString(R.string.m1003_b002)
                    u_setalpha()
                    b002!!.background.alpha = 130 //透明度設定0~255
                    when (iComPlay) {
                        1 -> {
                            c001!!.setImageResource(R.drawable.scissors)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f001)
                            t005!!.setTextColor(resources.getColor(R.color.Lime, null))
                            music(1) //自定義方法
                        }
                        2 -> {
                            c001!!.setImageResource(R.drawable.stone)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f003)
                            t005!!.setTextColor(resources.getColor(R.color.Yellow, null))
                            music(2) //自定義方法
                        }
                        3 -> {
                            c001!!.setImageResource(R.drawable.net)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f002)
                            t005!!.setTextColor(resources.getColor(R.color.Red, null))
                            music(3) //自定義方法
                        }
                    }
                }
                R.id.m1003_b003 -> {
                    user_select = getString(R.string.m1003_t004) + getString(R.string.m1003_b003)
                    u_setalpha()
                    b003!!.background.alpha = 130 //透明度設定0~255
                    when (iComPlay) {
                        1 -> {
                            c001!!.setImageResource(R.drawable.scissors)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f002)
                            t005!!.setTextColor(resources.getColor(R.color.Red, null))
                            music(3) //自定義方法
                        }
                        2 -> {
                            c001!!.setImageResource(R.drawable.stone)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f001)
                            t005!!.setTextColor(resources.getColor(R.color.Lime, null))
                            music(1) //自定義方法
                        }
                        3 -> {
                            c001!!.setImageResource(R.drawable.net)
                            answer = getString(R.string.m1003_t005) + getString(R.string.m1003_f003)
                            t005!!.setTextColor(resources.getColor(R.color.Yellow, null))
                            music(2) //自定義方法
                        }
                    }
                }
                R.id.m1003_b004 -> {
                    intent01.setClass(this@M1003, GameResult::class.java)
                    val bundle = Bundle()
                    bundle.putInt("Key_win", bwin)
                    bundle.putInt("Key_draw", bdraw)
                    bundle.putInt("Key_lose", blose)
                    intent01.putExtras(bundle)
                    startActivity(intent01)
                }
                R.id.m1003_b005 -> {
                    val it = Intent()
                    val bundle100 = Bundle()
                    bundle100.putInt("Ka", bwin)
                    bundle100.putInt("Kb", bdraw)
                    bundle100.putInt("Kc", blose)
                    it.putExtras(bundle100)
                    setResult(RESULT_OK, it)
                    finish()
                }
                R.id.m1003_b006 -> {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }

            //電腦出拳增加動畫
            c001!!.clearAnimation()
            val anim = AnimationUtils.loadAnimation(
                applicationContext, R.anim.anim_trans_in
            ) //down
            anim.interpolator = BounceInterpolator() //jump
            c001!!.animation = anim
            t004!!.text = user_select
            t005!!.text = answer
        }
    }

    private fun music(i: Int) {
        if (startMusic!!.isPlaying && startMusic != null) startMusic!!.stop()
        if (mediaDraw!!.isPlaying && mediaDraw != null) mediaDraw!!.pause()
        if (mediaLose!!.isPlaying && mediaLose != null) mediaLose!!.pause()
        if (mediaWin!!.isPlaying && mediaWin != null) mediaWin!!.pause()
        if (mediaBye!!.isPlaying && mediaBye != null) mediaWin!!.pause()
        when (i) {
            1 -> {
                mediaWin!!.start()
                bwin++
            }
            2 -> {
                mediaDraw!!.start()
                bdraw++
            }
            3 -> {
                mediaLose!!.start()
                blose++
            }
            4 -> mediaBye!!.start()
        }
    }

    override fun onClick(v: View) {
//
//        //電腦出拳增加動畫
//        c001.clearAnimation();
//        Animation anim=AnimationUtils.loadAnimation(this,R.anim.anim_trans_in);//down
//        anim.setInterpolator(new BounceInterpolator());//jump
//        c001.setAnimation(anim);
    }

    override fun makeView(): View {
        val v = ImageView(this)
        //        v.setBackgroundColor(0xFF000000);
        v.layoutParams = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
        return v
    }

    override fun onBackPressed() { //按返回鍵
//        super.onBackPressed();
//        music(4);
    }

    override fun finish() {
        super.finish()
        // ---關機動畫---
        overridePendingTransition(R.anim.anim_scale_rotate_out, R.anim.anim_alpha_out)
        music(4)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { //menu的method放主程式最後面
        menuInflater.inflate(R.menu.m1004, menu)
        //        return super.onCreateOptionsMenu(menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item01 -> b004!!.callOnClick() //=b004.performClick();
            R.id.item02 -> b005!!.performClick()
            R.id.item03 -> b006!!.performClick()
        }
        return super.onOptionsItemSelected(item)
    } //------------------------------------------------------下面放inner class(副程式
}