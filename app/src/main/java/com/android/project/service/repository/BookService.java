
import com.android.project.service.response.ConfigResponse;

import io.reactivex.Observable;
import retrofit2.Response;


interface BookService {

    @get:GET("/v1/book/mobile-app/configure")
    val configuration: Observable<Response<ConfigResponse>>

}
