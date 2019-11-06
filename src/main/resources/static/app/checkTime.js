$.getJSON('/users/getThoiHan', function(data) {	
	xuLyHetHan(data);
});

function xuLyHetHan(thoihan){
	var hethan = new Date(thoihan);
	var today = new Date();
	var nam = hethan.getFullYear() - today.getFullYear();
	var thang = hethan.getMonth() - today.getMonth();
	var ngay = hethan.getDate() - today.getDate();
	
	if(nam <= 0 && thang <= 0){
	    if(ngay <= 5){           
	    	setTimeout(function(){
	    		window.location.href = "/logout";
	    	}, hethan-today);
	    	
	        toastr.warning('Cửa hàng sắp hết hạn', 'Chú ý',{ 
		        timeOut: 0, 
		        extendedTimeOut: 0,
		        escapeHtml: true, 
		        closeButton: true,
		        positionClass: "toast-bottom-right", 
		        hideEasing: "linear", 
		        showMethod: "fadeIn",
		        hideMethod: "fadeOut", 
		        showEasing: "swing"
		    });
	    }
	}
}