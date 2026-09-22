package com.kawach.cloud.domain.model

import java.time.Instant

sealed interface CloudItem {
    val id: String
    val name: String
    val parentId: String?
    val createdAt: Instant
    val updatedAt: Instant
}

data class CloudFile(
    override val id: String,
    override val name: String,
    override val parentId: String?,
    val mimeType: String,
    val sizeBytes: Long,
    val remoteId: String? = null,
    val uploadState: TransferState = TransferState.PENDING,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : CloudItem

data class CloudFolder(
    override val id: String,
    override val name: String,
    override val parentId: String?,
    override val createdAt: Instant,
    override val updatedAt: Instant
) : CloudItem

enum class TransferState {
    PENDING,
    UPLOADING,
    AVAILABLE,
    DOWNLOADING,
    FAILED
}

enum class FileSortOrder {
    NAME,
    DATE,
    SIZE,
    TYPE
}
