package wang.mycroft.disney.ui.disney

import wang.mycroft.disney.db.entity.DisneyCharacter


data class DisneyCharacterItem(
    val character: DisneyCharacter,
    val favorite: Boolean = false,
)