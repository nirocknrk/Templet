package temp.nrk.com.templet.api


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import temp.nrk.com.templet.model.Website

interface Api {

    @GET("/all_data.php")
    fun getAllData(): Observable<List<Website>>

    @GET("/filter.php/{email}")
    fun getAllData(@Query("start") startDate: String, @Query("end") endDate: String): Observable<List<Website>>

}
