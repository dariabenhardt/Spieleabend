package com.example.boardgamerapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.boardgamerapp.model.*;

import com.example.boardgamerapp.model.Abend;

@Database(
        entities = {
                Spieler.class,
                Abend.class,
                Spiel.class,
                SpielVoting.class,
                AbendBewertung.class
        },
                version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    //DAOs
    public abstract SpielerDao spielerDao();
    public abstract AbendDao abendDao();
    public abstract SpielDao spielDao();
    public abstract SpielVotingDao spielVotingDao();
    public abstract AbendBewertungDao abendBewertungDao();

    // Singleton
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "boardgamer.db"
                    )
                    .allowMainThreadQueries() // ← nur zum Einstieg, später entfernen!
                    .build();
        }
        return instance;
    }
}
