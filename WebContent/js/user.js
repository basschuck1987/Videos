$(document).ready(function(e){
	
	var userDiv = $('userDiv');
	
	getUser();
	
	function initUsers(users){
		usersDiv.empty();
		for (var i = 0; i < users.length; i++) {
			appendUser(users[i]);
		}
	};	
		
function appendUser(user){
	
	
	/* <tr>
	    <td></td>
	    <td><span class="badge">5</span></td>
	    <td></td>
	    <td></td>
	    <td></td>
	    <td></td>
	    <td>
		    <button type="button" class="btn btn-info " data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-pencil"></span>
		    </button>*/
	
	var tableRow= $('<tr></tr>');
	var username = $('<td>' + user.name + '</td>');
	var previews = $('<td><span class="badge">'+user.previews+'</span></td>');
	var date = $('<td>' +user.date+ '</td>');
	var description = $('<td>' +user.description+ '</td>');
	var blockedRole = $('<td>'+user.blocked+ user.role + '</td>');
	var button = $('<td><button type="button" class="btn btn-info " data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-pencil"></span></button>');
	
	userDiv.append(tableRow);
	userDiv.append(username);
	userDiv.append(previews);
	userDiv.append(date);
	userDiv.append(description);
	userDiv.append(blockedRole);
	userDiv.append(button);
}		
function getUsers(){
	$.ajax({
		url: 'UserServlet',
		method: 'GET',
		dataType: 'json',
		success: function(response){
			if(response.status == "success"){
				initUsers(response.users);
			}else{
				alert(response.message);
			}
	
		},
		error: function(request, message, error){
			alert(error);
		}
	});
};
});