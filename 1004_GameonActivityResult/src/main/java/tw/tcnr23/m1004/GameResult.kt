package tw.tcnr23.m1004

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameResult : AppCompatActivity(), View.OnClickListener {
    private var edtCountSet: TextView? = null
    private var edtCountPlayerWin: TextView? = null
    private var edtCountComWin: TextView? = null
    private var edtCountDraw: TextView? = null
    private var btnBackToGame: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_result)
        setupViewComponent()
        showResult()
    }

    private fun setupViewComponent() {
        edtCountSet = findViewById<View>(R.id.edtCountSet) as TextView
        edtCountPlayerWin = findViewById<View>(R.id.edtCountPlayerWin) as TextView
        edtCountComWin = findViewById<View>(R.id.edtCountComWin) as TextView
        edtCountDraw = findViewById<View>(R.id.edtCountDraw) as TextView
        btnBackToGame = findViewById<View>(R.id.btnBackToGame) as Button
        btnBackToGame!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnBackToGame -> finish()
        }
    }

    private fun showResult() {
        val bundle = this.intent.extras
        val a = bundle!!.getInt("Key_win")
        val b = bundle.getInt("Key_lose")
        val c = bundle.getInt("Key_draw")
        edtCountSet!!.text = Integer.toString(a + b + c)
        edtCountPlayerWin!!.text = Integer.toString(a)
        edtCountComWin!!.text = Integer.toString(b)
        edtCountDraw!!.text = Integer.toString(c)
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