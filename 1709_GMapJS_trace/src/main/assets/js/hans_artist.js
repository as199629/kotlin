		$(function(){
			// 上一筆，觸發下面的function showprev()，#showprev和body的back-id相對應
			$('#showprev').bind('click', showprev);
			// 下一筆，觸發下面的function shownext()
			$('#shownext').bind('click', shownext); 
		});
	var index=0; // 目前藝人的索引
	var curImgSrc,curArtist; // 目前取得的藝人圖檔名、藝人名稱
	var curlink;// 目前取得的連結
	// 藝人圖片
	var aryArtistSrc = new Array("Artist01.jpg", "Artist02.jpg", "Artist03.jpg","Artist04.jpg", "Artist05.jpg", "Artist06.jpg","Artist07.jpg", "Artist08.jpg", "Artist09.jpg","Artist10.jpg");
// 藝人名稱
var aryArtistName = new Array("果果","以軒","培煌","亮瑜","浩文","冠廷","雅婷","漢聲","會俊","悠悠");
	//連結的網站
	var linkweb=new Array("http://hackertyper.net/",
		"https://10minutemail.com/10MinuteMail/index.html?dswid=5802",
		"http://www.fakenamegenerator.com/",
		"http://downforeveryoneorjustme.com/",
		"http://www.timeanddate.com/date/duration.html",
		"https://web-capture.net/",
		"https://www.google.com/ncr",
		"https://www.virustotal.com/",
		"http://hp.ipviking.com/",
		"http://www.netdisaster.com/index.html")

	function showprev() { // 上一筆
		index --;
		if (index<0)
	{index=9;} // 可循環
	curImgSrc="img/" + aryArtistSrc[index]; // 目前取得的圖檔名稱
	curArtist=aryArtistName[index]; // 目前取得的藝人名稱
	curlink=linkweb[index];// 目前取得的網站
	// console.log(curImgSrc);常用來看值是否正確
	$("#pimg").attr("src",curImgSrc); // 動態顯示圖片
	$("#pname").text(curArtist); // 動態顯示藝人名稱
	$("#plink").attr("href",curlink); // 動態更改超連結
}

	function shownext() { // 下一筆
		index ++;
		if (index>9)
	{index=0;} // 可循環
	curImgSrc="img/" + aryArtistSrc[index]; // 目前取得的圖檔名稱
	curArtist=aryArtistName[index]; // 目前取得的藝人名稱
	curlink=linkweb[index];// 目前取得的網站
	$("#pimg").attr("src",curImgSrc); // 動態顯示圖片
	$("#pname").text(curArtist); // 動態顯示藝人名稱
	$("#plink").attr("href",curlink); // 動態更改超連結
}