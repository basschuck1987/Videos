$(document).ready(function(e){

	var idUser = getUrlParameter('id');
	
	var loggedInUser =  null;
	
	var userDiv = $('#userDiv');
	
	var changeDiv = $('#changeDiv');
	
	var videosDiv = $('#videosDiv');
	
	var followersDiv = $('#followersDiv');
	
	//LOGIN REGISTER BUTTONS
	var loginRegisterButtons = $(".loginRegisterButtons");
	loginRegisterButtons.show();
	
	//MY PROFILE BUTTON
	var myProfileButton = $(".myProfileButton");
	myProfileButton.hide();
	
	// LOGOUT BUTTON
	var logoutButton = $("#logoutButton");
	logoutButton.hide();
	
	//USER BUTTON
	var usersButton = $("#usersButton");
	usersButton.hide();
	
	//BLOCK BUTTON
	var blockButton = null;
	
	//CHANGE BUTTON
	var buttonEdit = null;
	
	var checkboxChange = null;
	
	getUser();
	
	function initVideos(videos){
		videosDiv.empty();
		for (var i = 0; i < videos.length; i++) {
			appendVideo(videos[i]);
		}
	};
	
	function initFollowers(followers){
		followersDiv.empty();
		for (var i = 0; i < followers.length; i++) {
			appendFollower(followers[i]);
		}
	};
	
	$('#goToProfile').button().click(function(){
		window.location.href = '/Videos/user.html?id=' + loggedInUser.id;
	});
		
	
function appendUser(user){
	var tableRow= $('<tr></tr>');
	var username = $('<td>' + user.username + '</td>');
	var previews = $('<td><span class="badge">'+user.followers+'</span></td>');
	var date = $('<td>' +new Date(user.date).toLocaleDateString("en-US")+ '</td>');
	var description = $('<td>' +user.description+ '</td>');
	var theRole = $('<td>'+ user.role + '</td>');
	var checkBlocked = $('<td class="text-center"><input id="checkBox" type="checkbox" disabled="disabled" value="Bike"></td>');
	var buttonBlock = $('<td><button id="Block_btn" type="button" class="btn btn-m"><span class="glyphicon glyphicon-ban-circle"></span></button></td>');
	var buttonChange = $('<td><button id="changeBtn" type="button" class="btn btn-info " data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-pencil"></span></button>'+
					'<div id="myModal" class="modal fade" role="dialog">'+
						'<div class="modal-dialog">'+
						'<div class="modal-content">'+
							'<div class="modal-header"><button id="closeModal_btn" type="button" class="close" data-dismiss="modal">&times;</button><h4 class="modal-title">Change</h4></div>'+
					'<div class="modal-body">'+
						'<div class="form-group"><label for="usr">Name:</label><input class="form-control" type="text" id="usr" value="'+user.name+'"></div>'+
						'<div class="form-group"><label for="sur">Surname:</label><input type="text" class="form-control" id="sur" value="'+user.surname+'" ></div>'+
						'<div class="form-group"><label for="pwd">Password:</label><input type="password" class="form-control" id="ps" value="'+user.password+'" ></div>'+
						'<div class="form-group"><label for="desc">Description:</label><input type="text" class="form-control" id="desc" value="'+user.description+'"></div>'+
						'<div class="form-group"><label for="sel1">Select role:</label><select class="form-control" id="sel1"><option>User</option><option>Admin</option></select></div>'+
						'<div id="checkBlock" class="form-group"><label for="blocked">Block</label><input id="checkbox_change" type="checkbox" class="form-control"></div></div>'+
						'<div class="modal-footer"><button id="changeSave_btn" type="submit" class="btn btn-success">Save changes</button></div></div></div></div>');
	
	
	tableRow.append(username);
	tableRow.append(previews);
	tableRow.append(date);
	tableRow.append(description);
	tableRow.append(theRole);
	tableRow.append(checkBlocked);
	tableRow.append(buttonBlock);
	tableRow.append(buttonChange);
	userDiv.append(tableRow);
	
	blockButton = $("#Block_btn");
	blockButton.hide();
	
	checkBlock = $('#checkBlock');
	checkBlock.hide();
	
	checkboxChange = $('#checkbox_change');
	
	checkBox = $('#checkBox');
	
	if(user.blocked == true){
		checkBox.attr('checked','checked');
		checkboxChange.attr('checked','checked');
		buttonBlock.hide();
	}
	
	buttonEdit = $("#changeBtn");
	if(loggedInUser != null && loggedInUser.id == user.id){
		buttonEdit.show();
	}else{
		buttonEdit.hide();
	}
	
	blockButton.click(function(e){
		var x = $('#usr').val();
		var s = $('#sur').val();
		var y = $('#ps').val();
		var z = $('#rps').val();
		var p = $('#desc').val();
		var r = $('#sel1').find(":selected").text();
		
		var params = $.param({
			id : idUser,
			inputUsr : x,
			inputSur : s,
			inputPs : y,
			inputDesc : p,
			sel1 : r,
			action : 'update',
			blocked : true
		});
		
		$.ajax({
			url: 'UserServlet?' + params,
			method: 'POST',
			dataType: 'json',
			success: function(response){
				if(response.status == "success"){
					userDiv.empty();
					getUser();
				}else{
					alert(response.message);
				}
			},
			error: function(request,message,error){
				alert(error)
			}
		});	
		
		
	});


}

function appendVideo(video){
	var divColumn = $('<div class="col-md-5"></div>');
	var divThumbnail = $('<div class="thumbnail"></div>');
	var naziv = $('<div><p>' + video.name + '</p></div>');
	var linkVidea = $('<a href="/Videos/video.html?id='+video.id+'"></a>');
	var img = $('<img src="' + video.thumbnail + '" style="width:300px; height:270px";>');
	var linkOwnera = $('<a href="/Videos/user.html?id=' + video.owner.id+'"></a>');
	var caption = $('<div class="caption"><p>' + video.owner.username + '</p></div>');
	var textBlock = $('<div class="text-block"> <p>Date: '+ new Date(video.date).toLocaleDateString("en-US") + '</p><p>Previews: '+ video.previews +'</p></div>');
	
	linkOwnera.append(caption);
	linkVidea.append(img);
	divThumbnail.append(naziv);
	divThumbnail.append(linkVidea);
	divThumbnail.append(linkOwnera);
	divThumbnail.append(textBlock);
	divColumn.append(divThumbnail);
	
	videosDiv.append(divColumn);
	
}

function appendFollower(follower){
	var tableRow= $('<tr></tr>');
	var glyphicon = $('<td><div class="glyphicon glyphicon-user"><a href="/Videos/user.html?id='+follower.id+'">'+" "+ follower.username+'</a></div></td>');
	var numberOfFoll = $('<td class="text-center"><span class="badge">5</span></td>');
	var button= $('<td><button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>')

	tableRow.append(glyphicon);
	tableRow.append(numberOfFoll);
	tableRow.append(button);
	followersDiv.append(tableRow);
}


function showHide(loggedInUser){
	if(loggedInUser == null){
		/*usersButton.hide();
		myProfileButton.hide();*/
		blockButton.hide();
		buttonEdit.hide();
	}else{
		myProfileButton.show();
		loginRegisterButtons.hide();
		logoutButton.show();
//		buttonEdit.show();
		if(loggedInUser.role == 'ADMIN'){
			usersButton.show();
			blockButton.show();
			buttonEdit.show();
			checkBlock.show();
			$('#sel1').removeAttr('disabled');
		}else if(loggedInUser.role == 'USER'){
			/*usersButton.hide();*/
			blockButton.hide();
			$('#sel1').attr('disabled', 'disabled');
			
		}
	}
		
};

function getUser(){
	
	var params = $.param({
		id : idUser
	});

	$.ajax({
		url: 'UserServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			if(response.status == "success"){
				loggedInUser = response.loggedInUser;
				appendUser(response.user);
				initVideos(response.videos);
				initFollowers(response.followers);
				showHide(response.loggedInUser);
			}else{
				alert(response.message);
			}
		},
		error: function(request, message, error){
			alert(error);
		}
	});
};


$('#fff').submit(function(e){
	e.preventDefault();
	var x = $('#usr').val();
	var s = $('#sur').val();
	var y = $('#ps').val();
	var z = $('#rps').val();
	var p = $('#desc').val();
	var r = $('#sel1').find(":selected").text();
	var checkbox = checkboxChange.is(':checked');
	
	var params = $.param({
		id : idUser,
		inputUsr : x,
		inputSur : s,
		inputPs : y,
		inputDesc : p,
		sel1 : r,
		blocked: checkbox,
		action : 'update'
	});
	$.ajax({
		url: 'UserServlet?' + params,
		method: 'POST',
		dataType: 'json',
		success: function(response){
			if(response.status == "success"){
				userDiv.empty();
				getUser();
			}else{
				alert(response.message);
			}
		},
		error: function(request,message,error){
			alert(error)
		}
		
	});
	$('.modal-backdrop').remove();
});


$('#previewsAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
	//	lista: videosId,
		direction: "ASC",
		orderBy: "previews",
		id: idUser
	});
	$.ajax({
		url: 'UserServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initVideos(response.videos);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});

$('#dateAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
	//	lista: videosId,
		direction: "ASC",
		orderBy: "date",
		id: idUser
	});
	$.ajax({
		url: 'UserServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			initVideos(response.videos);
		},
		error: function(request, message, error){
			alert(error)
		}
		
	});
	
});

});