//路徑要對  img text id要對應

$(function(){
	//當#showBack 被點擊的時候 執行backfunction
	$('#showBack').bind('click',backfunction01);//上一張
	$('#showNext').bind('click',nextfunction01);//下一張
});

var imageIndex01=0;
var imgSrc01 ,imgText01;

var arrImgSrc01=new Array("c1.jpg","c2.jpg","c3.jpg","c4.jpg","c5.jpg","c6.jpg","c7.jpg","c8.jpg","c9.jpg","c10.jpg");
var arrText01=new Array("阿花","阿貓","阿汪","阿花","阿貓","阿汪","阿花","阿貓","阿汪","qq");


function backfunction01()
{
	imageIndex01 --;
	if(imageIndex01<0)
		imageIndex01=8;
	imgSrc01="image/img 300X300/"+arrImgSrc01[imageIndex01];
	imgText01=arrText01[imageIndex01];

	$("#img01").attr("src",imgSrc01);
	$("#text01").text(imgText01);
}
function nextfunction()
{
	imageIndex01 ++;
	if(imageIndex01>8)
		imageIndex01=0;
	imgSrc01="image/img 300X300/"+arrImgSrc01[imageIndex01];
	imgText01=arrText01[imageIndex01];
	$("#img01").attr("src",imgSrc01);
	$("#text01").text(imgText01);
}
