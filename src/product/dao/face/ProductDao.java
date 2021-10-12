package product.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import dto.product.NutrientInfo;
import dto.product.NutrientKind;
import dto.product.ProductInfo;
import dto.product.SymptomCode;
import util.Paging;

public interface ProductDao {
	/**
	 * ProductInfo테이블 전체조회
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 정보 객체
	 * @return List<ProductInfo> - ProductInfo테이블 전체 조회 결과 리스트
	 */
	public List<ProductInfo> selectAll(Connection conn, Paging paging);

	/**
	 * 총 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCntAll(Connection conn);

	/**
	 * 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param search - 검색어
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCntBySearch(Connection conn, String search);
	
	/**
	 * 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param targetId - 검색 대상
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCateCntByTarget(Connection conn, int targetId);
	
	/**
	 * 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param symptomId - 검색 증상
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCateCntBySymptom(Connection conn, int symptomId);
	
	/**
	 * 상품 수 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param nutId - 검색 영양소
	 * @return int - ProductInfo테이블 전체 행 수 조회 결과
	 */
	public int selectCateCntByNutrient(Connection conn, int nutId);
	
	/**
	 * 카테고리 대상별 검색
	 * 
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @param targetId 대상 코드
	 * @return List<ProductInfo> - ProductInfo테이블 카테고리 검색 조회 결과 리스트
	 */
	public List<ProductInfo> selectCateByTarget(Connection conn, Paging paging, int targetId);

	/**
	 * 카테고리 증상별 검색
	 * 
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @param symptomId 증상 코드
	 * @return List<ProductInfo> - ProductInfo테이블 카테고리 검색 조회 결과 리스트
	 */
	public List<ProductInfo> selectCateBySymptom(Connection conn, Paging paging, int symptomId);

	/**
	 * 카테고리 영양소별 검색
	 * 
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @param nutId 영양소 코드
	 * @return List<ProductInfo> - ProductInfo테이블 카테고리 검색 조회 결과 리스트
	 */
	public List<ProductInfo> selectCateByNutrient(Connection conn, Paging paging, int nutId);

	/**
	 * 영양제 검색
	 * 
	 * @param conn DB연결 객체
	 * @param paging 페이징 정보 객체
	 * @param search 영양소 코드
	 * @return List<ProductInfo> - ProductInfo테이블 검색 조회
	 */
	public List<ProductInfo> selectBySearch(Connection conn, Paging paging, String search);

	/**
	 * 증상코드 테이블조회
	 * 
	 * @param conn DB연결 객체
	 * @return List<SymptomCode>
	 */
	public List<SymptomCode> selectAllSymptomCode(Connection conn);

	/**
	 * 영양소 테이블 조회
	 * 
	 * @param conn DB연결 객체
	 * @return List<NutrientKind>
	 */
	public List<NutrientKind> selectAllNutrientKind(Connection conn);

	
	/**
	 * 상품아이디로 상품정보 조회
	 * 
	 * @param conn DB연결 객체
	 * @param productId 
	 * @return ProductInfo 하나의 상품 정보
	 */
	public ProductInfo selectProductInfoByProductId(Connection conn, long productId);

	

	

	public List<Map<String, Object>> selectNutirentInfoWithKind(Connection conn, ProductInfo productList);


	

	

	


}
