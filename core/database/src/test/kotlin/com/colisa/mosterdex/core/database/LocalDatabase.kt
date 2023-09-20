package com.colisa.mosterdex.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.squareup.moshi.Moshi
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 24)
abstract class LocalDatabase {

    lateinit var db: MosterdexDatabase

    @Before
    fun initDb() {
        val moshi = Moshi.Builder().build()
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), MosterdexDatabase::class.java)
            .allowMainThreadQueries()
            .addTypeConverter(TypeResponseConverter(moshi))
            .build()
    }

    @After
    fun closeDb() {
        db.close()
    }
}