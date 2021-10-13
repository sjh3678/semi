package product.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import common.JDBCTemplate;
import dto.product.NutrientInfo;
import dto.product.NutrientKind;
import dto.product.ProductInfo;
import dto.product.SymptomCode;
import product.dao.face.ProductDao;
import util.Paging;

public class ProductDaoImpl implements ProductDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체



	@Override
	public int selectCntAll(Connection conn) {

		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM productinfo";

		//총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int selectCntBySearch(Connection conn, String search) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM productinfo";
		sql += " WHERE productname LIKE '%' || ? || '%'";
		//총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, search);
			rs = ps.executeQuery();

			while(rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int selectCateCntByTarget(Connection conn, int targetId) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM targetinfo";
		if (targetId == 1) {
			sql += " 	WHERE targetid IN ( 1, 4, 6 ) ";
		} else if (targetId == 2) {
			sql += " 	WHERE targetid IN ( 2, 4, 5, 6 ) ";
		} else {
			sql += " 	WHERE targetid IN ( 3, 5, 6 ) ";
		}

		//총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int selectCateCntBySymptom(Connection conn, int symptomId) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM symptominfo";
		sql += " WHERE symptomid = ?";

		//총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, symptomId);

			rs = ps.executeQuery();

			while(rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public int selectCateCntByNutrient(Connection conn, int nutId) {
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM nutrientinfo";
		sql += " WHERE nutid = ?";

		//총 게시글 수
		int count = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, nutId);
			rs = ps.executeQuery();

			while(rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return count;
	}

	@Override
	public List<ProductInfo> selectAll(Connection conn, Paging paging) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM(";
		sql += " 	SELECT rownum rnum, p.* FROM";
		sql += " 		(SELECT * FROM PRODUCTINFO";
		sql += " 		ORDER BY starscore DESC";
		sql += " 		)p";
		sql += " 	)product";
		sql += " WHERE rnum BETWEEN ? AND ?";
		//결과 저장할 List
		List<ProductInfo> productList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				ProductInfo p = new ProductInfo(); //결과값 저장 객체


				//결과값 한 행 처리
				p.setProductId( rs.getLong("productId") );
				p.setProductName( rs.getString("productName") );
				p.setManuCom( rs.getString("manuCom") );
				p.setType( rs.getString("type") );
				p.setImage( rs.getString("image") );
				p.setPurchaseLink( rs.getString("purchaseLink") );
				p.setAllergyInfo( rs.getString("allergyInfo") );
				p.setStarScore( rs.getString("starScore") );

				//리스트에 결과값 저장
				productList.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return productList;
	}

	@Override
	public List<ProductInfo> selectCateByTarget(Connection conn, Paging paging, int targetId) {
		//SQL작성

		String sql = "";
		sql += "SELECT * FROM (";
		sql += " SELECT ROWNUM rnum, p.* FROM";
		sql += " (SELECT * FROM productinfo";
		sql += " WHERE productId IN(";
		sql += " SELECT productid FROM targetinfo";

		if (targetId == 1) {
			sql += " 	WHERE targetid IN ( 1, 4, 6 ) )";
		} else if (targetId == 2) {
			sql += " 	WHERE targetid IN ( 2, 4, 5, 6 ) )";
		} else {
			sql += " 	WHERE targetid IN ( 3, 5, 6 ) )";
		}

		sql += " ORDER BY starscore DESC) p";
		sql += " ) WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 List 객체 생성
		List<ProductInfo> productList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();


			while(rs.next()) {
				ProductInfo p = new ProductInfo(); //결과값 저장 객체

				//결과값 한 행 처리
				p.setProductId( rs.getLong("productId") );
				p.setProductName( rs.getString("productName") );
				p.setManuCom( rs.getString("manuCom") );
				p.setType( rs.getString("type") );
				p.setImage( rs.getString("image") );
				p.setPurchaseLink( rs.getString("purchaseLink") );
				p.setAllergyInfo( rs.getString("allergyInfo") );
				p.setStarScore( rs.getString("starScore") );

				//리스트에 결과값 저장
				productList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return productList;
	}


	@Override
	public List<ProductInfo> selectCateByNutrient(Connection conn, Paging paging, int nutId) {

		//SQL작성

		String sql = "";
		sql += " SELECT * FROM (";
		sql += " SELECT ROWNUM rnum, p.* FROM";
		sql += " (SELECT * FROM productinfo";
		sql += " WHERE productid IN";
		sql += " (SELECT productid FROM nutrientinfo";
		sql += " WHERE nutid = ?)";
		sql += " ORDER BY starscore DESC) p";
		sql += " )WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 List 객체 생성
		List<ProductInfo> productList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, nutId);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();


			while(rs.next()) {
				ProductInfo p = new ProductInfo(); //결과값 저장 객체

				//결과값 한 행 처리
				p.setProductId( rs.getLong("productId") );
				p.setProductName( rs.getString("productName") );
				p.setManuCom( rs.getString("manuCom") );
				p.setType( rs.getString("type") );
				p.setImage( rs.getString("image") );
				p.setPurchaseLink( rs.getString("purchaseLink") );
				p.setAllergyInfo( rs.getString("allergyInfo") );
				p.setStarScore( rs.getString("starScore") );

				//리스트에 결과값 저장
				productList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return productList;
	}



	@Override
	public List<ProductInfo> selectCateBySymptom(Connection conn, Paging paging, int symptomId) {
		//SQL작성

		String sql = "";
		sql += "SELECT * FROM (";
		sql += 		" SELECT ROWNUM rnum, p.* FROM";
		sql += 			" (SELECT * FROM productinfo";
		sql += 			" WHERE productid IN(";
		sql += 				" SELECT productid FROM symptominfo";
		sql += 				" WHERE symptomid = ?)";
		sql += 			" ORDER BY starscore DESC) p";
		sql += " ) WHERE rnum BETWEEN ? AND ?";


		//결과 저장할 List 객체 생성
		List<ProductInfo> productList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, symptomId);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());


			rs = ps.executeQuery();


			while(rs.next()) {
				ProductInfo p = new ProductInfo(); //결과값 저장 객체

				//결과값 한 행 처리
				p.setProductId( rs.getLong("productId") );
				p.setProductName( rs.getString("productName") );
				p.setManuCom( rs.getString("manuCom") );
				p.setType( rs.getString("type") );
				p.setImage( rs.getString("image") );
				p.setPurchaseLink( rs.getString("purchaseLink") );
				p.setAllergyInfo( rs.getString("allergyInfo") );
				p.setStarScore( rs.getString("starScore") );

				//리스트에 결과값 저장
				productList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return productList;
	}

	@Override
	public List<ProductInfo> selectBySearch(Connection conn, Paging paging, String Search) {
		//SQL작성

		String sql = "";

		sql +=	"SELECT * FROM(";
		sql +=	" 	SELECT ROWNUM rnum, p.* FROM";
		sql +=	" 		(SELECT * FROM productinfo";
		sql +=  " 		WHERE productname LIKE '%' || ? || '%'";
		sql +=	" 		ORDER BY starscore DESC) p";
		sql +=	" )WHERE rnum BETWEEN ? AND ?";

		//				sql += "SELECT * FROM productinfo";
		//				sql += " WHERE productname LIKE '%";
		//				sql += Search;
		//				sql += "%'";

		//결과 저장할 List 객체 생성
		List<ProductInfo> productList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, Search);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery();


			while(rs.next()) {
				ProductInfo p = new ProductInfo(); //결과값 저장 객체

				//결과값 한 행 처리
				p.setProductId( rs.getLong("productId") );
				p.setProductName( rs.getString("productName") );
				p.setManuCom( rs.getString("manuCom") );
				p.setType( rs.getString("type") );
				p.setImage( rs.getString("image") );
				p.setPurchaseLink( rs.getString("purchaseLink") );
				p.setAllergyInfo( rs.getString("allergyInfo") );
				p.setStarScore( rs.getString("starScore") );

				//리스트에 결과값 저장
				productList.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return productList;
	}

	@Override
	public List<SymptomCode> selectAllSymptomCode(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM SYMPTOMCODE";
		sql += " ORDER BY symptomId ASC";

		//결과 저장할 List
		List<SymptomCode> symptomCode = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				SymptomCode s = new SymptomCode(); //결과값 저장 객체

				//결과값 한 행 처리
				s.setSymptomId( rs.getInt("symptomId") );
				s.setSymptomName(rs.getString("symptomName") );

				//리스트에 결과값 저장
				symptomCode.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return symptomCode;
	}

	@Override
	public List<NutrientKind> selectAllNutrientKind(Connection conn) {
		//SQL 작성
		String sql = "";
		sql += "SELECT nutId, nutKind FROM NUTRIENTKIND";
		sql += " ORDER BY nutId ASC";

		//결과 저장할 List
		List<NutrientKind> nutrientKind = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				NutrientKind n = new NutrientKind(); //결과값 저장 객체

				//결과값 한 행 처리
				n.setNutId( rs.getInt("nutId") );
				n.setNutKind(rs.getString("nutKind") );

				//리스트에 결과값 저장
				nutrientKind.add(n);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		return nutrientKind;
	}

	@Override
	public ProductInfo selectProductInfoByProductId(Connection conn, long productId) {
		//SQL

		String sql = "";
		sql += "SELECT * FROM productinfo";
		sql += " WHERE productid = ?";


		ProductInfo productInfo = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, productId);

			rs = ps.executeQuery();
			rs.next();

			productInfo = new ProductInfo(); //결과값 저장 객체

			productInfo.setProductId( rs.getLong("productId") );
			productInfo.setProductName( rs.getString("productName") );
			productInfo.setManuCom( rs.getString("manuCom") );
			productInfo.setType( rs.getString("type") );
			productInfo.setImage( rs.getString("image") );
			productInfo.setPurchaseLink( rs.getString("purchaseLink") );
			productInfo.setAllergyInfo( rs.getString("allergyInfo") );
			productInfo.setStarScore( rs.getString("starScore") );

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}


		return productInfo;
	}





	@Override
	public List<Map<String, Object>> selectNutirentInfoWithKind(Connection conn, ProductInfo productList) {
		
		String sql = "";
		sql += "SELECT * FROM nutrientinfo NI, nutrientkind NK";
		sql += " WHERE NI.nutid = NK.nutid";
		sql += "	AND productid = ?";
		
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, productList.getProductId());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				map = new HashMap<>();
				
				NutrientInfo ni = new NutrientInfo();
				NutrientKind nk = new NutrientKind();
				
				ni.setProductId( rs.getLong("productid") );
				ni.setNutId( rs.getInt("nutid") );
				ni.setNutContent( rs.getString("nutcontent") );
				
				nk.setNutId( rs.getInt("nutid") );
				nk.setNutKind( rs.getString("nutkind") );
				nk.setEffect(rs.getString("effect"));
				nk.setDeficiency( rs.getString("deficiency"));
				nk.setHyperact(rs.getString("hyperact"));
				nk.setRcmYth(rs.getString("rcmYth"));
				nk.setRcmAdult(rs.getString("rcmAdult"));
				nk.setRcmSen(rs.getString("rcmSen"));
				
				
				map.put("ni", ni);
				map.put("nk", nk);
				
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return list;
	}

	@Override
	public boolean selectCntMemberByUseridUserpw(Connection connection, long productId, int userNo) {
		// TODO Auto-generated method stub
		return false;
	}

}
