package product.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.product.NutrientInfo;
import dto.product.NutrientKind;
import dto.product.ProductInfo;
import dto.product.SymptomCode;
import product.dao.face.ProductDao;
import product.dao.impl.ProductDaoImpl;
import product.service.face.ProductService;
import util.Paging;
import common.JDBCTemplate;

public class ProductServiceImpl implements ProductService {

	//Dao 객체 생성
	private ProductDao productDao = new ProductDaoImpl();

	@Override
	public List<ProductInfo> getAllProduct(Paging paging) {

		//상품 전체 조회 결과 처리 - 페이징 추가
		return productDao.selectAll(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		
		
		//전달파라미터 curPage 파싱
		String paramCurPage = req.getParameter("curPage");
		String paramSearch = req.getParameter("search");
		String paramTargetId = req.getParameter("targetId");
		String paramSymptomId = req.getParameter("symptomId");
		String paramNutId = req.getParameter("nutId");
		
		int curPage = 0;
		String Search = null;
		int TargetId = 0;
		int SymptomId = 0;
		int NutId = 0;
		int totalCount = 0;
		if(paramSearch != null && !"".equals(paramSearch)) {
			Search = paramSearch;
			System.out.println("검색어 입력");
			totalCount = productDao.selectCntBySearch(JDBCTemplate.getConnection(),Search);	
		} else if(paramTargetId != null && !"".equals(paramTargetId)) {
			TargetId = Integer.parseInt(paramTargetId);
			totalCount = productDao.selectCateCntByTarget(JDBCTemplate.getConnection(), TargetId);
		} else if (paramSymptomId != null && !"".equals(paramSymptomId)) {
			SymptomId = Integer.parseInt(paramSymptomId);
			totalCount = productDao.selectCateCntBySymptom(JDBCTemplate.getConnection(), SymptomId);
		} else if(paramNutId != null && !"".equals(paramNutId)) {
			NutId = Integer.parseInt(paramNutId);
			totalCount = productDao.selectCateCntByNutrient(JDBCTemplate.getConnection(), NutId);
		} else {
			totalCount = productDao.selectCntAll(JDBCTemplate.getConnection());
		}
		
			
		if(paramCurPage != null && !"".equals(paramCurPage)) {
			curPage = Integer.parseInt(paramCurPage);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}

		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}

	@Override
	public List<ProductInfo> getProduct(HttpServletRequest req, Paging paging) {
		
		String paramSearch = req.getParameter("search");
		String paramTargetId = req.getParameter("targetId");
		String paramSymptomId = req.getParameter("symptomId");
		String paramNutId = req.getParameter("nutId");
		
		
		String Search = null;
		int TargetId = 0;
		int SymptomId = 0;
		int NutId = 0;
		if(paramSearch != null && !"".equals(paramSearch)) {
			Search = paramSearch;
			System.out.println("검색어 입력");
			return productDao.selectBySearch(JDBCTemplate.getConnection(), paging, Search);
		} else if(paramTargetId != null && !"".equals(paramTargetId)) {
			TargetId = Integer.parseInt(paramTargetId);
			return productDao.selectCateByTarget(JDBCTemplate.getConnection(), paging, TargetId);
		} else if (paramSymptomId != null && !"".equals(paramSymptomId)) {
			SymptomId = Integer.parseInt(paramSymptomId);
			return productDao.selectCateBySymptom(JDBCTemplate.getConnection(), paging, SymptomId);
		} else if(paramNutId != null && !"".equals(paramNutId)) {
			NutId = Integer.parseInt(paramNutId);
			return productDao.selectCateByNutrient(JDBCTemplate.getConnection(), paging, NutId);
		} else {
			System.out.println("카테고리 선택 안함");
		}

		return productDao.selectAll(JDBCTemplate.getConnection(), paging);
	}

	@Override
	public List<SymptomCode> getSymptomCode() {
		
		return productDao.selectAllSymptomCode(JDBCTemplate.getConnection());
	}

	@Override
	public List<NutrientKind> getNutrientKind() {
		return productDao.selectAllNutrientKind(JDBCTemplate.getConnection());
	}

	@Override
	public ProductInfo getProductInfoByProductId(HttpServletRequest req) {
		
		String paramProductId = req.getParameter("productId");
		long productId = Long.parseLong(paramProductId);
		return productDao.selectProductInfoByProductId(JDBCTemplate.getConnection(), productId);
	}





	@Override
	public List<Map<String, Object>> getNutirentInfoWithKind(ProductInfo productList) {
		return productDao.selectNutirentInfoWithKind(JDBCTemplate.getConnection(), productList);
	}

}
