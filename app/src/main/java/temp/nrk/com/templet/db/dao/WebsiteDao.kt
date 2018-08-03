package temp.nrk.com.templet.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import temp.nrk.com.templet.db.entity.WebsiteEntity

@Dao
interface WebsiteDao {

    @get:Query("SELECT * from website_table ORDER BY idWebsite ASC")
    val allWebsitesAsList: Maybe<List<WebsiteEntity>>

    @get:Query("SELECT * from website_table")
    val allWebsites: LiveData<List<WebsiteEntity>>

    @get:Query("SELECT * from website_table ORDER BY totalVisits DESC limit 5")
    val top5Websites: LiveData<List<WebsiteEntity>>

    @Insert(onConflict = REPLACE)
    fun insert(websiteEntity: WebsiteEntity)

    @Query("DELETE FROM website_table")
    fun deleteAll()

}