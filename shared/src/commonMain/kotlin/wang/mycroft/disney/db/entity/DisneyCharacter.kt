package wang.mycroft.disney.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.Long
import kotlin.String

@Entity(tableName = "t_disney_character")
data class DisneyCharacter(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val createdAt: String,
    val imageUrl: String?,
    val name: String,
    val url: String?,
)
