//demo-page 為頁面ID
//#side_photo 為圖片ID


$( document ).on( "pagecreate", "#demo-page", function() {
    $( document ).on( "swipeleft swiperight", "#demo-page", function( e ) {
        if ( $( ".ui-page-active" ).jqmData( "panel" ) !== "open" ) {
            if ( e.type === "swipeleft" ) {
                side_showprev();
            } else if ( e.type === "swiperight" ) {
                side_shownext();
            }
        }
    });
});

var side_pointer = 0;
var side_Pimg; //紀錄圖片路徑
	var arrImgSrc=new Array("c1.jpg","c2.jpg","c3.jpg","c4.jpg","c5.jpg","c6.jpg","c7.jpg","c8.jpg","c9.jpg","c10.jpg");

function side_showprev(){
	side_pointer--;
	if(side_pointer < 0){
		side_pointer =9;
	}
	side_Pimg = "images/" + side_PimgArray[side_pointer];
	$("#side_photo").attr("src", side_Pimg);
}

function side_shownext(){
	side_pointer++;
	if(side_pointer > 9){
		side_pointer = 0;
	}
	side_Pimg = "images/" + side_PimgArray[side_pointer];
	$("#side_photo").attr("src", side_Pimg);
}