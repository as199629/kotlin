$(function()
{
	setInterval(showImage,1000);

});
var imageIndex=0;
var imgSrc;

var arrImgSrc=new Array("a01.gif","p01.gif","p03.gif","a05.gif","a04.gif");


function showImage()
{
	imageIndex --;
	if(imageIndex<0)
		imageIndex=4;
	imgSrc="background-image: url(image/"+arrImgSrc[imageIndex]+");";

	$("#img01").attr("style",imgSrc);
}