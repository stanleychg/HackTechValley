package beta.hackthevalley;

import com.google.gson.annotations.SerializedName;
/**
 * @author Stanley C
 * @since 2/11/18.
 */

public class User {
	@SerializedName("user_id")
	private String id;
	public String getId() {
		return id;
	}

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("last_name")
	private String lastName;

	public String getName() {
		return String.format("%s %s", firstName, lastName);
	}
}
