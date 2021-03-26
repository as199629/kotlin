$(function()
{
	setInterval(showImage,1000);

});
var imageIndex=0;
var imgSrc;

var arrImgSrc=new Array("c1.jpg","c2.jpg","c3.jpg","c4.jpg","c5.jpg","c6.jpg","c7.jpg","c8.jpg","c9.jpg");


function showImage()
{
	imageIndex --;
	if(imageIndex<0)
		imageIndex=8;
	imgSrc="image/img 300X300/"+arrImgSrc[imageIndex];

	$("#img01").attr("src",imgSrc);
}