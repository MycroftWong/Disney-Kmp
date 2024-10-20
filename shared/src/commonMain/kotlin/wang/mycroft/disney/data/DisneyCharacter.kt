package wang.mycroft.disney.`data`

import kotlin.Long
import kotlin.String

public data class DisneyCharacter(
  public val id: Long,
  public val createdAt: String,
  public val imageUrl: String?,
  public val name: String,
  public val url: String?,
)
