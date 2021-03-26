package tw.tcnr23.m1706

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class M1706 : AppCompatActivity(), LocationListener, View.OnClickListener {
    private var webView: WebView? = null
    private var mSpnLocation: Spinner? = null
    private var Lat: String? = null
    private var Lon: String? = null
    private var jcontent: String? = null
    private var txtOutput: TextView? = null
    private val tmsg: TextView? = null
    var Navon = "off"
    private val permissionsList: MutableList<String> = ArrayList()
    private var provider: String? = null // 提供資料
    private var locationMgr: LocationManager? = null
    private var bNav: Button? = null
    var Navstart = "24.172127,120.610313" // 起始點
    var Navend = "24.144671,120.683981" // 結束點
    private var iSelect = 0
    private var aton: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        checkRequiredPermission(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.m1706)
        setUpViewComponent()
    }

    private fun setUpViewComponent() {
        webView = findViewById<View>(R.id.webview) as WebView
        mSpnLocation = findViewById<View>(R.id.spnLocation) as Spinner
        mSpnLocation!!.background.alpha = 150 //0-255
        // ----Location-----------
        txtOutput = findViewById<View>(R.id.txtOutput) as TextView
        webView!!.settings.javaScriptEnabled = true
        webView!!.addJavascriptInterface(this@M1706, "AndroidFunction") //
        //webView.loadUrl(MAP_URL);
        val adapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_style
        )
        for (i in locations.indices) adapter.add(locations[i][0])
        adapter.setDropDownViewResource(R.layout.spinner_style)
        mSpnLocation!!.adapter = adapter
        mSpnLocation!!.onItemSelectedListener = mSpnLocationOnItemSelLis
        bNav = findViewById<View>(R.id.Navigation) as Button
        bNav!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (Navon === "off") {
            bNav!!.setTextColor(getColor(R.color.Blue))
            Navon = "on"
            bNav!!.text = "關閉路徑規劃"
            setMapLocation()
        } else {
            bNav!!.setTextColor(getColor(R.color.Red))
            Navon = "off"
            bNav!!.text = "開啟路徑規劃"
            setMapLocation()
        }
    }

    override fun onLocationChanged(location: Location) {
//        // 定位改變時
//        updateWithNewLocation(location);
////        // --- 呼叫 Map JS
//        Navstart = locations[0][1];
//        //---------增加判斷是否規畫路徑------------------
//        if (Navon == "on" && iSelect != 0) {
//            final String deleteOverlays = "javascript: RoutePlanning()";
//            webView.loadUrl(deleteOverlays);
//        }else{
//            webView.getSettings().setJavaScriptEnabled(true);
//            webView.addJavascriptInterface(M1706.this, "AndroidFunction");
//            webView.loadUrl(MAP_URL);
//        }
//        // ---
//////        Log.d(TAG, "onLocationChanged");
        //定位改變時
        updateWithNewLocation(location)
        //將畫面移至定位點的位置
        aton = location.latitude.toString() + "," + location.longitude
        val centerURL = "javascript:centerAt(" +
                location.latitude + "," + location.longitude + ")"
        webView!!.loadUrl(centerURL)
        val deleteOverlays = "javascript:deleteOverlays()"
        webView!!.loadUrl(deleteOverlays)
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        when (status) {
            LocationProvider.OUT_OF_SERVICE -> {
            }
            LocationProvider.TEMPORARILY_UNAVAILABLE -> {
            }
            LocationProvider.AVAILABLE -> {
            }
        }
    }

    override fun onProviderEnabled(provider: String) {
//        tmsg.setText("目前Zoom:" + map.getCameraPosition().zoom);
    }

    override fun onProviderDisabled(provider: String) {
        updateWithNewLocation(null)
    }

    private fun updateWithNewLocation(location: Location?) {
        var where = ""
        if (location != null) {
            val lng = location.longitude // 經度
            val lat = location.latitude // 緯度
            Lon = location.longitude.toString() + "" // 經度
            Lat = location.latitude.toString() + "" // 緯度
            val speed = location.speed // 速度
            val time = location.time // 時間
            val timeString = getTimeString(time)
            where = """
                緯度: $lat
                經度: $lng
                速度: $speed
                時間: $timeString
                Provider: $provider
                """.trimIndent()
            // 標記"我的位置"
            locations[0][1] = "$lat,$lng" // 用GPS找到的位置更換 陣列的目前位置
            // --- 呼叫 Map JS

            // webView.loadUrl(MAP_URL);
        } else {
            where = "*位置訊號消失*"
        }
        // 位置改變顯示
        txtOutput!!.text = where
    }

    private fun getTimeString(timeInMilliseconds: Long): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(timeInMilliseconds)
    }

    //    private JSONArray ArryToJson() {
    private fun ArryToJson(): String {
        val jArry = JSONArray()
        for (i in 1 until locations.size) {
            val jObj = JSONObject() // 一定要放在這裡
            val arr = locations[i][1].split(",".toRegex()).toTypedArray()
            try {
                jObj.put("title", locations[i][0])
                jObj.put("jlat", arr[0])
                jObj.put("jlon", arr[1])
                jArry.put(jObj)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return jArry.toString()
        //            return jArry;
    }

    private val mSpnLocationOnItemSelLis: OnItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
            setMapLocation()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private fun setMapLocation() {
        iSelect = mSpnLocation!!.selectedItemPosition
        val sLocation = locations[iSelect][1].split(",".toRegex()).toTypedArray()
        Lat = sLocation[0] // 南北緯
        Lon = sLocation[1] // 東西經
        jcontent = locations[iSelect][0] //地名
        //===============增加判斷是否規劃路徑=============
        if (Navon === "on" && iSelect != 0) {
            Navstart = locations[0][1]
            Navend = locations[iSelect][1]
            val deleteOverlays = "javascript: RoutePlanning()"
            webView!!.loadUrl(deleteOverlays)
        } else {
            webView!!.settings.javaScriptEnabled = true
            webView!!.addJavascriptInterface(this@M1706, "AndroidFunction") //
            webView!!.loadUrl(MAP_URL)
        }
    }

    /* 檢查GPS 是否開啟 */
    private fun initLocationProvider(): Boolean {
        locationMgr = getSystemService(LOCATION_SERVICE) as LocationManager
        if (locationMgr!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER
            return true
        }
        return false
    }

    private fun nowaddress() {
        //檢查是否有權限-------------------------------------------
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val location = locationMgr!!.getLastKnownLocation(provider)
        updateWithNewLocation(location)
        // 監聽 GPS Listener
        locationMgr!!.addGpsStatusListener(gpsListener)
        // Location Listener
        val minTime: Long = 5000 // ms
        val minDist = 5.0f // meter
        locationMgr!!.requestLocationUpdates(
            provider, minTime, minDist,
            this
        ) //開始座標移動
    }

    // -------------------------------
    var gpsListener = GpsStatus.Listener { event ->

        /* 監聽GPS 狀態 */
        when (event) {
            GpsStatus.GPS_EVENT_STARTED -> {
            }
            GpsStatus.GPS_EVENT_STOPPED -> {
            }
            GpsStatus.GPS_EVENT_FIRST_FIX -> {
            }
            GpsStatus.GPS_EVENT_SATELLITE_STATUS -> {
            }
        }
    }

    /* 開啟時先檢查是否有啟動GPS精緻定位 */
    override fun onStart() {
        super.onStart()
        if (initLocationProvider()) {
            nowaddress()
        } else {
            txtOutput!!.text = "GPS未開啟,請先開啟定位！"
        }
    }

    override fun onStop() {
        super.onStop()
        locationMgr!!.removeUpdates(this)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
//        super.onBackPressed();
    }

    private fun checkRequiredPermission(activity: Activity) { //
//        String permission_check= String[i][0] permission;
        for (i in permissionsArray.indices) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permissionsArray[i][0]
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsList.add(permissionsArray[i][0])
            }
        }
        if (permissionsList.size != 0) {
            ActivityCompat.requestPermissions(
                activity,
                permissionsList.toTypedArray(),
                REQUEST_CODE_ASK_PERMISSIONS
            )
        }
    }

    /*** request需要的權限 */
    private fun requestNeededPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_ASK_PERMISSIONS
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { //選擇哪個layout的檔名
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> finish()
            R.id.item1 -> {
            }
            R.id.item2 -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //========================================================
    @JavascriptInterface
    fun GetLat(): String? {
        return Lat
    }

    @JavascriptInterface
    fun GetLon(): String? {
        return Lon
    }

    @JavascriptInterface
    fun Getjcontent(): String? {
        return jcontent
    }

    @JavascriptInterface
    fun GetJsonArry(): String {
        return ArryToJson()
    }

    //-----傳送導航資訊-------------------------------
    @JavascriptInterface
    fun Navon(): String {
        return Navon
    }

    @JavascriptInterface
    fun Getstart(): String? {
        return aton
    }

    @JavascriptInterface
    fun Getend(): String {
        return Navend
    }

    companion object {
        //    private static final String MAP_URL = "file:///android_asset/letitgo.html";// 自建的html檔名
        private const val MAP_URL = "file:///android_asset/GoogleMap.html" // 自建的html檔名
        private val locations = arrayOf(
            arrayOf("現在位置", "0,0"),
            arrayOf("中區職訓", "24.172127,120.610313"),
            arrayOf("東海大學路思義教堂", "24.179051,120.600610"),
            arrayOf("台中公園湖心亭", "24.144671,120.683981"),
            arrayOf("秋紅谷", "24.1674900,120.6398902"),
            arrayOf("台中火車站", "24.136829,120.685011"),
            arrayOf("國立科學博物館", "24.1579361,120.6659828")
        )

        //所需要申請的權限數組
        private val permissionsArray = arrayOf(
            arrayOf(Manifest.permission.INTERNET, "網路"), arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, "定位"
            ), arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, "定位")
        )
        private const val REQUEST_CODE_ASK_PERMISSIONS = 1
    }
}