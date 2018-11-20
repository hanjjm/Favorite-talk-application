package com.example.hanju.myapplication;

import android.provider.BaseColumns;

public class Database {
    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "name";
        public static final String PHONENUMBER = "phoneNum";
        public static final String FAVOR = "favor";
        public static final String _TABLENAME0 = "people";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +NAME+" text not null , "
                +PHONENUMBER+" text not null unique, "
                +FAVOR+" Integer not null );";
    }
}
