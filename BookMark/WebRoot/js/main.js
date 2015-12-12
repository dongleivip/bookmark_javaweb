$(document).ready(function() { 
	
	$("#keyword").on("input propertychange", function(){
		pageQueryBookMarks($(this).val());
	});
	
	pageQueryBookMarks($("#keyword").val(),1);
	
	//console.log($(".delbtn").length);
});


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

function pageQueryBookMarks(key,pageNo){
	
	var NumReg = /^[1-9]+[0-9]*]*$/;
	if (!NumReg.test(pageNo)){  
        //不是正整数 --- 重新搜索,页码置为1
		pageNo = 1;
    }
	
	var reg = new RegExp(key,'gi');
	
	$.ajax({  
        type:"GET",  
        url: ctx + "/servlet/PageQueryBookMarkServlet",  
        data :  "keyword=" + key + "&pageNo=" + pageNo,
        dataType: "json", 
        success: function(page){
        	
        	var str_list = JSON.stringify(page.result);
        	$("#resultcount").text("搜索到" + page.totalRows + "个结果");
        	
        	var data = JSON.parse(str_list); 
        	
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
        	
        	if(page.totalRows > 10){
        		pagination(page);
        	} else {
        		$("#pager").empty();
        	}
        },
        error : function(){
        	alert("Request Failed...");
        }
	});
	
}

function filterKeyword(key){

	var reg = new RegExp(key,'gi');
	
	$.ajax({  
        type:"GET",  
        url: ctx + "/servlet/SearchBookMarkServlet",  
        data :  "keyword=" + key,
        dataType: "json", 
        success: function(jsonArray){
        	
        	var str_json = JSON.stringify(jsonArray);
        	var data = JSON.parse(str_json); 
        	
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
        	alert("Request Failed...");
        }
	});
	
}


function createList(data){
	
	var result = data.reduce(function(str,item){
		//var link = "http://www.baidu.com";
		var link = "#";
		if(item.url != ""){
			link = item.url;
		}
		str += "<li class=\"list\">";
		str += "<div class=\"title\"><span class=\"titlename\" onclick=\"openLink(this.title);\" title=\"" + link + "\">" + item.title+ "</span></div>";
		str += "<div class=\"delete\" ><span class=\"delbtn\" onclick=\"deleteItem('" + item.id + "')\">删除</span></div>";
		str += "<div class=\"createDate\">Created@" + jsonDateFormat(item.created) +  "</div>";
		str += "</li>";
		return str;
	},"");
	$("#list").html(result);
}

function openLink(url){
	//var $span = jQuery(element);
	//var url = $span.attr("title").trim();
	url = url.trim();
	if(url != "" && url !="#"){
		window.open(url);
	}
}

//json日期格式转换为正常格式
function jsonDateFormat(jsonDate) {
    try {
        var date = new Date(parseInt(jsonDate) * 1000);
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hours = date.getHours();
        var minutes = date.getMinutes();
        return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes;
    } catch (ex) {
        return "";
    }
}


function pagination(page){
	
	var content = "<ul class=\"pager\">";
	content += "<li>共" + page.totalRows + "条记录</li>" + "<li>共" + page.totalPages + "页</li>";
	content += "<li><a href=\"javascript:void(0);\" onclick=\"changePage(" + page.firstpage + ")\">首页</a></li>";
	content += "<li><a href=\"javascript:void(0);\" onclick=\"changePage(" + page.prePage + ")\" >上页</a></li>";
	
	//分页逻辑开始
	var begin = 0;
	var end = 0;
	if(page.totalPages <= 5){
		begin = 1;
		end = page.totalPages;
	}
	if(page.totalPages > 5){
		if(page.pageNo <= 3){
			begin = 1;
			end = 5;
		} else if(page.pageNo >= page.totalPages - 2){
			begin = page.totalPages - 4;
			end = page.totalPages;
		} else{
			begin = page.pageNo - 2;
			end = page.pageNo + 2;
		}
	}
	
	for(var i = begin ; i <= end ; i++){
		if(i != page.pageNo){
			content += "<li><a href=\"javascript:void(0);\" onclick=\"changePage("+ i +")\">" + i + "</a></li>";
		} else if(i == page.pageNo){
			content += "<li><b>" + i + "</b><li>";
		}
	}
	//分页逻辑结束
	
	content += "<li><a href=\"javascript:void(0);\" onclick=\"changePage(" + page.nextPage + ")\" >下一页</a></li>";
	content += "<li><a href=\"javascript:void(0);\" onclick=\"changePage(" + page.lastPage + ")\" >尾页</a></li>";
	content += "</ul>";
	
	$("#pager").html(content);
}


function changePage(pageNo){
	var keyword = $("#keyword").val();
	pageQueryBookMarks(keyword,pageNo);
}


function deleteItem(itemId){
	
	if(!confirm("确定要删除吗？")){
		return;
	}
	
	alert(itemId);
	
	$.ajax({  
        type:"POST",
        url: ctx + "/servlet/DeleteBookMarkServlet",
        data : "itemId=" + itemId,
        dataType: "json",
        success: function(json){
        	if(json.flag == "ok"){
        		//重新查询
        		pageQueryBookMarks($("#keyword").val(),1);
        	} else if(json.flag == "fail"){
        		alert("Delete Item Failed...");
        	}
        	
        },
        error : function(){
        	alert("Request Failed...");
        }
	});
}

    