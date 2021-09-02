package id.interconnect.gamestar.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.interconnect.gamestar.core.data.source.local.entity.GameItemEntity

@Database(entities = [GameItemEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}