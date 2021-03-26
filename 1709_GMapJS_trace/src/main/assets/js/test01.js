$(function()
	{
		$('#showBack').bind('click',showBack);//#showBack按鈕被按得時候 執行showBack
		$('#showNext').bind('click',showNext);//#showNext按鈕被按得時候 執行showNext

	});

	var index=0;
	var imgSrc,imgText,jshref;//圖片路徑,圖片下面的文字,圖片連結

	var arrImgSrc=new Array("c1.jpg","c2.jpg","c3.jpg","c4.jpg","c5.jpg","c6.jpg","c7.jpg","c8.jpg","c9.jpg","c10.jpg");
	var arrText=new Array("阿花","阿貓","阿汪","阿花","阿貓","阿汪","阿花","阿貓","阿汪","qq");
	var ahref01=new Array("https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW",
						  "https://translate.google.com.tw/?hl=zh-TW");



	function showBack()
	{
		index--;
		if(index<0)
		{
			index=9;
		}
		imgSrc="image/"+arrImgSrc[index];

		imgText=arrText[index];
		jshref=ahref01[index]



		$("#inImg").attr("src",imgSrc);// #inImg 的src屬性 替換成 imgSrc
		$("#inName").text(arrText[index]);//#inName的文字  替換成  陣列文字
		$("#href01").attr("href",jshref);//#href01 的href屬性 替換成 jshref陣列裡的連結
		content.log(jshref);
	}

	function showNext()
	{
		index ++;
		if(index>9)
		{
			index=0;
		}
		imgSrc="image/"+arrImgSrc[index];
		imgText=arrText[index];
		$("#inImg").attr("src",imgSrc);// #inImg 的src屬性 替換成 imgSrc
		$("#inName").text(arrText[index]);//#inName的文字  替換成  陣列文字
		$("#href01").attr("href",jshref);//#href01 的href屬性 替換成 jshref陣列裡的連結
		content.log(jshref);
	}