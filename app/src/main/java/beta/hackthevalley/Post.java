package beta.hackthevalley;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Stanley C
 * @since 2/11/18.
 */

public class Post {
	@SerializedName("user_id")
	private String id;
	public String getId() {
		return id;
	}

	@SerializedName("description")
	private String desc;
	public String getDesc() {
		return desc;
	}

	@SerializedName("pub_date")
	private Date pubDate;

	@SerializedName("event_date")
	private Date eventDate;
	public Date getEventDate() {
		return eventDate;
	}

	@SerializedName("event_city")
	private String city;
	public String getCity() {
		return city;
	}

	@SerializedName("event_state")
	private String state;
	public String getState() {
		return state;
	}

	public Post(Post p) {
		this.desc = p.desc;
		this.id = p.id;
		this.pubDate = p.pubDate;
		this.eventDate = p.eventDate;
		this.city = p.city;
		this.state = p.state;
	}
}
