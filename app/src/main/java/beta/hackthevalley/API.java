package beta.hackthevalley;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * @author Stanley C
 * @since 2/11/18.
 */

public class API {

	private static final String URL = "http://192.168.43.208:8181";

	interface APIService {
		@GET("/core/users")
		Call<List<User>> getUsers();

		@GET("/core/posts")
		Call<List<Post>> getPosts();

		@GET("/core/businesses")
		Call<List<Business>> getBusinesses();
	}

	public static Call<List<User>> getUsers() {
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(URL)
				.build();

		APIService service = retrofit.create(APIService.class);
		return service.getUsers();
	}

	public static Call<List<Post>> getPosts() {
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(URL)
				.build();

		APIService service = retrofit.create(APIService.class);
		return service.getPosts();
	}

	public static Call<List<Business>> getBusinesses() {
		Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(URL)
				.build();

		APIService service = retrofit.create(APIService.class);
		return service.getBusinesses();
	}
}
