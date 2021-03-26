package tw.tcnr23.m0501

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity

class M0501 : AppCompatActivity() {
    private var e001: EditText? = null
    private var b001: Button? = null
    private var s001: Spinner? = null
    private var f000: TextView? = null
    private var sSex: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m0501)
        setupViewcomponent()
    }

    private fun setupViewcomponent() {
        e001 = findViewById<View>(R.id.m0501_e001) as EditText
        b001 = findViewById<View>(R.id.m0501_b001) as Button
        s001 = findViewById<View>(R.id.m0501_s001) as Spinner
        f000 = findViewById<View>(R.id.m0500_f000) as TextView

        //設定spinner item選項-------------
        val adapSexList = ArrayAdapter.createFromResource(
            this,
            R.array.m0501_a001,
            android.R.layout.simple_spinner_item
        ) //contest為提示字 打this會出現    此行為設定spinner樣式
        s001!!.adapter = adapSexList
        //準備Listener a001Lis 需再設定 Listener  上面設定adapSexList 所以這邊也要設定一樣的
        s001!!.onItemSelectedListener = s001ON
        //準備Listener btnb001 需再設定Listener
        b001!!.setOnClickListener(b001ON)
    }

    //=====================================================================
    private val s001ON: OnItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            sSex = parent.selectedItem.toString()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    private val b001ON: View.OnClickListener = object : View.OnClickListener {
        private val iAge = 0
        override fun onClick(v: View) {
            var strSug = getString(R.string.m0501_f000)
            // 檢查 年齡是否有輸入
            if (e001!!.text.toString().trim { it <= ' ' }.length != 0) {
                val iAge = e001!!.text.toString().toInt()
                strSug += if (sSex == getString(R.string.chk01)) if (iAge < 28) getString(R.string.m0501_f001) else if (iAge > 33) getString(
                    R.string.m0501_f003
                ) else {
                    getString(R.string.m0501_f002)
                } else if (iAge < 25) getString(R.string.m0501_f001) else if (iAge > 30) getString(R.string.m0501_f003) else {
                    getString(R.string.m0501_f002)
                }
                f000!!.text = strSug
                //-------------------------------------------------------
            } else {
                strSug = getString(R.string.nospace) //else { //年齡輸入空白
            }
            f000!!.text = strSug //請勿輸入空白
        }
    }
}