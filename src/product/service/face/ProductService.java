package product.service.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.product.NutrientInfo;
import dto.product.NutrientKind;
import dto.product.ProductInfo;
import dto.product.SymptomCode;
import util.Paging;

public interface ProductService {

	/**
	 * ProductInfo테이블 전체조회
	 * 페이징 처리
	 * 
	 * @param paging - paging 정보 객체
	 * @return List<ProductInfo> - 테이블 전체 조회 리스트
	 */
	List<ProductInfo> getAllProduct(Paging paging);
	/**
	 * 페이징 객체 생성
	 * 
	 * 요청파라미터 curPage를 구한다
	 * ProductInfo테이블과 curPage값을 이용하여 Paging객체를 구하여 반환한다
	 * 
	 * @param req - 요청정보 객체
	 * @return 페이징 계산이 완료된 Paging 객체
	 */
	Paging getPaging(HttpServletRequest req);
	
	/**
	 * ProductInfo 카테고리별 조회
	 * 
	 * @param req 요청정보 객체 (선택한 카테고리)
	 * @param paging 페이징정보
	 * @return 선택한 카테고리 상품리스트
	 */
	List<ProductInfo> getProduct(HttpServletRequest req, Paging paging);
	
	/**
	 * SymptomCode 테이블 정보 조회
	 * 
	 * @return List<SymptomCode> - SymptomCode 테이블 정보
	 */
	List<SymptomCode> getSymptomCode();
	
	/**
	 * NutrientKind 테이블 정보 조회
	 * 
	 * @return List<NutrientKind> - NutrientKind 테이블 정보
	 */
	List<NutrientKind> getNutrientKind();
	
	/**
	 * ProductInfo 테이블 정보 조회
	 * 
	 * @param req
	 * @return ProductInfo 하나의 튜플 조회
	 */
	ProductInfo getProductInfoByProductId(HttpServletRequest req);
	

	
	
	
	
	List<Map<String, Object>> getNutirentInfoWithKind(ProductInfo productList);
	


	

}
