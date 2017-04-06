package model;

public class ReviewVO {
	private int reviewNo;
	private int grade;
	private String review;
	private String mid;
	private String timePosted;
	public ReviewVO() {
		super();
	}
	public ReviewVO(int reviewNo, int grade, String review, String mid, String timePosted) {
		super();
		this.reviewNo = reviewNo;
		this.grade = grade;
		this.review = review;
		this.mid = mid;
		this.timePosted = timePosted;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	@Override
	public String toString() {
		return "ReviewVO [reviewNo=" + reviewNo + ", grade=" + grade + ", review=" + review + ", mid=" + mid
				+ ", timePosted=" + timePosted + "]";
	}
}