package wang.mycroft.disney.`data`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FavoriteCharacterQueries {

    private val _favoriteCharacters = MutableStateFlow(emptyList<FavoriteCharacter>())

    fun selectAll(): Flow<List<FavoriteCharacter>> = _favoriteCharacters.asStateFlow()

    fun selectById(characterId: Long): Flow<FavoriteCharacter?> =
        _favoriteCharacters.map { items ->
            items.firstOrNull { it.characterId == characterId }
        }

    fun insert(
        characterId: Long,
        favorite: Long,
        createdAt: String,
    ) {
        _favoriteCharacters.update { items ->
            items.filterNot { it.characterId == characterId } + FavoriteCharacter(
                characterId,
                favorite,
                createdAt
            )
        }
    }

    fun delete(characterId: Long) {
        _favoriteCharacters.update { items ->
            items.filterNot { it.characterId == characterId }
        }
    }
}
