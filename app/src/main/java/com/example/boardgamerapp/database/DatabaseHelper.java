package com.example.boardgamerapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {

    // Datenbankname und Version
    private static final String DATABASE_NAME = "boardgamer.db";
    private static final int DATABASE_VERSION = 1;

    // Tabellen Strings

    private static final String CREATE_TABLE_SPIELER =
            "CREATE TABLE Spieler (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "turnusPosition INTEGER)";

    private static final String CREATE_TABLE_ABEND =
            "CREATE TABLE Abend (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "zeit TEXT, " +
                    "datum TEXT, " +
                    "gastgeberId INTEGER, " +
                    "FOREIGN KEY(gastgeberId) REFERENCES Spieler(id))";

    private static final String CREATE_TABLE_SPIEL =
            "CREATE TABLE Spiel (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "vorschlagVon INTEGER, " +
                    "FOREIGN KEY(vorschlagVon) REFERENCES Spieler(id))";

    private static final String CREATE_TABLE_SPIELVOTING =
            "CREATE TABLE SpielVoting (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "spielerId INTEGER, " +
                    "spielId INTEGER, " +
                    "abendId INTEGER, " +
                    "FOREIGN KEY(spielerId) REFERENCES Spieler(id), " +
                    "FOREIGN KEY(spielId) REFERENCES Spiel(id), " +
                    "FOREIGN KEY(abendId) REFERENCES Abend(id))";

    private static final String CREATE_TABLE_ABENDBEWERTUNG =
            "CREATE TABLE AbendBewertung (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "abendId INTEGER, " +
                    "spielerId INTEGER, " +
                    "essenSterne INTEGER, " +
                    "abendSterne INTEGER, " +
                    "gastgeberSterne INTEGER, " +
                    "FOREIGN KEY(abendId) REFERENCES Abend(id), " +
                    "FOREIGN KEY(spielerId) REFERENCES Spieler(id))";

    // Konstruktor
    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Erstellen
    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SPIELER);
        db.execSQL(CREATE_TABLE_ABEND);
        db.execSQL(CREATE_TABLE_SPIEL);
        db.execSQL(CREATE_TABLE_SPIELVOTING);
        db.execSQL(CREATE_TABLE_ABENDBEWERTUNG);
    }

    // Update der Datenbank
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Spieler");
        db.execSQL("DROP TABLE IF EXISTS Abend");
        db.execSQL("DROP TABLE IF EXISTS Spiel");
        db.execSQL("DROP TABLE IF EXISTS SpielVoting");
        db.execSQL("DROP TABLE IF EXISTS AbendBewertung");
        onCreate(db);
    }
}
