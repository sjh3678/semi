<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
window.onload = function() {
	//이벤트리스너 등록 - addEventListener
	//이벤트리스너 해제 - removeEventListener
	//--------------------------------------------------------
	<c:forEach items="${nikList }" var="nik">
	btn${nik.ni.nutId }.addEventListener("click", togleDetail${nik.ni.nutId })
	</c:forEach>
	//--------------------------------------------------------
	
} //window.onload end

<c:forEach items="${nikList }" var="nik">
function togleDetail${nik.ni.nutId }() {
	if (detail${nik.ni.nutId }.style.display == "none"){
	detail${nik.ni.nutId }.style.display = "block";
	}else{
		detail${nik.ni.nutId }.style.display = "none";
	}
	
}
</c:forEach>
</script>


<h1>디테일 페이지</h1>

<hr>
<style type="text/css">
.tg  {border-collapse:collapse;border-spacing:0;}
.tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  overflow:hidden;padding:10px 5px;word-break:normal;}
.tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
  font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
.tg .tg-zv4m{border-color:#ffffff;text-align:left;vertical-align:top}
.tg .tg-baqh{text-align:center;vertical-align:top}
.tg .tg-8jgo{border-color:#ffffff;text-align:center;vertical-align:top}
.tg .tg-lqy6{text-align:right;vertical-align:top}
.tg .tg-0lax{text-align:left;vertical-align:top}
.detail{
	display: none;
}

table{
	margin: auto
}
</style>

<table class="tg" style="undefined;table-layout: fixed; width: 960px">
<colgroup>
<col style="width: 480px">
<col style="width: 240px">
<col style="width: 240px">
</colgroup>
<thead>
  <tr>
    <td class="tg-0lax" rowspan="3"><img src="${productInfo.image }" width="480" height="300"></td>
    <td class="tg-baqh">${productInfo.productName }</td>
    <td class="tg-lqy6">별점 : ${productInfo.starScore }점</td>
  </tr>
  <tr>
    <td class="tg-baqh">제조사 : ${productInfo.manuCom }</td>
    <td class="tg-baqh">타입 : ${productInfo.type }</td>
  </tr>
  <tr>
    <td class="tg-baqh"><a href = "${productInfo.purchaseLink }">구매하러가기</a></td>
    <td class="tg-baqh"><button>비교함추가</button> </td>
  </tr>
</thead>
</table>




<hr>


<table class="tg" style="undefined;table-layout: fixed; width: 960px">
<colgroup>
<col style="width: 120px">
<col style="width: 310px">
<col style="width: 90px">
<col style="width: 170px">
<col style="width: 170px">
<col style="width: 100px">
</colgroup>
<c:forEach items="${nikList }" var="nik">
<thead>
  <tr>
    <th class="tg-baqh">${nik.nk.nutKind }</th>
    <th class="tg-baqh" colspan="2">${nik.ni.nutContent }/ ${nik.nk.rcmAdult }</th>
    <th class="tg-baqh">${nik.ni.nutContent }</th>
    <th class="tg-baqh">${nik.nk.rcmAdult }</th>
    <th class="tg-8jgo"><button id = "btn${nik.ni.nutId }">상세</button></th>
  </tr>
</thead>
<tbody id = "detail${nik.ni.nutId }" class = "detail">
  <tr>
    <td class="tg-baqh" colspan="5">${nik.nk.effect }</td>
    <td class="tg-zv4m"></td>
  </tr>
  <tr>
    <td class="tg-baqh" colspan="2">${nik.nk.deficiency }</td>
    <td class="tg-baqh" colspan="3">${nik.nk.hyperact }</td>
    <td class="tg-zv4m"></td>
  </tr>
</tbody>
</c:forEach>
</table>


<c:import url="/WEB-INF/views/layout/footer.jsp" />