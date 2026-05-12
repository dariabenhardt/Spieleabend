package com.example.boardgamerapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.boardgamerapp.model.*;

import com.example.boardgamerapp.model.Abend;

import java.util.concurrent.Executors;

@Database(
        entities = {
                Spieler.class,
                Abend.class,
                Spiel.class,
                SpielVoting.class,
                AbendBewertung.class
        },
        version = 4,
        exportSchema = false
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
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration() // ← DB wird bei Änderungen neu erstellt
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> {

                                // 1. Spieler Testdaten
                                SpielerDao spielerDao = instance.spielerDao();
                                long id1 = spielerDao.insert(new Spieler("Max", 1, "Hauptstraße 5"));
                                long id2 = spielerDao.insert(new Spieler("Lisa", 2, "Goethestraße 17"));
                                long id3 = spielerDao.insert(new Spieler("Tom", 3, "Frankfurter Straße 57"));

                                // 2. Abende
                                AbendDao abendDao = instance.abendDao();
                                //Vergangene Abende
                                abendDao.insert(new Abend("18:00", "2025-05-10", (int) id1));
                                abendDao.insert(new Abend("19:00", "2025-06-15", (int) id2));
                                abendDao.insert(new Abend("20:00", "2025-07-20", (int) id3));
                                abendDao.insert(new Abend("20:00", "2026-03-20", (int) id1));
                                //Zukünftige Abende
                                abendDao.insert(new Abend("20:00", "2026-06-10", (int) id3));
                                abendDao.insert(new Abend("18:30", "2026-07-15", (int) id1));
                                abendDao.insert(new Abend("18:30", "2026-06-01", (int) id2));

                                // 3. Spiele
                                SpielDao spielDao = instance.spielDao();
                                spielDao.insert(new Spiel("Exploding Kittens", (int) id1));
                                spielDao.insert(new Spiel("Azul", (int) id2));
                                spielDao.insert(new Spiel("Cards Against Humanity", (int) id3));
                                spielDao.insert(new Spiel("Dixit", (int) id3));
                                spielDao.insert(new Spiel("Werwolf", (int) id2));
                            });
                        }
                    })
                    .build();
        }
        return instance;
    }
}
