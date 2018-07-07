$(function() {
	$('.required-icon').tooltip({
		placement: 'left',
        title: 'Required field'
	});
	
	$(document).tooltip({
		selector: "[title]",
		placement: 'bottom'
	});
	
	$(".form_datetime").datetimepicker({
		format: 'yyyy-mm-dd',
		autoclose: true,
		minView: 2
	});
	
	$(".expiredButton").click(function () {
		$(location ).attr("href", "/david/signin");
	});

	$("#menu-toggle").click(function(e){
		$("#sidebar-wrapper").toggleClass("active",true);		
		e.preventDefault();
	});

	$('#sidebar-wrapper').mouseleave(function(e){
		$('#sidebar-wrapper').toggleClass('active',false);		
		e.stopPropagation();	
		e.preventDefault();										
	});
	
	$("body").submit(function(){
		$("#load").modal();
	});
	
	$('#search').click(function () {
    	$('#formSearch').submit();
    });
	
	$('#prev').click(function () {
		var page = parseInt($('#page').val());
		var newPage = page-1;
		
		if(newPage >= 1){
			$('#page').val(newPage);
	    	$('#formSearch').submit();
		}
    });
	
	$('#next').click(function () {
		var page = parseInt($('#page').val());
		var size = parseInt($('#size').val());
		var newPage = page+1;
		
		if(newPage <= size){
			$('#page').val(newPage);
	    	$('#formSearch').submit();
		}
    });
	
	$('#clear').click(function () {
    	$('.main-head table input').val('');
    });
	
	$('.eye-button').click(function () {
		$("#showText").find("#error").val($(this).parent().parent().find('input').val());
		$("#showText").modal();
	});
});
