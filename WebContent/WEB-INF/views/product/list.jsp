<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
window.onload = function() {
	//이벤트리스너 등록 - addEventListener
	//이벤트리스너 해제 - removeEventListener
	
	//--------------------------------------------------------

	btnTargetCate.addEventListener("click", targetCateClicked)
	btnSymptomCate.addEventListener("click", SymptomCateClicked)
	btnNutCate.addEventListener("click", NutCateClicked)

	//--------------------------------------------------------
	
} //window.onload end

function targetCateClicked() {
	targetCate.style.display = "block";
	symptomCate.style.display = "none";
	nutCate.style.display ="none";
}
function SymptomCateClicked() {
	targetCate.style.display = "none";
	symptomCate.style.display = "block";
	nutCate.style.display ="none";
}
function NutCateClicked() {
	targetCate.style.display = "none";
	symptomCate.style.display = "none";
	nutCate.style.display ="block";
}


	
</script>
<style type="text/css">
.category{
	display: none;
}

</style>


<div class="container">


	<hr>
	<form action = "/product/display" method = "get"><input type="text" name="search"><input type = "submit" value="검색" /></form><br>
	<button id="btnTargetCate">대상별</button>
	<button id="btnSymptomCate">증상별</button>
	<button id="btnNutCate">영양소별</button>
	<hr>
	
	<div id="targetCate" class="category">
	<button><a href="/product/display?targetId=1">청소년</a></button>
	<button><a href="/product/display?targetId=2">성인</a></button>
	<button><a href="/product/display?targetId=3">중장년</a></button>
	</div>
	
	<div id="symptomCate" class="category">
	<c:forEach items="${symptomList }" var="symptomCode">
	<button><a href = "/product/display?symptomId=${symptomCode.symptomId }">${symptomCode.symptomName }</a></button>
	</c:forEach>
	</div>
	
	<div id="nutCate" class="category">
	<c:forEach items="${nutrientList }" var="NutrientKind">
	<button><a href = "/product/display?nutId=${NutrientKind.nutId }">${NutrientKind.nutKind }</a></button>
	</c:forEach>
	</div>

	



	<hr>



	<c:forEach items="${productList }" var="productInfo">
		<tr>
			<td>
			<a href="/product/detail?productId=${productInfo.productId }">
				<img src="${productInfo.image }" width="300" height="200">
			</a></td>
		</tr>
		<tr>
			<td>${productInfo.productName }</td>
			<td>${productInfo.starScore }</td>
		</tr>
		<tr>
			<td>${productInfo.allergyInfo }</td>
		</tr>
	</c:forEach>



	<!-- .container -->
</div>
<div id="result"></div>
<c:import url="/WEB-INF/views/layout/paging.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />
