package com.example.ingajuan_examenfinal;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.ingajuan_examenfinal.modelo.Base;
import com.example.ingajuan_examenfinal.modelo.Empleados;
import android.database.sqlite.SQLiteDatabase;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    Button btnCrear,btnGuardar,btnEliminar,btnBuscar, btnModificar;
    EditText txtcedula,txtnombres,txtapellidos,txtfecha, txtsalario, txtdisca, txthorario;
    Base base;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrear=findViewById(R.id.botonCrear);
        btnModificar=findViewById(R.id.botonEditar);
        btnEliminar=findViewById(R.id.botonEliminar);
        btnBuscar=findViewById(R.id.botonVer);
        verEmpleado();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Empleados empleados=new Empleados();
                empleados.setCedula(txtcedula.toString());
                empleados.setNombres(txtnombres.getText().toString());
                empleados.setApellidos(txtapellidos.toString());
                empleados.setFecha_contrato(txtfecha.toString());
                empleados.setSalario(txtsalario.toString());
                empleados.setDiscapacidad(txtdisca.toString());
                empleados.setHorario(txthorario.toString());

                empleados.guardar(MainActivity.this);

                txtcedula.setText("");
                txtnombres.setText("");
                txtapellidos.setText("");
                txtfecha.setText("");
                txtsalario.setText("");
                txtdisca.setText("");
                txthorario.setText("");
                Toast.makeText(getApplicationContext(),"EL empleado ha sido creado",Toast.LENGTH_LONG).show();



            }
        });

        //guarda empleados
        btnModificar=findViewById(R.id.botonEditar);
        btnEliminar=findViewById(R.id.botonEliminar);
        btnGuardar=findViewById(R.id.botonGrabar);
        btnBuscar=findViewById(R.id.botonVer);
        txtcedula=findViewById(R.id.edittxtid);
        txtnombres=findViewById(R.id.edittxtnombres);
        txtapellidos=findViewById(R.id.edittxtapellidos);
        txtfecha=findViewById(R.id.edittxtfecha);
        txtsalario=findViewById(R.id.edittextsalario);
        txtdisca=findViewById(R.id.edittextdisca);
        txthorario=findViewById(R.id.edittexthorario);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Empleados empleados=new Empleados();
                empleados.setCedula(txtcedula.getText().toString());
                empleados.setNombres(txtnombres.getText().toString());
                empleados.setApellidos(txtapellidos.getText().toString());
                empleados.setFecha_contrato(txtfecha.getText().toString());
                empleados.setSalario(txtsalario.getText().toString());
                empleados.setDiscapacidad(txtdisca.getText().toString());
                empleados.setHorario(txthorario.getText().toString());
                empleados.guardar(MainActivity.this);

                txtcedula.setText("");
                txtnombres.setText("");
                txtapellidos.setText("");
                txtfecha.setText("");
                txtsalario.setText("");
                txtdisca.setText("");
                txthorario.setText("");

                Toast.makeText(getApplicationContext(),"El empleado ha sido creado",Toast.LENGTH_LONG).show();



            }
        });
        //actualizar un empleado
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarEmpleado();


            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eliminarEmpleado();
            }
        });

    }
    public void verEmpleado(){
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consulta();


            }
        });

    }
    public void showMessage (String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        builder.setCancelable (true);
        builder.setTitle (title);
        builder.setMessage(Message);
        builder.show();

    }
    public void consulta() {

        Base admin = new Base(this);
        SQLiteDatabase db = admin.getWritableDatabase();

// lee el campo de de cedula del empleado

        String cedula = txtcedula.getText().toString();

// SE REALIZA LA CONSULTA

        Cursor fila = db.rawQuery("SELECT nombres, apellidos,fecha_contrato,salario, discapacidad, horario FROM empleados WHERE cedula=" + cedula, null);
        if(fila.moveToFirst()) {

            txtnombres.setText(fila.getString( 0));
            txtapellidos.setText(fila.getString( 1));
            txtfecha.setText(fila.getString(2));
            txtsalario.setText(fila.getString(3));
            txtdisca.setText(fila.getString(4));
            txthorario.setText(fila.getString(5));

        } else {

            Toast.makeText( this,"El empleado no existe",Toast.LENGTH_LONG).show();
        }
    }
    public void modificarEmpleado(){
        Base admin = new Base(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cedula = txtcedula.getText().toString();
        String nombres= txtnombres.getText().toString();
        String apellidos= txtapellidos.getText().toString();
        String fecha_contrato= txtfecha.getText().toString();
        String salario= txtsalario.getText().toString();
        String discapacidad= txtdisca.getText().toString();
        String horario= txthorario.getText().toString();


        ContentValues registro = new ContentValues();
        registro.put("nombres",nombres);
        registro.put("apellidos",apellidos);
        registro.put("fecha_contrato",fecha_contrato);
        registro.put("salario",salario);
        registro.put("discapacidad",discapacidad);
        registro.put("horario",horario);

        int cant= db.update("empleados",registro,"cedula="+cedula,null);
        db.close();
        txtcedula.setText("");
        txtnombres.setText("");
        txtapellidos.setText("");
        txtfecha.setText("");
        txtsalario.setText("");
        txtdisca.setText("");
        txthorario.setText("");

        if(cant==1){
            Toast.makeText( this,"El empleado ha sido actualizado",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText( this,"No existe el empleado",Toast.LENGTH_LONG).show();

        }

    }
    public void eliminarEmpleado(){

        Base admin = new Base(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cedula = txtcedula.getText().toString();
        int cant= db.delete("empleados","cedula="+cedula,null);
        db.close();
        txtcedula.setText("");
        txtnombres.setText("");
        txtapellidos.setText("");
        txtfecha.setText("");
        txtsalario.setText("");
        txtdisca.setText("");
        txthorario.setText("");

        if(cant==1){
            Toast.makeText( this,"El empleado ha sido eliminado",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText( this,"El empleado no existe",Toast.LENGTH_LONG).show();

        }
    }

}