$(document).ready(function(e){
	
	getVideos();
	
	function getVideos(){
		$.ajax({
			url: 'VideosServlet',
			method: 'GET',
			dataType: 'json',
			success: function(response){
				console.log(response);
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
					alert(response.msg)
				}
			},
			error: function(request, message, error){
				alert(error)
			}
		});

	});
	/*$('#signupForm').submit(function(e){
		e.preventDefault();
		
		var x = $('#enterUsername').val().trim();
		var p = $('#enterPassword').val();
		var s = $('#repeatPassword').val();
		
		var params = $.param({
			usernameReg: x,
			passwordReg: p,
			passwordRep: s
		});
		$.ajax({
			url: 'RegisterServlet?' + params,
			method: 'POST',
			dataType: 'json',
			success: function(response){
				if(response.status == 'failure'){
					alert(response.message)
				}
			},
			error: function(request, message, error){
				alert(error)
			}
		});

	});*/
	
});
	
