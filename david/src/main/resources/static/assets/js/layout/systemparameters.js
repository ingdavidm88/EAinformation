$(function () {
	var header = $("meta[name='csrf_header']").attr("content");
	var token = $("meta[name='csrf']").attr("content");
	
	$('.edit').click(function () {
		$('#messageAjax').find('.success, .danger').text('');
		
		$.ajax({
    		type: "POST",
    		contentType: "application/json",
    		url: "/david/findbyidsystemparameters",
    		beforeSend: function(xhr){xhr.setRequestHeader(header, token)},
    		data : JSON.stringify({idSystemParameters : $(this).attr('title')}),
    		dataType: 'json',
    		success: function(response) {
    			$("#idSystemParameters").val(response.idSystemParameters);
    			$("#name").val(response.name);
    			$("#value").val(response.value);
    			$("#description").val(response.description);
    			$("#userName").val(response.userName);
    			$("#date").val(response.date);
    			
    			$("#systemparametersModal").modal();
    		},
    		error: function(response) {
    			if(response.responseJSON){
    				$('#messageAjax').find('.danger').text(response.responseJSON.message);
    				$("#systemparametersModal").modal();
    			}else{
    				$(location ).attr("href", "/david/signin");
    			}
    	    }
    	});
	});
	
	$('#update').click(function () {
		$('#messageAjax').find('.success, .danger').text('');
		$('#modalLoad').css('display' , 'block');
		
		$.ajax({
			type: "POST",
    		contentType: "application/json",
    		url: "/david/savesystemparameters",
    		beforeSend: function(xhr){xhr.setRequestHeader(header, token)},
    		data : JSON.stringify(
    				{
    					idSystemParameters : $("#idSystemParameters").val(),
    					name : $("#name").val(),
    					value : $("#value").val(),
    					description : $("#description").val(),
    					userName : $("#userName").val(),
    					date : $("#date").val()
    				}),
    		dataType: 'json',
    		success: function(response) {
    			$('#modalLoad').css('display' , 'none');
    			 
    			if(response.errors){
    				$('#messageAjax').find('.danger').text(response.message);
    				for (var i = 0; i < response.errors.length; i++) {
    					$('#'+response.errors[i].field+"Error").text(response.errors[i].code);
    				}
    			}else{
    				$('#messageAjax').find('.success').text(response.message);
    			}    			 
    		},
    		error: function(response) {
    			$('#modalLoad').css('display' , 'none');
    			if(response.responseJSON){
    				$('#messageAjax').find('.danger').text(response.responseJSON.message);
    			}else{
    				$(location ).attr("href", "/david/signin");
    			}
    		}
    	});
	});
});