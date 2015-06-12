package com.example.alvarado.memoria_ext;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {
    private EditText Texto;//declaramos las variables.
    private Button Guardar, Abrir;
    private static final int READ_BLOCK_SIZE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//empesamos a escribir el codigo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Texto = (EditText) findViewById(R.id.txtArchivo);
        Guardar = (Button)findViewById(R.id.btnGuardar);
        Abrir = (Button)findViewById(R.id.btnAbrir);
        Guardar.setOnClickListener((android.view.View.OnClickListener) this);
        Abrir.setOnClickListener((android.view.View.OnClickListener) this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;//retornamos los valores
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v)
    { File sdCard, directory, file = null;

        try {
            if (Environment.getExternalStorageState().equals("mounted")) {

                sdCard = Environment.getExternalStorageDirectory();

                if (v.equals(Guardar)) {
                    String str = Texto.getText().toString();

                    FileOutputStream fout = null;
                    try {
                        directory = new File(sdCard.getAbsolutePath() + "/Mis archivos");

                        directory.mkdirs();

                        file = new File(directory, "MiArchivo.txt");//Vargamos los archivosd

                        fout = new FileOutputStream(file);
                        OutputStreamWriter ows = new OutputStreamWriter(fout);
                        ows.write(str);
                        ows.flush();
                        ows.close();

                        Toast.makeText(getBaseContext(), "El archivo se ha almacenado!!!", Toast.LENGTH_SHORT).show();//mandamos el mensaje si los archivos se cargaron correctamente

                        Texto.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (v.equals(Abrir)) {
                    try {
                        directory = new File(sdCard.getAbsolutePath() + "/Mis archivos");
                        file = new File(directory, "MiArchivo.txt");
                        FileInputStream fin = new FileInputStream(file);
                        InputStreamReader isr = new InputStreamReader(fin);
                        char[] inputBuffer = new char[READ_BLOCK_SIZE];
                        String str = "";
                        int charRead;
                        while ((charRead = isr.read(inputBuffer)) > 0) {
                            String strRead = String.copyValueOf(inputBuffer, 0, charRead);
                            str += strRead;
                            inputBuffer = new char[READ_BLOCK_SIZE];
                        }
                    }
                }
            }

            Texto.setText(str);


            isr.close();
            Toast.makeText(getBaseContext(), "El ACRHIVO A SIDO CARGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}else{

        Toast.makeText(getBaseContext(), "EL ALMACENAMIENTO NO SE ENCONTRO", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){


