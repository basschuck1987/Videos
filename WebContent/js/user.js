$(document).ready(function(e){

	var idUser = getUrlParameter('id');
	var userDiv = $('#userDiv');

	getUser();
	
		
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
		inputDesc = p,
		sel1 = sel1
	});
	
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
})
});