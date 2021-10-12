package dto.member;

import java.util.Date;

public class Member {
	private int userNo;
	private int userKind;
	private String userId;
	private String userPw;
	private String userName;
	private String userNick;
	private int userPh;
	private String userEm;
	private String userGen;
	private Date userBirth;
	private Date joinDate;
	private int businessNo;
	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userKind=" + userKind + ", userId=" + userId + ", userPw=" + userPw
				+ ", userName=" + userName + ", userNick=" + userNick + ", userPh=" + userPh + ", userEm=" + userEm
				+ ", userGen=" + userGen + ", userBirth=" + userBirth + ", joinDate=" + joinDate + ", businessNo="
				+ businessNo + "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getUserKind() {
		return userKind;
	}
	public void setUserKind(int userKind) {
		this.userKind = userKind;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public int getUserPh() {
		return userPh;
	}
	public void setUserPh(int userPh) {
		this.userPh = userPh;
	}
	public String getUserEm() {
		return userEm;
	}
	public void setUserEm(String userEm) {
		this.userEm = userEm;
	}
	public String getUserGen() {
		return userGen;
	}
	public void setUserGen(String userGen) {
		this.userGen = userGen;
	}
	public Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public int getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(int businessNo) {
		this.businessNo = businessNo;
	}
	
	
}
