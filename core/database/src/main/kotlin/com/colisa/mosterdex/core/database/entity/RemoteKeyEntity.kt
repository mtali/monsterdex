package com.colisa.mosterdex.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *  This class is designed to store information related to pagination,
 *  specifically focusing on managing the next offset value.
 *  It is intended for use in endpoints that rely on the 'offset' parameter for data retrieval
 */
@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "next_offset") val nextOffset: Int
)
