 /*<tr>
        <td>
            <div class="glyphicon glyphicon-user">
            	<a href="link ka profilu"class="user-link">Full name 1</a>
            </div>
        </td>
        <td>
        </td>
        <td>
        </td>
        <td>
        </td>
        <td>
        </td>
        <td>
	<button type="button" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>
	</button>
</td>*/

$(document).ready(function(e){
	
	var usersDiv = $('#usersDiv')
	
	getUsers();
	
	function initUsers(users){
		usersDiv.empty();
		for (var i = 0; i < users.length; i++) {
			appendUser(users[i]);
		}
	};
	
	



function appendUser(user){
	var tableRow= $('<tr></tr>');
	var userName = $('<td><div class="glyphicon glyphicon-user"><a href="user.html?"id='+user.id+'>'+ user.username +'</a></div>');
	var name = $('<td>'+user.name+'</td>');
	var surname = $('<td>'+user.surname+'</td>');
	var email = $('<td>'+user.email+'</td>');
	var role = $('<td>'+user.role +'</td>');
	
	usersDiv.append(tableRow);
	usersDiv.append(userName);
	usersDiv.append(name);
	usersDiv.append(surname);
	usersDiv.append(email);
	usersDiv.append(role);
}

function getUsers(){
	$.ajax({
		url: 'UsersServlet',
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
$('#loginForm').submit(function(e){
	e.preventDefault();
	
	var x = $('#inputUsername').val().trim();
	var p = $('#inputPassword').val();
	var params = $.param({
		username: x,
		password: p
	});
	console.log(params)
	$.ajax({
		url: 'LoginServlet?' + params,
		method: 'POST',
		dataType: 'json',
		success: function(response){
			if(response.status == 'failure'){
				alert(response.message)
			} 
			logoutButton.show();
			loginRegisterButtons.hide();
			getVideos();
			
		},
		error: function(request, message, error){
			alert(error)
		}
	});

});
});