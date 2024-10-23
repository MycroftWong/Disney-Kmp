package wang.mycroft.disney.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.Long
import kotlin.String

@Entity(tableName = "t_favorite_character")
data class FavoriteCharacter(
    @PrimaryKey(autoGenerate = false)
    val characterId: Long,
    val favorite: Long,
    val createdAt: String,
)
