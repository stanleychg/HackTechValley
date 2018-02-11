package beta.hackthevalley;

/**
 * @author Stanley C
 * @since 2/11/18.
 */

public class FormattedPost extends Post {

	private String name;
	public String getName() {
		return name;
	}

	public FormattedPost(Post p, String name) {
		super(p);
		this.name = name;
	}
}
