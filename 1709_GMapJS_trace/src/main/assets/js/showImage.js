//arrImgSrc 路徑要改
//要變換的圖片 給id="inImg"

$(function()
{
	setInterval(showImage,1000);

});

	var index=0;
	var imgSrc;//圖片路徑,圖片下面的文字,圖片連結

	var arrImgSrc=new Array("c1.jpg","c2.jpg","c3.jpg","c4.jpg","c5.jpg","c6.jpg","c7.jpg","c8.jpg","c9.jpg","c10.jpg");



	function showImage()
	{
		index--;
		if(index<0)
		{
			index=9;
		}
		imgSrc="image/"+arrImgSrc[index];
		$("#inImg").attr("src",imgSrc);// #inImg 的src屬性 替換成 imgSrc

	}