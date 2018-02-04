$(document).ready(function(e){
	
	var idVideo = getUrlParameter('id');
	var videoDiv = $('#videoDiv');
	var urlDiv = $('#urlDiv');
	getVideo();

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
	var owner = $('<td class="text-center">'+video.owner.username+'</td>');
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

function getVideo(){
	
	var params = $.param({
		id : idVideo
	});
	console.log(params);
	$.ajax({
		url: 'VideoServlet?' + params,
		method: 'GET',
		dataType: 'json',
		success: function(response){
			if(response.status == "success"){
				appendVideo(response.video);
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
	
	
		
	
	
});
	
	
	
	





