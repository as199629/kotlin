package tw.tcnr23.m1004

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main : AppCompatActivity(), View.OnClickListener {
    private var txtResult: TextView? = null
    private var btnLaunchAct: Button? = null
//    private val intent = Intent()
    private val LAUNCH_GAME = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setupViewComponent()
    }

    private fun setupViewComponent() {
        btnLaunchAct = findViewById<View>(R.id.btnLaunchAct) as Button
        txtResult = findViewById<View>(R.id.txtResult) as TextView
        btnLaunchAct!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLaunchAct -> {
                intent.setClass(this@Main, M1003::class.java)
                //                startActivity(intent);
                startActivityForResult(intent, LAUNCH_GAME) //掛號，呼叫程式有回傳值
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != LAUNCH_GAME) {
            return
        }
        when (resultCode) {
            RESULT_OK -> {
                val bundle = data!!.extras
                val iCountPlayerWin = bundle!!.getInt("Ka")
                val iCountComWin = bundle.getInt("Kb")
                val iCountDraw = bundle.getInt("Kc")
                val s =
                    """${getString(R.string.m1004_result)}${iCountPlayerWin + iCountComWin + iCountDraw}${
                        getString(R.string.m1004_table)
                    }
 ${getString(R.string.m1004_PlayerWin)}$iCountPlayerWin${getString(R.string.m1004_table)}
 ${getString(R.string.m1004_comWin)}$iCountComWin${getString(R.string.m1004_table)}
 ${getString(R.string.m1004_draw)}$iCountDraw${getString(R.string.m1004_table)}"""
                txtResult!!.text = s
            }
            RESULT_CANCELED -> txtResult!!.text = getString(R.string.cancel)
        }
    }

    override fun onBackPressed() {
//        super.onBackPressed();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { //menu的method放主程式最後面
        menuInflater.inflate(R.menu.main, menu)
        //        return super.onCreateOptionsMenu(menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item03 -> finish() //返回或結束，每一個menu都要有
        }
        return super.onOptionsItemSelected(item)
    }
}