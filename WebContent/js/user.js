$(document).ready(function(e){

	var idUser = getUrlParameter('id');
	var userDiv = $('#userDiv');

	var videosDiv = $('#videosDiv');
	var followersDiv = $('#followersDiv');
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
		
	
function appendUser(user){
	console.log("uso")
	var tableRow= $('<tr></tr>');
	var username = $('<td>' + user.username + '</td>');
	var previews = $('<td><span class="badge">'+user.followers+'</span></td>');
	var date = $('<td>' +user.date+ '</td>');
	var description = $('<td>' +user.description+ '</td>');
	var blockedRole = $('<td>'+user.blocked+ user.role + '</td>');
	var button = $('<td><button type="button" class="btn btn-info " data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-pencil"></span></button><div id="myModal" class="modal fade" role="dialog"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal">&times;</button><h4 class="modal-title">Change</h4></div><div class="modal-body"><div class="form-group"><label for="usr">Name:</label><input id="usr" type="text" class="form-control"></div><div class="form-group"><label for="sur">Surname:</label<input id="ps" type="text" class="form-control"></div><div class="form-group"><label for="pwd">Password:</label><input id="ps" type="password" class="form-control"></div><div class="form-group"><label for="pwd">Repeat password:</label><input id="rps" type="password" class="form-control"></div><div class="form-group"><label for="desc">Description:</label><input id="desc" type="text" class="form-control"></div><div class="form-group"><label for="sel1">Select role:</label><select class="form-control" id="sel1"><option>User</option><option>Admin</option></select></div></div><div class="modal-footer"><button id="changeSave_btn" type="button" class="btn btn-success">Save changes</button></div></div></div></div>');
	
	tableRow.append(username);
	tableRow.append(previews);
	tableRow.append(date);
	tableRow.append(description);
	tableRow.append(blockedRole);
	tableRow.append(button);
	userDiv.append(tableRow);

}		

function appendVideo(video){
	console.log("uso u videe");
	var divColumn = $('<div class="col-md-5"></div>');
	var divThumbnail = $('<div class="thumbnail"></div>');
	var naziv = $('<div><p>' + video.name + '</p></div>');
	var linkVidea = $('<a href="/Videos/video.html?id='+video.id+'"></a>');
	var img = $('<img src="' + video.thumbnail + '" style="width:470px; height:300px";>');
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


/*<tr>
<td>
    <div class="glyphicon glyphicon-user">
    	<a href="link ka profilu"class="user-link">Full name 1</a>
    </div>
</td>
<td class="text-center"><span class="badge">5</span>
</td>
<td>
	<button type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>
	</button>
</td>
</tr>*/
function appendFollower(follower){
	console.log("uso u folovere")
	var tableRow= $('<tr></tr>');
	var glyphicon = $('<td><div class="glyphicon glyphicon-user"><a href="/Videos/user.html?id='+follower.id+'">'+" "+ follower.username+'</a></div></td>');
	var numberOfFoll = $('<td class="text-center"><span class="badge">5</span></td>');
	var button= $('<td><button type="button" class="btn btn-xs"><span class="glyphicon glyphicon-remove"></span></button></td>')

	tableRow.append(glyphicon);
	tableRow.append(numberOfFoll);
	tableRow.append(button);
	followersDiv.append(tableRow);
}

function getUser(){
	
	var params = $.param({
		id : idUser
	});
	console.log(params);
	$.ajax({
		url: 'UserServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			if(response.status == "success"){
				appendUser(response.user);
				initVideos(response.videos);
				initFollowers(response.followers);
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

$('#changeSave_btn').submit(function(e){
	
	var x = $('#usr').val();
	var y = $('#ps').val();
	var z = $('#rps').val();
	var p = $('#desc').val();
	var r = $('#sel1').find(":selected").text();
	
	var params = $.param({
		inputUsr : x,
		inputPs : y,
		inputRps : z,
		inputDesc : p,
		sel1 : sel1
	});
	console.log(params);
	$.ajax({
		url: 'UserServlet?' + params,
		method: 'POST',
		dataType: 'json',
		success: function(response){
			
		},
		error: function(request,message,error){
			alert(error)
		}
		
		
	});
});
$('#previewsAsc_btn').click(function(e){
	e.preventDefault();
	
	var params = $.param({
	//	lista: videosId,
		direction: "ASC",
		orderBy: "previews",
		id: idUser
	});
	console.log(params)
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
	console.log(params)
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