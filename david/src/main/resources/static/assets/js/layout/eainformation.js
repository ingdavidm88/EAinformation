$(function () {
	var header = $("meta[name='csrf_header']").attr("content");
	var token = $("meta[name='csrf']").attr("content");
	
	var x = 0;
	var y = 1;
	var timer;
	function progressBar(id){
		if(x != y){
			$.ajax({
        		type: "POST",
        		contentType: "application/json",
        		url: "/david/progress",
        		beforeSend: function(xhr){xhr.setRequestHeader(header, token)},
        		data : JSON.stringify(),
        		dataType: 'json',
        		success: function(response) {
        			$('#progress-bar'+id).css('width',response.percentage+'%');
            		$('#progress-value'+id).text(response.percentage+'%');
            		$('#status_'+id).text(response.status);
            		$('#processing_'+id).text(response.count + " - "+ response.length);
            		$('#success_'+id).text(response.countSuccess);
            		$('#fail_'+id).text(response.countFail);
            		$('#error_'+id).text(response.countError);
            		x = response.count;
            		y = response.length;
        		},
        		error: function(response) {
        	    }
        	});
		}else{
			$('#continue_'+id).attr("disabled", false);
			$('#eainformation'+id).attr("disabled", false);
			clearInterval(timer);
		}
    }
	
	function step(id, url, time){
		$.ajax({
    		type: "POST",
    		contentType: "application/json",
    		url: "/david/"+url,
    		beforeSend: function(xhr){
    			$('#eainformation'+id).attr("disabled", true);
    	    	$('#progress-bar'+id).css('width','0%');
    			$('#progress-value'+id).text('0%');
    			$('#status_'+id).text("Starting");
    			$('#processing_'+id).text("0 - 0");
    			$('#success_'+id).text("0");
    			$('#fail_'+id).text("0");
    			$('#error_'+id).text("0");
    			
    			xhr.setRequestHeader(header, token);
    		},
    		data : JSON.stringify(),
    		dataType: 'json',
    		success: function(response) {
    			
    		},
    		error: function(response) {
    			if(response.responseJSON){
    				$("#errorAjax").find("#error").text(response.responseJSON.message);
    				$("#errorAjax").modal();
    			}else{
    				$("#expiredSession").modal();
    			}
    	    }
    	});
    	
    	x = 0;
    	y = 1;
    	timer = setInterval(progressBar, time, id);
	}
	
    $('#eainformation1').click(function () {
    	step(1, "step1", 2000);
    });  
	
    $('#eainformation2').click(function () {
    	step(2, "step2", 2000);
    });  
    
    $('#eainformation3').click(function () {
    	step(3, "step3", 2000);
    }); 
    
    $('#eainformation4').click(function () {
    	step(4, "step4", 4000);
    }); 
    
    $('#eainformation5').click(function () {
    	step(5, "step5", 4000);
    }); 
    
    $('.nav-tabs > li a[title]').tooltip();
	    
	$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
		var $target = $(e.target);
	
		if ($target.parent().hasClass('disabled')) {
	        return false;
	    }
	});

    $(".next-step").click(function (e) {
        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        $active.addClass('disabled');
        nextTab($active);
    });

	function nextTab(elem) {
	    $(elem).next().find('a[data-toggle="tab"]').click();
	}
});