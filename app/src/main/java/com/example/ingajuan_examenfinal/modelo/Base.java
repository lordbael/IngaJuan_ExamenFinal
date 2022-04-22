package com.example.ingajuan_examenfinal.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Base extends SQLiteOpenHelper{

    private static final String DATABASE_NOMBRE="empleados.db";
    private  static final int DATABASE_VERSION=2;
    public static final String TABLA_EMPLEADOS ="empleados";
    public Base(@Nullable Context context) {
        super(context, DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql ="CREATE TABLE "+TABLA_EMPLEADOS+" (" +
                "cedula TEXT," +
                "nombres TEXT," +
                "apellidos TEXT," +
                "fecha_contrato TEXT," +
                "salario TEXT," +
                "discapacidad TEXT," +
                "horario TEXT" +
                ")";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqld ="DROP TABLE " +TABLA_EMPLEADOS;
        sqLiteDatabase.execSQL(sqld);
        onCreate(sqLiteDatabase);


    }
    public Cursor AllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        //Cursor res= db.rawQuery("SELECT * FROM "+TABLA_EMPLEADOS,null);
        Cursor cursor = db.rawQuery("Select * from empleados", null);
        return cursor;
    }

    public void editarEmpleado(String cedula,String nombres,String apellidos,String fecha_contrato, String salario, String discapacidad, String horario){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("UPDATE empleados SET nombres='"+nombres+"',apellidos='"+apellidos+"',"+fecha_contrato+","+salario+","+discapacidad+","+horario+" WHERE id_producto="+cedula);
        db.close();

    }
    public  void eliminarEmpleado( String cedula){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM empleados WHERE cedula="+cedula);
        db.close();

    }



    public void noQuery( String noqsl){
        this.getWritableDatabase().execSQL(noqsl);


    }
    public Cursor query(String sql){

        return this.getReadableDatabase().rawQuery(sql,null);


    }

}
