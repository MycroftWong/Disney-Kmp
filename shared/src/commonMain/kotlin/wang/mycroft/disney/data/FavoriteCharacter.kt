package wang.mycroft.disney.`data`

import kotlin.Long
import kotlin.String

public data class FavoriteCharacter(
  public val characterId: Long,
  public val favorite: Long,
  public val createdAt: String,
)
