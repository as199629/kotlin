		$( document ).on( "pagecreate", "#home", function() {

			$( document ).on( "swipeleft swiperight", "#home", function( e ) {
				// We check if there is no open panel on the page because otherwise
				// a swipe to close the left panel would also open the right panel (and v.v.).
				// We do this by checking the data that the framework stores on the page element (panel: open).
				if ( $( ".ui-page-active" ).jqmData( "panel" ) !== "open" ) 
				{
					if ( e.type === "swipeleft" ) {
						backfunction02();
						// $( "#right-panel" ).panel( "open" );
					} else if ( e.type === "swiperight" ) {
						nextfunction02();
						// $( "#left-panel" ).panel( "open" );
					}
				}
			});
		});


		var imageIndex02=0;
		var imgSrc02;

		var arrImgSrc02=new Array("d01.jpg","d02.jpg","d03.jpg","d04.jpg","d05.jpg","d06.jpg","d07.jpg","d08.jpg","d09.jpg");


		function backfunction02()
		{
			imageIndex02 --;
			if(imageIndex02<0)
				imageIndex02=8;
			imgSrc02="image/img 300X300/"+arrImgSrc02[imageIndex02];

			$("#img02").attr("src",imgSrc02);

		}
		function nextfunction02()
		{
			imageIndex02 ++;
			if(imageIndex02>8)
				imageIndex02=0;
			imgSrc02="image/img 300X300/"+arrImgSrc02[imageIndex02];

			$("#img02").attr("src",imgSrc02);
		}