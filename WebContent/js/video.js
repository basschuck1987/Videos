$(document).ready(function(e){
	
	var idVideo = getUrlParameter('id');
	
	var loggedInUser =  null;
	
	var videoDiv = $('#videoDiv');
	
	var urlDiv = $('#urlDiv');
	var commentDiv = $('#commentDiv');
	
	//LOGIN REGISTER BUTTONS
	var loginRegisterButtons = $(".loginRegisterButtons");
	
	//MY PROFILE BUTTON
	var myProfileButton = $(".myProfileButton");
	myProfileButton.hide();
	
	// LOGOUT BUTTON
	var logoutButton = $("#logoutButton");
	logoutButton.hide();
	
	//USER BUTTON
	var usersButton = $("#usersButton");
	usersButton.hide();
	
	//CHANGE BUTTON
	var changeVideoButton = null;
	
	getVideo();
	
	function initComments(comments){
		commentDiv.empty();
		for (var i = 0; i < comments.length; i++) {
			appendComment(comments[i]);
		}
	};
	
	$('#goToProfile').button().click(function(){
		window.location.href = '/Videos/user.html?id=' + loggedInUser.id;
	});
	

function appendVideo(video){
	console.log("aaaaaa");
	
	var column1 = $('<div class="col-md-2"></div>');
	var column = $('<div class="col-md-8 pb-video"></div');
	var ifrime = $('<iframe class="pb-video-frame" width="100%" height="450" src="https://www.youtube.com/embed/'+video.url +' " frameborder="0" allowfullscreen></iframe>');
	
	urlDiv.append(column1);
	column.append(ifrime);
	urlDiv.append(column);
	
	var tableRow = $('<tr></tr>');
	var name = $('<td class="text-center">'+video.name+'</td>');
	var previews = $('<td class="text-center">'+video.previews+'</td>');
	var owner = $('<td class="text-center"><a href="/Videos/user.html?id='+video.owner.id+'"</a>'+video.owner.name+'</td>');
	var date = $('<td class="text-center">'+new Date(video.date).toLocaleDateString("en-US")+'</td>');
	var description = $('<td class="text-center">'+video.description+'</td>');
	var rating = $('<td class="text-center"><a>Likes:</a><span class="badge">'+video.like+'</span><a>Dislikes:</a><span class="badge">'+video.dislikes+'</span></span></td>');
	var changeButton =$('<td><button id="changeVideoBtn" type="button" class="btn btn-info " data-toggle="modal" data-target="#changeVideo"><span class="glyphicon glyphicon-pencil"></span>Edit</button>'+
		 	'<div id="changeVideo" class="modal fade" role="dialog">'+
		 	'<div class="modal-dialog">'+
		 	'<div class="modal-content">'+
		      '<div class="modal-header">'+	
		      	'<button type="button" class="close" data-dismiss="modal">&times;</button>'+
		      	'<h4 class="modal-title">CHANGE</h4>'+
		      '</div>'+
		      '<div class="modal-body">'+
		      '<div class="form-group">'+
		      		'<label for="tittle">Tittle:</label>'+
		      	'<input id="tittle" type="text" class="form-control" required value="'+video.name+'">'+
		      	'</div>'+
		      '<div class="form-group">'+
		      	'<label for="url">URL:</label>'+
		      	'<input id="url" type="text" class="form-control" required value="'+video.url+'">'+
		      	'</div>'+
		      	'<div class="form-group">'+
		      	'<label for="desc">Description:</label>'+
		      		'<input id="descVideo" type="text" class="form-control" value="'+video.description+'">'+
		      '</div>'+
		      '<div class="form-group">'+
		      		'<label for="sel2">Select visibility:</label>'+
		      		'<select class="form-control" id="sel2">'+
		      			'<option>PUBLIC</option>'+
		      			'<option>UNLISTED</option>'+
		      			'<option>PRIVATE</option>'+
		      			'</select>'+
		      		'</div>'+
		      	'</div>'+
		      '<div class="modal-footer">'+
		      '<button type="submit" class="btn btn-success">Save</button>'+
		      	'</div>'+
		      		'</div></td>');
	var emptyColumn =$('<td></td>')
	var deleteVideoButton = $('<td><td<button id="deleteVideoButton" class="btn btn-danger btn-m">DELETE VIDEO</button></td></td>')
	
	tableRow.append(name);
	tableRow.append(previews);
	tableRow.append(owner);
	tableRow.append(date);
	tableRow.append(description);
	tableRow.append(rating);
	tableRow.append(changeButton);
	tableRow.append(emptyColumn);
	tableRow.append(deleteVideoButton);
	videoDiv.append(tableRow);
	
	deleteVideoButton = $('#deleteVideoButton');
	
	deleteVideoButton.click(function(e){
		var params = $.param({
			id : idVideo,
			action : "delete"
		});
		$.ajax({
			url: 'VideoServlet?' + params,
			method: 'POST',
			dataType: 'json',
			success : function(response){
				if(response.status == "success"){
					window.location.replace("/Videos/index.html");
				}else{
					alert(response.message);
				}
			},
			error: function(request, message, error){
				alert(error);
			}
			
			
			
			
		});
		
		
	});
	
	/*changeVideoButton = $('#changeVideoBtn');
	if(loggedInUser != null && loggedInUser.id == user.id){
		changeVideoButton.show();
	}else{
		changeVideoButton.hide();
	}*/
	
}
function appendComment(comment){
	console.log("komentariii");
	/*<a href="/Videos/video.html?id='+video.id+'"></a>*/
	var column = $('<div class="col-sm-offset-3 col-sm-5"></div>');
	var panel = $('<div class="panel panel-default"></div>');
	var panelHeading = $('<div class="panel-heading"><strong><a href="/Videos/user.html?id='+comment.owner.id+'"</a><span class="text-muted">'+ comment.owner.name+' </span></strong> <span>'+new Date(comment.date).toLocaleDateString("en-US")+'</span></div>');
	var body = $('<div class="panel-body">'+comment.content+'</div>');
	
	panel.append(panelHeading);
	panel.append(body);
	column.append(panel);
	commentDiv.append(column);
	}



function showHide(loggedInUser){
	console.log(loggedInUser);
	if(loggedInUser == null){
		usersButton.hide();
		myProfileButton.hide();
	}else{
		myProfileButton.show();
		loginRegisterButtons.hide();
		logoutButton.show();
		if(loggedInUser.role == 'ADMIN'){
			usersButton.show();
		}else if(loggedInUser.role == 'USER'){
			usersButton.hide();
		}
	}
		
};

function getVideo(){
	
	var params = $.param({
		id : idVideo,
		
	});
	console.log(params);
	$.ajax({
		url: 'VideoServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			if(response.status == "success"){
				appendVideo(response.video);
				initComments(response.comments);
				showHide(response.loggedInUser);
				loggedInUser = response.loggedInUser;
				console.log(response);
			}else{
				console.log(response);
				alert(response.message);
				window.location.replace("/Videos/index.html");
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
		orderBy: "date",
		id : idVideo
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
		orderBy: "date",
			id : idVideo
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

$('#videoEditForm').submit(function(e){
	e.preventDefault();
	var tittleInput = $('#tittle').val();
	var urlInput = $('#url').val();
	var descriptionVideoInput = $('#descVideo').val();
	var select2 = $('#sel2').find(":selected").text();
	
	var params = $.param({
		id : idVideo,
		tittleInput : tittleInput,
		urlInput : urlInput,
		descriptionVideoInput : descriptionVideoInput,
		select2 : select2,
		action : "update"
	});
	$.ajax({
		url: 'VideoServlet?' + params,
		method: 'POST',
		dataType: 'json',
		success : function(response){
			if(response.status == "success"){
			/*	urlDiv.empty();
				videoDiv.empty();
				getVideo();*/
				location.reload();
			}else{
				alert(response.message);
			}
		},
		error: function(request, message, error){
			alert(error);
		}
	});
	$('.modal-backdrop').remove();
});


$('#commentForm').submit(function(e){
	e.preventDefault();
	var comment = $('#writeComment').val();
	var params = $.param({
		comment : comment,
		id : idVideo
	});
	console.log(params);
	$.ajax({
		url: 'CommentServlet?' + params,
		method: 'POST',
		dataType: 'json',
		success : function(response){
			if(response.status == "success"){
				
				location.reload();
			}else{
				alert(response.message);
			}
		},
		error: function(request, message, error){
			alert(error);
		}
		
		
	});
	
	
	
})
		
	
	
});
	
	
	
	





