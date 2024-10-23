package wang.mycroft.disney.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import wang.mycroft.disney.db.entity.FavoriteCharacter

@Dao
interface FavoriteCharacterDao {
    @Insert
    suspend fun insert(favoriteCharacter: FavoriteCharacter)

    @Insert
    suspend fun insertAll(favoriteCharacters: List<FavoriteCharacter>)

    @Query("SELECT * FROM t_favorite_character")
    fun selectAll(): Flow<List<FavoriteCharacter>>

    @Query("SELECT * FROM t_favorite_character WHERE characterId = :characterId")
    fun selectById(characterId: Long): Flow<FavoriteCharacter?>

    @Query("DELETE FROM t_favorite_character WHERE characterId = :characterId")
    suspend fun deleteById(characterId: Long)

    @Query("DELETE FROM t_favorite_character")
    suspend fun deleteAll()
}