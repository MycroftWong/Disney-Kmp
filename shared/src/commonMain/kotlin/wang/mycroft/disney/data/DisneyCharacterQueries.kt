package wang.mycroft.disney.`data`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class DisneyCharacterQueries {

    private val _characters = MutableStateFlow(emptyList<DisneyCharacter>())

    fun selectAll(): Flow<List<DisneyCharacter>> = _characters.asStateFlow()

    fun selectById(id: Long): Flow<DisneyCharacter?> = _characters.map { items ->
        items.firstOrNull { it.id == id }
    }

    fun insert(
        id: Long,
        createdAt: String,
        imageUrl: String?,
        name: String,
        url: String?,
    ) {
        _characters.update { items ->
            items.filterNot { it.id == id } + DisneyCharacter(id, createdAt, imageUrl, name, url)
        }
    }

    fun insertItem(disneyCharacter: DisneyCharacter) {
        _characters.update { items ->
            items.filterNot { it.id == disneyCharacter.id } + disneyCharacter
        }
    }

}
