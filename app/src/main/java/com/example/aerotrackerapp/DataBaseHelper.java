package com.example.aerotrackerapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "aerotracker.db";
    private static final int DB_VERSION = 1; //version
    static final String TABLE_NAME_ATHLETE = "athlete"; //table name
    private static final String ID_COL_athlete = "athlete_id"; //column names
    private static final String NAME_COL_athlete = "name"; //variable athlete name column
    private static final String SURNAME_COL_athlete = "surname";
    private static final String AGE_COL_athlete = "dateOfBirth";
    private static final String PASSWORD_COL_athlete = "password";
    private static final String TABLE_NAME_COACH = "coach";
    private static final String ID_COL_coach = "id";
    private static final String NAME_COL_coach = "name";
    private static final String SURNAME_COL_coach = "surname";
    private static final String TYPE_COL_coach = "trainingType";
    private static final String PASSWORD_COL_coach = "password";
    private static final String TABLE_NAME_RESULTS = "results";
    private static final String ID_COL_results = "results_id";
    private static final String EFFICIENCY_COL = "efficiency";
    private static final String INTENSITY_COL = "intensity";
    private static final String ATHLETE_COL_fk = "athlete_id";
    private static final String CATEGORY_COL_fk = "category_id";
    private static final String PERIOD_COL_fk = "period_id";
    private static final String HOURS_COL_fk = "training_id";
    private static final String PHYSICAL_COL_fk = "physical_id";
    private static final String TABLE_TRAINING = "training_info";
    private static final String ID_COL_training = "training_id";
    private static final String WarmUp_COL_time_start = "Warm_up_start";
    private static final String WarmUp_COL_time_end = "Warm_up_end";
    private static final String MainPart_COL_time_start = "Main_part_start";
    private static final String MainPart_COL_time_end = "Main_part_end";
    private static final String TABLE_CATEGORY = "category";
    private static final String ID_COL_category = "category_id";
    private static final String CATEGORY_COL = "categoryType";
    private static final String TABLE_PERIOD = "training_period";
    private static final String ID_COL_period = "period_id";
    private static final String PERIOD_COL = "period";
    private static final String TABLE_PHYSICAL_PREPARATION = "physical_preparation";
    private static final String ID_COL_physical = "preparation_id";
    private static final String PREPARATORY_COL = "preparatory_preparation";
    private static final String PRECOMPETITIVE_COL = "precompetitive_preparation";
    private static final String COMPETITIVE_COL = "competitive_preparation";
    private static final String POSTCOMPETITIVE_COL = "postcompetitive_preparation";

    private static final String queryAthlete = "CREATE TABLE " + TABLE_NAME_ATHLETE + " ("
            + ID_COL_athlete + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_COL_athlete + " TEXT,"
            + SURNAME_COL_athlete + " TEXT,"
            + AGE_COL_athlete + " TEXT,"
            + PASSWORD_COL_athlete + " TEXT)";
    private static final String SQL_CREATE_RESULTS = " CREATE TABLE " + TABLE_NAME_RESULTS
            + " ( " + ID_COL_results + " INTEGER PRIMARY KEY, " + EFFICIENCY_COL + " TEXT,"
            + INTENSITY_COL + " TEXT, " + ATHLETE_COL_fk + " INTEGER, " + CATEGORY_COL_fk + " INTEGER, " + PERIOD_COL_fk + " INTEGER, " + HOURS_COL_fk + " INTEGER," + PHYSICAL_COL_fk + " INTEGER,"
            + " FOREIGN KEY ( " + ATHLETE_COL_fk + " ) REFERENCES " + TABLE_NAME_ATHLETE + " ( "
            + ID_COL_athlete + " ), "
            + " FOREIGN KEY ( " + PERIOD_COL_fk + " ) REFERENCES " + TABLE_PERIOD + " ( "
            + ID_COL_period + " ), "
            + " FOREIGN KEY ( " + CATEGORY_COL_fk + " ) REFERENCES " + TABLE_CATEGORY + " ( "
            + ID_COL_category + " ),"
            + " FOREIGN KEY ( " + PHYSICAL_COL_fk + " ) REFERENCES " + TABLE_PHYSICAL_PREPARATION + " ( "
            + ID_COL_physical + " )," +
            " FOREIGN KEY ( " + HOURS_COL_fk + ") REFERENCES " + TABLE_TRAINING + " ( "
            + ID_COL_training + ")); ";
    private static final String queryCoach = "CREATE TABLE " + TABLE_NAME_COACH + " ("
            + ID_COL_coach + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME_COL_coach + " TEXT,"
            + SURNAME_COL_coach + " TEXT,"
            + TYPE_COL_coach + " TEXT,"
            + PASSWORD_COL_coach + " TEXT)";
    private static final String queryCategory = "CREATE TABLE " + TABLE_CATEGORY + " ("
            + ID_COL_category + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CATEGORY_COL + " TEXT )";

    private static final String queryPeriod = "CREATE TABLE " + TABLE_PERIOD + " ("
            + ID_COL_period + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PERIOD_COL + " TEXT )";
    private static final String queryTraining = " CREATE TABLE " + TABLE_TRAINING
            + " ( " + ID_COL_training + " INTEGER PRIMARY KEY, " + WarmUp_COL_time_start + " TEXT,"
            + WarmUp_COL_time_end + " TEXT, " + MainPart_COL_time_start + " TEXT, " + MainPart_COL_time_end + " TEXT, " + ATHLETE_COL_fk + " INTEGER, "
            + " FOREIGN KEY ( " + ATHLETE_COL_fk + " ) REFERENCES " + TABLE_NAME_ATHLETE + " ( "
            + ID_COL_athlete + " )); ";

    private static final String queryPhysicalPrep = " CREATE TABLE " + TABLE_PHYSICAL_PREPARATION
            + " ( " + ID_COL_physical + " INTEGER PRIMARY KEY, " + PREPARATORY_COL + " TEXT,"
            + PRECOMPETITIVE_COL + " TEXT, " + COMPETITIVE_COL + " TEXT, " + POSTCOMPETITIVE_COL + " TEXT)";


    private static final String querySync = "CREATE TABLE " + DbSync.TABLE_SERVER_SYNC + " ("
            + DbSync.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DbSync.ID_ATH + " TEXT,"
            + DbSync.NAME + " TEXT,"
            + DbSync.SURNAME + " TEXT,"
            + DbSync.AGE + " TEXT,"
            + DbSync.WS + " TEXT,"
            + DbSync.WE + " TEXT,"
            + DbSync.MS + " TEXT,"
            + DbSync.ME + " TEXT,"
            + DbSync.EFFICIENCY + " TEXT,"
            + DbSync.INTENSITY + " TEXT,"
            + DbSync.PHYSICAL + " TEXT,"
            + DbSync.CATEGORY + " TEXT,"
            + DbSync.PERIOD + " TEXT )";


    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    } //constructor


    @Override
    public void onCreate(SQLiteDatabase db) { //creating a database by running a sqlite query
        db.execSQL(queryAthlete);
        db.execSQL(queryCoach);
        db.execSQL(queryCategory);
        db.execSQL(queryPeriod);
        db.execSQL(SQL_CREATE_RESULTS);
        db.execSQL(queryTraining);
        db.execSQL(queryPhysicalPrep);
        db.execSQL("insert into category ('category_id', 'categoryType') VALUES " +
                "(1,'Individual')," +
                "(2,'Mixed pair')," +
                "(3,'Trio')," +
                "(4,'Group');");
        db.execSQL("insert into training_period ('period_id', 'period') VALUES " +
                "(1,'preparatory')," +
                "(2,'precompetitive')," +
                "(3,'competitive')," +
                "(4,'postcompetitive');");
        db.execSQL(querySync);


    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void putInfoFromServer(String id, String name, String surname, String birthDate, String ws, String we, String ms, String me, String efficiency, String intensity, String physiclaPrep, String category, String period, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbSync.ID_ATH, id);
        contentValues.put(DbSync.NAME, name);
        contentValues.put(DbSync.SURNAME, surname);
        contentValues.put(DbSync.AGE, birthDate);
        contentValues.put(DbSync.WS, ws);
        contentValues.put(DbSync.WE, we);
        contentValues.put(DbSync.MS, ms);
        contentValues.put(DbSync.ME, me);
        contentValues.put(DbSync.EFFICIENCY, efficiency);
        contentValues.put(DbSync.INTENSITY, intensity);
        contentValues.put(DbSync.PHYSICAL, physiclaPrep);
        contentValues.put(DbSync.CATEGORY, category);
        contentValues.put(DbSync.PERIOD, period);
        db.insert(DbSync.TABLE_SERVER_SYNC, null, contentValues);
    }

    public Cursor getInfoFromServer(SQLiteDatabase db) {
        String[] projection = {DbSync.ID_ATH, DbSync.NAME, DbSync.SURNAME, DbSync.AGE, DbSync.WS, DbSync.WE,
                DbSync.MS, DbSync.ME, DbSync.EFFICIENCY, DbSync.INTENSITY, DbSync.PHYSICAL, DbSync.CATEGORY, DbSync.PERIOD};
        Cursor cursor = db.query(DbSync.TABLE_SERVER_SYNC, projection, null, null, null, null, null);
        return cursor;
    }


    public void addNewAthlete(String athleteName, String athleteSurname, String athleteAge, String athletePass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL_athlete, athleteName);
        values.put(SURNAME_COL_athlete, athleteSurname);
        values.put(AGE_COL_athlete, athleteAge);
        values.put(PASSWORD_COL_athlete, athletePass);
        db.insert(TABLE_NAME_ATHLETE, null, values);
        db.close();
    }


    @SuppressLint("Range")
    public long getAthlete(String name_a, String surname_a) {
        long id;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryAthlete = " SELECT " + ID_COL_athlete + " FROM " + TABLE_NAME_ATHLETE + " WHERE " +
                NAME_COL_athlete + " =? " + " AND " + SURNAME_COL_athlete + " =? ";
        Cursor cursor = db.rawQuery(queryAthlete, new String[]{name_a, surname_a});
        if (cursor.moveToFirst()) {
            id = cursor.getLong(cursor.getColumnIndex(ID_COL_athlete));
        } else {
            id = 0;
        }
        cursor.close();
        db.close();
        if (id != 0) {
            return id;
        } else return 0;
    }

    @SuppressLint("Range")
    public String getAthleteAge(String id) {
        String age = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String queryAthlete = " SELECT " + AGE_COL_athlete + " FROM " + TABLE_NAME_ATHLETE + " WHERE " +
                ID_COL_athlete + " =? ";
        Cursor cursor = db.rawQuery(queryAthlete, new String[]{id});
        if (cursor.moveToFirst()) {
            age = cursor.getString(cursor.getColumnIndex(AGE_COL_athlete));
        } else {
            age = "";
        }
        cursor.close();
        db.close();
        if (!age.equals("")) {
            return age;
        } else return "";
    }


    public boolean checkIfAthleteExistsIntoDataBase(String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select* from athlete where name=? and surname=? ", new String[]{name, surname});
        if (cursor.getCount() > 0) {
            return true;

        } else {
        }
        return false;
    }

    public boolean checkNameAndPasswordForAthlete(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select* from athlete where name=? and password=?", new String[]{name, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addNewCoach(String coachName, String coachSurname, String coachType, String coachPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL_coach, coachName);
        values.put(SURNAME_COL_coach, coachSurname);
        values.put(TYPE_COL_coach, coachType);
        values.put(PASSWORD_COL_coach, coachPass);
        db.insert(TABLE_NAME_COACH, null, values);
        db.close();
    }

    public boolean checkIfCoachExistsIntoDataBase(String name, String surname) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select* from coach where name=? and surname=? ", new String[]{name, surname});
        if (cursor.getCount() > 0) {
            return true;

        } else {
        }
        return false;
    }

    public boolean checkNameAndPasswordForCoach(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select* from coach where name=? and password=?", new String[]{name, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addTimeIntoTrainingTable(String warmUpStart, String warmUpEnd, String mainStart, String mainEnd, long id_ath) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(WarmUp_COL_time_start, warmUpStart);
        contentValues.put(WarmUp_COL_time_end, warmUpEnd);
        contentValues.put(MainPart_COL_time_start, mainStart);
        contentValues.put(MainPart_COL_time_end, mainEnd);
        contentValues.put(ATHLETE_COL_fk, id_ath);
        db.insert(TABLE_TRAINING, null, contentValues);

        db.close();
        return true;

    }

    @SuppressLint("Range")
    public long getHoursId(String ws, String we, String ms, String me) {
        long id;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryAthlete = " SELECT " + ID_COL_training + " FROM " + TABLE_TRAINING + " WHERE " +
                WarmUp_COL_time_start + " =? " + " AND " + WarmUp_COL_time_end + " =? " + " AND " + MainPart_COL_time_start + " =? " + " AND " + MainPart_COL_time_end + " =? ";
        Cursor cursor = db.rawQuery(queryAthlete, new String[]{ws, we, ms, me});
        if (cursor.moveToFirst()) {
            id = cursor.getLong(cursor.getColumnIndex(ID_COL_training));
        } else {
            id = 0;
        }
        cursor.close();
        db.close();
        if (id != 0) {
            return id;
        } else return 0;
    }

    public void addNewResult(String intensity, String efficiency, long id_ath, long id_c, long id_p, long id_h, long id_prep) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesR = new ContentValues();
        valuesR.put(INTENSITY_COL, intensity);
        valuesR.put(EFFICIENCY_COL, efficiency);
        valuesR.put(ATHLETE_COL_fk, id_ath);
        valuesR.put(CATEGORY_COL_fk, id_c);
        valuesR.put(PERIOD_COL_fk, id_p);
        valuesR.put(HOURS_COL_fk, id_h);
        valuesR.put(PHYSICAL_COL_fk, id_prep);
        db.insert(TABLE_NAME_RESULTS, null, valuesR);
        db.close();
    }

    @SuppressLint("Range")
    public long getCategory(String category) {
        long id;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryCategory = " SELECT " + ID_COL_category + " FROM " + TABLE_CATEGORY + " WHERE " +
                CATEGORY_COL + " =? ";
        Cursor cursor = db.rawQuery(queryCategory, new String[]{category});
        if (cursor.moveToFirst()) {
            id = cursor.getLong(cursor.getColumnIndex(ID_COL_category));
        } else {
            id = 0;
        }
        cursor.close();
        db.close();
        if (id != 0) {
            return id;
        } else return 0;
    }


    @SuppressLint("Range")
    public long getPeriod(String period) {
        long id;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryPeriod = " SELECT " + ID_COL_period + " FROM " + TABLE_PERIOD + " WHERE " +
                PERIOD_COL + " =? ";
        Cursor cursor = db.rawQuery(queryPeriod, new String[]{period});
        if (cursor.moveToFirst()) {
            id = cursor.getLong(cursor.getColumnIndex(ID_COL_period));
        } else {
            id = 0;
        }
        cursor.close();
        db.close();
        if (id != 0) {
            return id;
        } else return 0;
    }

    public void addPhysicalPreparation(String preparatory, String precompetitive, String competitive, String postcompetitive) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PREPARATORY_COL, preparatory);
        values.put(PRECOMPETITIVE_COL, precompetitive);
        values.put(COMPETITIVE_COL, competitive);
        values.put(POSTCOMPETITIVE_COL, postcompetitive);
        db.insert(TABLE_PHYSICAL_PREPARATION, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public long getIdPreparation(String preparatory, String precompetitive, String competitive, String postcompetitive) {
        long id;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryAthlete = " SELECT " + ID_COL_physical + " FROM " + TABLE_PHYSICAL_PREPARATION + " WHERE " +
                PREPARATORY_COL + " =? " + " AND " + PRECOMPETITIVE_COL + " =? " + " AND " + COMPETITIVE_COL + " =? " + " AND " + POSTCOMPETITIVE_COL + " =? ";
        Cursor cursor = db.rawQuery(queryAthlete, new String[]{preparatory, precompetitive, competitive, postcompetitive});
        if (cursor.moveToFirst()) {
            id = cursor.getLong(cursor.getColumnIndex(ID_COL_physical));
        } else {
            id = 0;
        }
        cursor.close();
        db.close();
        if (id != 0) {
            return id;
        } else return 0;
    }

    public ArrayList<TrainingPhysicalPreparation> readPhysicalPreparation() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorPhysicalPrep = db.rawQuery("SELECT * FROM " + TABLE_PHYSICAL_PREPARATION, null);

        // on below line we are creating a new array list.
        ArrayList<TrainingPhysicalPreparation> trainingPhysicalPreparationArrayList = new ArrayList<>();

        // moving our cursor to first position.
        while (cursorPhysicalPrep.moveToNext()) {
            String id = cursorPhysicalPrep.getString(0);
            String preparatory = cursorPhysicalPrep.getString(1);
            String precompetitive = cursorPhysicalPrep.getString(2);
            String competitive = cursorPhysicalPrep.getString(3);
            String postcompetitive = cursorPhysicalPrep.getString(4);

            TrainingPhysicalPreparation physicalPreparation = new TrainingPhysicalPreparation(preparatory, precompetitive, competitive, postcompetitive, id);
            trainingPhysicalPreparationArrayList.add(physicalPreparation);
        }
        cursorPhysicalPrep.close();
        return trainingPhysicalPreparationArrayList;
        // at last closing our cursor
        // and returning our array list.
    }

  /*  public ArrayList<MergedTables> readMergedTablesRvTrainingPlan() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor merge = db.rawQuery(" SELECT *  FROM " + TABLE_NAME_ATHLETE + " a " + "  JOIN " + TABLE_NAME_RESULTS + " r " + " ON a. " + ID_COL_athlete + " =r. " + ATHLETE_COL_fk, null);
        ArrayList<MergedTables> mergedTablesArrayList = new ArrayList<>();
        while (merge.moveToNext()) {
            String name = merge.getString(1);
            String surname = merge.getString(2);
            String age = merge.getString(3);
            String efficiency = merge.getString(6);
            String intensity = merge.getString(7);
            MergedTables mergedTables = new MergedTables(name, surname, age, efficiency, intensity);
            mergedTablesArrayList.add(mergedTables);
        }
        merge.close();
        return mergedTablesArrayList;


    }*/

    public ArrayList<MergedTables> readMergedTablesAthleteActivity() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor merge = db.rawQuery(" SELECT *  FROM " + TABLE_NAME_ATHLETE + " a " + " JOIN " + TABLE_NAME_RESULTS + " r "
                + " ON a. " + ID_COL_athlete + " =r. " + ATHLETE_COL_fk
                + " JOIN " + TABLE_CATEGORY + " c " + " ON c. " + ID_COL_category + " =r." + CATEGORY_COL_fk
                + " JOIN " + TABLE_PERIOD + " p " + "ON r. " + PERIOD_COL_fk + " =p. " + ID_COL_period
                + " JOIN " + TABLE_TRAINING + " h " + "ON h. " + ID_COL_training + " =r. " + HOURS_COL_fk
                + " JOIN " + TABLE_PHYSICAL_PREPARATION + " t " + "ON t. " + ID_COL_physical + " =r. " + PHYSICAL_COL_fk, null);
        ArrayList<MergedTables> mergedTablesAthleteActivity = new ArrayList<>();
        while (merge.moveToNext()) {
            String id = merge.getString(0);
            String name = merge.getString(1);
            String surname = merge.getString(2);
            String age = merge.getString(3);
            String efficiency = merge.getString(6);
            String intensity = merge.getString(7);
            String category = merge.getString(14);
            String period = merge.getString(16);
            String ws = merge.getString(18);
            String we = merge.getString(19);
            String ms = merge.getString(20);
            String me = merge.getString(21);
            String preparatory = merge.getString(24);
            String precompetitive = merge.getString(25);
            String competitive = merge.getString(26);
            String postcompetitive = merge.getString(27);

            MergedTables mergedTables = new MergedTables(id, name, surname, age, efficiency, intensity, category, period, ws, we, ms, me, preparatory, precompetitive, competitive, postcompetitive);
            mergedTablesAthleteActivity.add(mergedTables);
        }
        merge.close();
        return mergedTablesAthleteActivity;
    }

    /*public void addSync(String id, String name, String efficiency, String intensity, String ws,SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(DbSync.ID_ATH, id);
        values.put(DbSync.NAME, name);
        values.put(DbSync.EFFICIENCY, efficiency);
        values.put(DbSync.INTENSITY, intensity);
        values.put(DbSync.WS, ws);

        database.insert(DbSync.TABLE_SERVER_SYNC, null, values);


    }*/
/*
    public Cursor readSyncTable(SQLiteDatabase database) {

        String[] projection = {DbSync.ID_ATH, DbSync.NAME, DbSync.EFFICIENCY, DbSync.INTENSITY, DbSync.WS};
        return (database.query(DbSync.TABLE_SERVER_SYNC, projection, null, null, null, null, null));
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ATHLETE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COACH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_RESULTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAINING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERIOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHYSICAL_PREPARATION);
        db.execSQL("DROP TABLE IF EXISTS " + DbSync.TABLE_SERVER_SYNC);

        onCreate(db);
    }
}