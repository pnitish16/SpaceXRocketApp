package com.spacex.app.data.db.dao

import androidx.room.*
import com.spacex.app.data.db.entities.DbRocket
import com.spacex.app.data.db.entities.DbRocketDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {

    @Transaction
    suspend fun updateRocketDetails(rocketsDetails: DbRocketDetails) {
        clearRocketDetails()
        insertRocketDetails(rocketsDetails)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRocketDetails(rocketsDetails: DbRocketDetails)

    @Transaction
    suspend fun updateRockets(rockets: List<DbRocket>) {
        clearRockets()
        insertAllRockets(rockets)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRockets(rockets: List<DbRocket>)

    @Query("DELETE FROM rocket_details_table")
    suspend fun clearRocketDetails()

    @Query("DELETE FROM rockets_table")
    suspend fun clearRockets()

    @Query("SELECT * FROM rocket_details_table")
    suspend fun getRocketDetails(): DbRocketDetails?

    @Query("SELECT * FROM rocket_details_table WHERE id LIKE :rocketId")
    fun getRocketDetail(rocketId: String): Flow<DbRocketDetails?>

    @Query("SELECT * FROM rockets_table")
    fun getRockets(): Flow<List<DbRocket>>
}