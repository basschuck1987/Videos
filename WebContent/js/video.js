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
	
	tableRow.append(name);
	tableRow.append(previews);
	tableRow.append(owner);
	tableRow.append(date);
	tableRow.append(description);
	tableRow.append(rating);
	videoDiv.append(tableRow);

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





		
	
	
});
	
	
	
	





