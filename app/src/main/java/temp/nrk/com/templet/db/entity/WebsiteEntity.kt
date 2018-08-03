package temp.nrk.com.templet.db.entity


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "website_table")
class WebsiteEntity(
        @field:ColumnInfo(name = "idWebsite")
        var idWebsite: String, @field:ColumnInfo(name = "websiteName")
        var websiteName: String, @field:ColumnInfo(name = "visitDate")
        var visitDate: String, @field:ColumnInfo(name = "totalVisits")
        var totalVisits: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
