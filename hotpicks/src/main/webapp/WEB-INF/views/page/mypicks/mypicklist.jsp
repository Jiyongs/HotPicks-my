<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/page/template/header.jsp"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<style>
#about .one-fourth img {
	-moz-box-shadow: 0px 1px 2px #656565;
	-webkit-box-shadow: 0px 1px 2px #656565;
	box-shadow: 0px 1px 2px #656565;
	display: block;
	margin-bottom: 20px;
	height: 160px;
	width: 100%;
}

</style>
<script>
$(function() {
	
	$.ajax({
		url : "${root}/mypicklist/list",
		type : "get",
		dataType : "html",
		success :function(result){
			console.log("넘어옴");
			$('#about').html(result);
			}
	});
	
});
</script>


<%-- <c:set var="title" value="${parameter.title}" />
<c:set var="info" value="${parameter.infoName}" />
<c:set var="image" value="${parameter.image1}" />
<c:set var="seq" value="${parameter.pg}" />
<c:set var="catid" value="${parameter.catId}" /> --%>
<div id="wrapper"> 
<div class="content">
<div>
	<div align="center" style="margin-bottom: 20px;">
		<div
			style="margin-bottom: 20px; font-size: 25px; font-family: Arial, Helvetica Neue, Helvetica, sans-serif;">
			<a>전체 |</a> <a>공연 |</a> <a>전시 |</a> <a>행사</a>
		</div>
		<div style="margin-bottom: 20px;">
			<a href="${root}/mypicklist/list"><img src="${root}/resources/style/images/listpicks.png"></a>
			<img src="${root}/resources/style/images/blank.png">
			<a href="${root}/mypickmap/mvmypickmap"><img src="${root}/resources/style/images/mappicks.png"></a>
			<img src="${root}/resources/style/images/blank.png">
			<a href="${root}/mypicklist/cal"><img src="${root}/resources/style/images/calendarpicks.png"></a>
		</div>
		
		<div style="margin-bottom: 10px; font-size: 15px;">
			<div style="float: right;">
			<input type="radio" value="가고싶은곳" name="wanna" checked="checked">가고싶은곳
			<input type="radio" value="다녀온곳" name="wanna">다녀온곳
				<button>저장</button>
				<button>삭제</button>
			</div>
			<div style="float: left;">
				<a href="#">가고싶은 곳</a><img src="${root}/resources/style/images/blank.png"> <a href="#">다녀온 곳</a>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>

    <div id="about">
   
     
    </div>
     </div>
     
     </div>
     </div>







<%@ include file="/WEB-INF/views/page/template/footer.jsp"%>