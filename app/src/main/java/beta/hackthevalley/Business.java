package beta.hackthevalley;

import com.google.gson.annotations.SerializedName;

/**
 * @author Stanley C
 * @since 2/11/18.
 */

public class Business {
	@SerializedName("user_id")
	private String id;
	public String getId() {
		return id;
	}

	@SerializedName("business_name")
	private String businessName;
	public String getBusinessName() {
		return businessName;
	}

	@SerializedName("business_city")
	private String city;

	@SerializedName("business_state")
	private String state;
}
