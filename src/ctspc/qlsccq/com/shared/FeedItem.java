package ctspc.qlsccq.com.shared;

import java.io.Serializable;

public class FeedItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name, status, image, profilePic, timeStamp, url;

	public FeedItem() {
	}

	public FeedItem(int id, String name, String image, String status,
			String profilePic, String timeStamp, String url) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.status = status;
		this.profilePic = profilePic;
		this.timeStamp = timeStamp;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImge() {
		return image;
	}

	public void setImge(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public static final String tag_TABLE="FEEDITEM";
	
	public static final String tag_id = "id";
	public static final String tag_name = "name";
	public static final String tag_image = "image";
	public static final String tag_status = "status";
	public static final String tag_profilePic = "profilePic";
	public static final String tag_timeStamp = "timeStamp";
	public static final String tag_url = "url";
}
