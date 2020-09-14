package model;

public class Action {
	// ActionDAOと繫がる
	// 日付
	private String day;
	// 開始時間
	private String starttime;
	// 終了時間
	private String finishtime;
	// 場所
	private String place;
	// 理由
	private String reason;
	// 備考
	private String remark;
	
	//userid 外部制約
	private String userid;
	private String name;

	public Action() {
		super();
	}

	// setterとgetter
	// day
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	// Starttime
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	// Finishtime
	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	// Place
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	// Reason
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	// Remark
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
