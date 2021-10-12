package dto.product;

public class CompBasket {
	private int productId;
	private int userNo;
	@Override
	public String toString() {
		return "CompBasket [productId=" + productId + ", userNo=" + userNo + "]";
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
}
