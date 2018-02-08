//$(document).ready(function(e){
	
	
	
//	getComments();
	
	function initComments(comments){
		commentDiv.empty();
		for (var i = 0; i < comments.length; i++) {
			appendComment(comments[i]);
		}
	};
	/*<div class="col-sm-offset-3 col-sm-5">
		<div class="panel panel-default">
			<div class="panel-heading">
				<strong>myusername</strong> <span class="text-muted">commented 5 days ago</span>
			</div>
			<div class="panel-body">
				Panel content
			</div>
		</div>
	</div>*/


function getComments(){
	$.ajax({
		url: 'VideoServlet',
		method: 'GET',
		dataType: 'json',
		success: function(response){
			if(response.status == "success"){
				initComments(response.comments);
				console.log(response);
			}else{
				alert(response.message);
			}
	
		},
		error: function(request, message, error){
			alert(error);
		}
	});
};

$('#ratingAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "ASC",
		orderBy: "rating"
	});
	console.log(params);
	$.ajax({
		url: 'VideoServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initComments(response.comments);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
});

$('#ratingDesc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "DESC",
		orderBy: "rating"
	});
	console.log(params);
	$.ajax({
		url: 'VideoServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initComments(response.comments);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
});

$('#dateAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "ASC",
		orderBy: "date"
	});
	console.log(params);
	$.ajax({
		url: 'VideoServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initComments(response.comments);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});

$('#dateDesc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
		direction: "DESC",
		orderBy: "date"
	});
	console.log(params);
	$.ajax({
		url: 'VideoServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initComments(response.comments);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});
	

//});