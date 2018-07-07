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
    			$("#idSystemParameters").val(response.idSystemParameters);
    			$("#value").val(response.value);
    			$("#description").val(response.description);
    			$("#userName").val(response.userName);
    			$("#date").val(response.date);
    			
    			$("#systemparametersModal").modal();
    		},
    		error: function(response) {
    	    }
    	});
	});
	
	$('#save').click(function () {
		$("#load").modal();
		$("#systemparametersModal").addClass('importantRule');
		
		$.ajax({
    		type: "POST",
    		contentType: "application/json",
    		url: "/david/savesystemparameters",
    		beforeSend: function(xhr){xhr.setRequestHeader(header, token)},
    		data : JSON.stringify(
    				{
    					idSystemParameters : $("#idSystemParameters").val(),
    					value : $("#value").val(),
    					description : $("#description").val(),
    					userName : $("#userName").val(),
    					date : $("#date").val()
    				}),
    		dataType: 'json',
    		success: function(response) {
    			 $("#save").prop("disabled", false);
    			 $("#systemparametersModal").removeClass('importantRule');
    			 $('#load').modal('hide');
    			 
    			 if(response.errors){
    				 $('#message1').find('.danger').text(response.message);
    				 for (var i = 0; i < response.errors.length; i++) {
    					 $('#'+response.errors[i].field+"Error").text(response.errors[i].code);
    				 }
    			 }else{
    				 $('#message1').find('.success').text(response.message);
    			 }    			 
    		},
    		error: function(response) {
    	    }
    	});
	});
});