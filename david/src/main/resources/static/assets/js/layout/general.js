$(function() {
//	$('[data-toggle="popover"]').popover({
//		trigger: 'click',
//		container: 'body',
//		placement: 'bottom'
//	});
	
	$(".form_datetime").datetimepicker({
		format: 'yyyy-mm-dd',
		autoclose: true,
		minView: 2
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
	
	/***************** Load **********************/
	$("body").submit(function(){
		$("#bodyLoad").modal();
	});
	
	$('.evetMenu').click(function(){
		$("#bodyLoad").modal();
	});
	/***************** Edn Load **********************/
	
	
	/***************** Pagination **********************/
	$('#search').click(function () {
    	$('#formSearch').submit();
    });	
	
	$('#firstPage').click(function () {
		var page = parseInt($('#page').val());
		var newPage = 1;
		
		if(page > newPage){
			$('#page').val(newPage);
			$('#formSearch').submit();
		}
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
	
	$('#lastPage').click(function () {
		var page = parseInt($('#page').val());
		var size = parseInt($('#size').val());
		
		if(page < size){
			$('#page').val(size);
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
	
	$('.closeButton').click(function () {
		$('#formSearch').submit();	
	});
	/***************** End pagination **********************/
});
