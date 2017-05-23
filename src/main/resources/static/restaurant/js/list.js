$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    var search = {}
    if($("#name").val() !== ""){
    	search["nameLike"] = $("#name").val();
    }
    //search["nameEquals"] = $("#name").val();
    search["sort"] = $("#sort").val();
    search["page"] = $("#page").val();
    search["pageSize"] = $("#pageSize").val();

    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/restaurant/search",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	var list = "";
        	if(data.binaryStatus == "success"){
        		$.each(data.result, function(i, item) {
                	list += "<li class=\"list-group-item\">"+item.name +"<span class=\"badge\">"+item.rating+"</span></li>";
                });
        		$('#list-group').html(list);
        	}
        	if(data.binaryStatus == "error"){
        		list += "<ul>"
            	$.each(data.result, function(i, item) {
                    list += "<li>"+item +"</li>";
                });
            	list += "</ul>"
            	$('#message').html(list).addClass("alert alert-danger");
        	}
        	
        	
        	
        	
            $("#btn-search").prop("disabled", false);
            
            
            
        },
        error: function (e) {
        	var errorResponse = "";
            $('#feedback').html(json);

            $("#btn-search").prop("disabled", false);

        }
    });

}
