package com.fulldoping.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.product.NutrientKind;
import dto.product.ProductInfo;
import dto.product.SymptomCode;
import product.service.face.ProductService;
import product.service.impl.ProductServiceImpl;
import util.Paging;

@WebServlet("/product/display")
public class ProductDisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private ProductService productService = new ProductServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		System.out.println("/porduct/display [GET]");
		
		
		//요청파라미터를 전달하여 Paging객체 생성하기
		Paging paging = productService.getPaging(req);
		System.out.println("ProductDisplayController [GET] - " + paging);
		System.out.println("paging : " + paging  );
		
		//상품 카테고리별 조회
		List<ProductInfo> productList = productService.getProduct(req, paging);
		System.out.println("productList : " + productList);
		
		
		List<SymptomCode> symptomList = productService.getSymptomCode();
//		System.out.println("symptomList : " + symptomList);
		List<NutrientKind> nutrientList = productService.getNutrientKind();
//		System.out.println("nutrientList : " + nutrientList);
		
		
		//조회결과 MODEL값 전달
		req.setAttribute("symptomList", symptomList);
		req.setAttribute("nutrientList", nutrientList);
		req.setAttribute("productList", productList);
	
		//페이징 정보 MODEL값 전달
		req.setAttribute("paging", paging);
		
		//VIEW 지정 및 응답 - forward
		req.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(req, resp);
	}
}
