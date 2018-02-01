$(document).ready(function(e){
	
	// VIDEOS DIV
	var videosDiv = $("#videosDiv");
	
	//LOGIN REGISTER BUTTONS
	var loginRegisterButtons = $(".loginRegisterButtons");
	
	// LOGOUT BUTTON
	var logoutButton = $("#logoutButton");
	logoutButton.hide();
	
/*	// VIDEOS ID LIST
	var videosId = [];*/

	getVideos();
	
	function initVideos(videos){
		videosDiv.empty();
		for (var i = 0; i < videos.length; i++) {
			appendVideo(videos[i]);
		}
		
	
	};

	
	
	function appendVideo(video){
		var divColumn = $('<div class="col-md-5"></div>');
		var divThumbnail = $('<div class="thumbnail"></div>');
		var naziv = $('<div><p>' + video.description + '</p></div>');
		var linkVidea = $('<a href="video.html?id='+video.id+'"></a>');
		var img = $('<img src="' + video.thumbnail + '" style="width:100%">');
		var linkOwnera = $('<a href="user.html"></a>');
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
	
	function getVideos(){
		$.ajax({
			url: 'VideosServlet',
			method: 'GET',
			dataType: 'json',
			success: function(response){
				if(response.status == "success"){
					initVideos(response.videos);
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
	
	$('#descriptionAsc_btn').click(function(e){
		e.preventDefault();
		
		var params = $.param({
		//	lista: videosId,
			direction: "ASC",
			orderBy: "description"
		});
		console.log(params)
		$.ajax({
			url: 'VideosServlet?' + params,
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
	
	$('#descriptionDesc_btn').click(function(e){
		e.preventDefault();
		
		var params = $.param({
		//	lista: videosId,
			direction: "DESC",
			orderBy: "description"
		});
		console.log(params)
		$.ajax({
			url: 'VideosServlet?' + params,
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
	
	$('#ownerAsc_btn').click(function(e){
		e.preventDefault();
		
		var params = $.param({
		//	lista: videosId,
			direction: "ASC",
			orderBy: "owner"
		});
		console.log(params)
		$.ajax({
			url: 'VideosServlet?' + params,
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
	
	$('#previewsAsc_btn').click(function(e){
		e.preventDefault();
		
		var params = $.param({
		//	lista: videosId,
			direction: "ASC",
			orderBy: "previews"
		});
		console.log(params)
		$.ajax({
			url: 'VideosServlet?' + params,
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
			orderBy: "date"
		});
		console.log(params)
		$.ajax({
			url: 'VideosServlet?' + params,
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
	
	
	$('#searchForm').submit(function(e){
		e.preventDefault();
		
		var x = $('#inputSearch').val();
		var params = $.param({
			searchParams : x
		});
		console.log(params)
		$.ajax({
			url: 'VideosServlet?' + params,
			method: 'GET',
			dataType: 'json',
			success: function(response){
				if(response.status == 'failure'){
					initVideos(response.videos);
				} 
				
			},
			error: function(request, message, error){
				alert(error)
			}
		});

	});
	
	
});
	
