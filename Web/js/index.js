var loginStatus = 0;
var pageNumber = 1
var totalPage = 0;
$(document).ready(
		function() {
			var src = $("#previewPic #picr img").attr('src');
			if ("" == src) {
				$("#previewPic").hide();
			}

			/*
			 * var picdiv = $("<div class='picdiv'></div>"); picdiv.html("<div
			 * class='heardPic'>"+ "<img userid ='../image/1487064676150.png'
			 * src='../image/1487064676150.png'height='28' width='28'
			 * style='border-radius:50%' onclick='userDetail(this)'></img><span
			 * class='createTime'>传时间</span><span class='loginName'>登录名字</span></div>"+ "<div
			 * class='contentPic'><img class='contentPicimg'
			 * src='../image/1487064676150.png'></img>"+ "<div
			 * class='commentcontent'><div>444444赞</div><ul><li>评论一</li><li>评论二</li><li>评论三</li><li>评论四</li><li>评论五</li></ul>"+ "<div><textarea
			 * class='commentbox'style='width:250px; height:100px; border:solid
			 * 1px #bfbfbf; border-radius:5px;
			 * resize:none;'placeholder='#//留下你的足迹'></textarea></div>"+ "</div></div>");
			 * picdiv.appendTo($(".picBody"));
			 */

			$.ajax({
				url : "../userLoginStatus",
				success : function(dataStatus) {
					if (dataStatus.code == 1) {
						loginStatus = 1;
						$(".uploandpic").html('请先登录').css({
							color : "#bfbfbf"
						});
						$("#items_pic").attr("disabled", true).css({
							cursor : 'auto'
						});
						$(".personhemopage").css("background-image",
								"url(../image/nologin.png)");
					}
				}
			});

		});

function loadMeinv(pageNumber) {
	$
			.ajax({
				// type: "POST",
				url : "../findPicturessss",
				data : {
					"pageNo" : pageNumber
				},
				dataType : "json",
				async : false,
				success : function(data) {
					console.log(data);
					if (data.length > 0) {
						for (var i = 0; i < data.length; i++) {
							var cc = data.data[i].markPic.mark;
							var picdiv = $("<div class='picdiv'></div>");
							if (1 == cc) {
								picdiv
										.html("<div class='heardPic'>"
												+ "<img userid ='../image/1487064676150.png' src='"
												+ data.data[i].user.headPicUrl
												+ "'height='28' width='28' style='border-radius:50%' onclick='userDetail(this)'></img><span class='createTime'>"
												+ data.data[i].createadate
												+ "</span><span class='loginName'>"
												+ data.data[i].user.loginName
												+ "</span></div>"
												+ "<div class='contentPic'><img class='contentPicimg' src='"
												+ data.data[i].url
												+ "'></img>"
												+ "<div class='commentcontent'><div><p>#"
												+ data.data[i].discription
												+ "</p></div>"
												+ "<ul><li>"
												+ data.data[i].markNumber
												+ "赞</li><li>评论二</li><li>评论三</li><li>评论四</li><li>评论五</li></ul>"
												+ "<div class='commentboxd'><HR SIZE='1'><span class='dzspanimg' id='dzspanimg'>"
												+ "<img class='dzPicimg' id='dzPicimg' src='../image/dz2.png' code='"
												+ cc
												+ "' name='"
												+ data.data[i].id
												+ "' onclick='dzspanimg(this)'>"
												+ "</span></img></span><input class='commentbox'   placeholder='#留下你足迹'></input></div></div></div>");
								picdiv.appendTo($(".picBody"));
							} else {
								picdiv
										.html("<div class='heardPic'>"
												+ "<img userid ='../image/1487064676150.png' src='"
												+ data.data[i].user.headPicUrl
												+ "'height='28' width='28' style='border-radius:50%' onclick='userDetail(this)'></img><span class='createTime'>"
												+ data.data[i].createadate
												+ "</span><span class='loginName'>"
												+ data.data[i].user.loginName
												+ "</span></div>"
												+ "<div class='contentPic'><img class='contentPicimg' src='"
												+ data.data[i].url
												+ "'></img>"
												+ "<div class='commentcontent'><div><p>#"
												+ data.data[i].discription
												+ "</p></div>"
												+ "<ul><li>"
												+ data.data[i].markNumber
												+ "赞</li><li>评论二</li><li>评论三</li><li>评论四</li><li>评论五</li></ul>"
												+ "<div class='commentboxd'><HR SIZE='1'><span class='dzspanimg' id='dzspanimg'>"
												+ "<img class='dzPicimg' id='dzPicimg' src='../image/dz1.png' code='"
												+ cc
												+ "' name='"
												+ data.data[i].id
												+ "' onclick='dzspanimg(this)'>"
												+ "</span></img></span><input class='commentbox'   placeholder='#留下你足迹'></input></div></div></div>");
								picdiv.appendTo($(".picBody"));
							}
						}
					}
				}
			});
}
loadMeinv(pageNumber);
$(window).on(
		"scroll",
		function() {
			var scrollTop = document.documentElement.scrollTop
					|| document.body.scrollTop;
			if (document.body.scrollHeight
					- document.documentElement.clientHeight == scrollTop) {
				if (totalPage == pageNumber) {
					$("#loadMeinvMOre").html("无更多数据")
					return;
				} else {
					pageNumber++;
					loadMeinv(pageNumber);
				}

			}

		})

function dzspanimg(This) {
	var markNumber = $(This).parent().parent().prev().find('li').eq(0).html();
	var markNumber = parseInt(markNumber)
	var dzstatus = $(This).attr('code');
	var picId = $(This).attr('name');
	$.ajax({
		type : "POST",
		url : "../markForPic",
		data : {
			"mark" : dzstatus,
			"picId" : picId
		},
		dataType : "json",
		success : function(data) {
			if (1 == data.code) {
				window.location.href = "login.html";
			}
			if (dzstatus == 0 && data.code == 0) {
				console.log(markNumber);
				$(This).attr("src", "../image/dz2.png");// 位0说明当前时为赞转状态，所以点击之后状态改为1(已赞)
				$(This).attr('code', '1');
				$(This).parent().parent().prev().find('li').eq(0).html(
						markNumber + 1 + "赞");
			} else if (dzstatus == 1 && data.code == 0) {
				$(This).attr("src", "../image/dz1.png");
				$(This).attr('code', '0');
				$(This).parent().parent().prev().find('li').eq(0).html(
						markNumber - 1 + "赞");
			} else {
				console.log("重新登录吧");
			}

		}
	});
}

function userDetail(This) {
	alert($(This).attr('userid'));
}

$("#personhemopage").click(function() {
	if (loginStatus == 0) {
		alert("个人中心");
		//个人中心
	} else {
		window.location.href = "login.html";
	}
});
