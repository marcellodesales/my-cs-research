window.onerror = errorHandler;

   

function errorHandler() {

     //alert("Error Handled");

     return true;

}



   

if (document.images) {



	//alert("Begin Image Load");

	

products_on = new Image(); products_on.src = "/images/header_images/products_on.gif";

products_off = new Image(); products_off.src = "/images/header_images/products_off.gif";

products_click = new Image(); products_click.src = "/images/header_images/products_click.gif";



solutions_on = new Image(); solutions_on.src = "/images/header_images/solutions_on.gif";

solutions_off = new Image(); solutions_off.src = "/images/header_images/solutions_off.gif";

solutions_click = new Image(); solutions_click.src = "/images/header_images/solutions_click.gif";



support_on = new Image(); support_on.src = "/images/header_images/support_on.gif";

support_off = new Image(); support_off.src = "/images/header_images/support_off.gif";

support_click = new Image(); support_click.src = "/images/header_images/support_click.gif";



services_on = new Image(); services_on.src = "/images/header_images/services_on.gif";

services_off = new Image(); services_off.src = "/images/header_images/services_off.gif";

services_click = new Image(); services_click.src = "/images/header_images/services_click.gif";



partners_on = new Image(); partners_on.src = "/images/header_images/partners_on.gif";

partners_off = new Image(); partners_off.src = "/images/header_images/partners_off.gif";

partners_click = new Image(); partners_click.src = "/images/header_images/partners_click.gif";



intl_on = new Image(); intl_on.src = "/images/header/intl_on.gif";

intl_off = new Image(); intl_off.src = "/images/header/intl_off.gif";


jobs_on = new Image(); jobs_on.src = "/images/header/jobs_on.gif";
jobs_off = new Image(); jobs_off.src = "/images/header/jobs_off.gif";

about_on = new Image(); about_on.src = "/images/header/about_on.gif";

about_off = new Image(); about_off.src = "/images/header/about_off.gif";



map_on = new Image(); map_on.src = "/images/header_images/map_on.gif";

map_off = new Image(); map_off.src = "/images/header_images/map_off.gif";



cmap_on = new Image(); cmap_on.src = "/images/header_images/cmap_on.gif";

cmap_off = new Image(); cmap_off.src = "/images/header_images/cmap_off.gif";



contact_on = new Image(); contact_on.src = "/images/header/contact_on.gif";

contact_off = new Image(); contact_off.src = "/images/header/contact_off.gif";



downloads_on = new Image(); downloads_on.src = "/images/header/download_on.gif";

downloads_off = new Image(); downloads_off.src = "/images/header/download_off.gif";



cdownloads_on = new Image(); cdownloads_on.src = "/images/header_images/cdownloads_on.gif";

cdownloads_off = new Image(); cdownloads_off.src = "/images/header_images/cdownloads_off.gif";



search_on = new Image(); search_on.src = "/images/header_images/search_on.gif";

search_off = new Image(); search_off.src = "/images/header_images/search_off.gif";





	//alert("End Image Load");

}

function rollON(which) {
    //alert('begin rollover: ' + which);
	if (document.images) {
           eval('document.images["' + which + '"].src=' + which + '_on.src;');
    }
}

        
function rollOFF(which) {
    //alert('begin');
    if (document.images) {
          eval('document.images["' + which + '"].src=' + which + '_off.src;');
    }         
}      


function highlight() {
	//alert('begin highlight:' + section);
	//section=section + ""
		
	if (document.images[section] != null) {
	    if (document.images) {
	          eval('document.images["' + section + '"].src=' + section + '_click.src;');
	    }         
	}
}

var pointer;
function Popper(newPage) {       

	pointer = window.open(newPage,'instructionalwindow','toolbar=no, resizable=yes,scrollbars=yes,height=480,width=640, screenx=200,screeny=100');
    pointer.location = newPage;     
	pointer.focus();
}
