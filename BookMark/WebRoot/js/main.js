$(document).ready(function() { 
	
	$("#keyword").on("input propertychange", function(){
		filterKeyword($(this).val());
	});
	
	//$("btn_add").on("click",addBookMark);
	
	
});


function queryBookMarks(){
	
	$.ajax({  
        type:"POST",
        url: ctx + "/servlet/QureyBookMarkServlet", 
        dataType: "json", 
        success: function(jsonArray){
        	var str_json = JSON.stringify(jsonArray);
        	var data = JSON.parse(str_json); 
        	createList(data);
        },
        error : function(){
        	alert("Request Failed...");
        }
	});
}

function addBookMark(){

	$.ajax({  
        type:"POST",  
        data:$("#form1").serialize(),
        url:$("#form1").attr("action"),  
        success: function(result){
        	
        },
        error : function(){
        	alert("Request Failed...");
        }
	});
	
}




function filterKeyword(key){

	//alert(key);
	var reg = new RegExp(key,'gi');
	
	$.ajax({  
        type:"GET",  
        url:"data/bookmarks.json",  
        dataType: "json",  
        success: function(data){
        	
        	if(key == ""){
        		createList(data);
        	} else {
        		
        		var result = data.filter(function(item){
    				return item.title.match(reg);
    			}).map(function(item){
    				item.title = item.title.replace(reg,'<span class="key">$&</span>');
    				return item;
    			});
        		createList(result);
        	}
        },
        error : function(){
        	alert("Request Data Failed...");
        }
	});
	
}


function createList(data){
	
	var result = data.reduce(function(str,item){
		str += "<li class=\"list\"><div class=\"title\">" + item.title 
			+  "</div><div class=\"createDate\">Created@" + formatDate(item.created) 
			+  "</div></li>";
		return str;
	},"");
	//$("#list").empty();
	$("#list").html(result);
}

	
function formatDate(date) {
	
    var time = new Date(parseInt(date) * 1000);
    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    month = month > 10 ? month : '0' + month;
    var day = time.getDay();
    day = day > 10 ? day : '0' + day;
    return year + '-' + month + '-' + day;
}

    