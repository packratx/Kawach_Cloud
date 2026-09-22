package com.kawach.cloud.domain.model

import java.time.Instant

object FileNameValidator {
    private const val MAX_LENGTH = 255
    private val forbiddenCharacters = Regex("[\\u0000-\\u001F\\\\/:*?\"<>|]")

    fun validate(name: String, existingNames: Set<String> = emptySet()): Result<Unit> {
        val normalized = name.trim()
        return when {
            normalized.isEmpty() -> Result.failure(IllegalArgumentException("Name cannot be empty"))
            normalized.length > MAX_LENGTH -> Result.failure(IllegalArgumentException("Name is too long"))
            forbiddenCharacters.containsMatchIn(normalized) -> Result.failure(IllegalArgumentException("Name contains unsupported characters"))
            normalized == "." || normalized == ".." -> Result.failure(IllegalArgumentException("Name is reserved"))
            existingNames.any { it.equals(normalized, ignoreCase = true) } -> Result.failure(IllegalArgumentException("An item with this name already exists"))
            else -> Result.success(Unit)
        }
    }

    fun newFolder(name: String, parentId: String? = null): CloudFolder {
        validate(name).getOrThrow()
        val now = Instant.now()
        return CloudFolder(
            id = java.util.UUID.randomUUID().toString(),
            name = name.trim(),
            parentId = parentId,
            createdAt = now,
            updatedAt = now
        )
    }
}
