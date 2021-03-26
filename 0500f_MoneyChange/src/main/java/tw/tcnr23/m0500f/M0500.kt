package tw.tcnr23.m0500f

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class M0500 : AppCompatActivity() {
    //這個類別的開始

    private var e001: EditText? = null
    private var b001: Button? = null
    private var t003: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m0500) //R:呼叫res其中任何一處,並且編譯為java語言
        setupViewcompontent()
    }

    private fun setupViewcompontent() {
//        設定layout設置
        e001 = findViewById<EditText>(R.id.m0500_e001)
       // e001 = findViewById<View>(R.id.m0500_e001) as EditText //輸入公斤，建立一個名為e001的EditText變數
        b001 = findViewById<Button>(R.id.m0500_b001)
        //b001 = findViewById<View>(R.id.m0500_b001) as Button //按鈕轉換，建立一個名為b001的Button變數
        t003 = findViewById<View>(R.id.m0500_t003) as TextView //輸出磅，建立一個名為e001的TextView變數
        b001!!.setOnClickListener(b001ON) //呼叫(.)句點 setOnClickListener當你按下時監聽 ，
    }

    private val b001ON = View.OnClickListener {
        val pondsFormat = DecimalFormat("0.0000") //限制小數點字量
        val outcomp = pondsFormat.format(e001!!.text.toString().toFloat() * 28.6)
        t003!!.text = outcomp
    } //private宣告 b001ON變成新的物件，new從不占記憶體變為記憶體  private
    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) { //Bundle打包
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.m0500);
    //    }
}