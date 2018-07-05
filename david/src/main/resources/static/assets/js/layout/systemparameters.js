$(function () {
	var header = $("meta[name='csrf_header']").attr("content");
	var token = $("meta[name='csrf']").attr("content");
	
	$('.edit').click(function () {
		$.ajax({
    		type: "POST",
    		contentType: "application/json",
    		url: "/david/findbyidsystemparameters",
    		beforeSend: function(xhr){xhr.setRequestHeader(header, token)},
    		data : JSON.stringify({idSystemParameters : '1'}),
    		dataType: 'json',
    		success: function(response) {
    		},
    		error: function(response) {
    	    }
    	});
		
		$("#systemparametersModal").modal();
	});
});