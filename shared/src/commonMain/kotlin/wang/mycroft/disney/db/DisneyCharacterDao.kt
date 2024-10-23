package wang.mycroft.disney.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import wang.mycroft.disney.db.entity.DisneyCharacter

@Dao
interface DisneyCharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: DisneyCharacter)

    @Insert
    suspend fun insertAll(characters: List<DisneyCharacter>)

    @Query("SELECT * FROM t_disney_character")
    fun selectAll(): Flow<List<DisneyCharacter>>

    @Query("SELECT * FROM t_disney_character WHERE id = :id")
    fun selectById(id: Long): Flow<DisneyCharacter?>

    @Delete
    suspend fun deleteAll(characters: List<DisneyCharacter>)
}