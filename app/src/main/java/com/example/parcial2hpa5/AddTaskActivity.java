package com.example.parcial2hpa5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    // EditText para ingresar el título de la tarea
    private EditText titleEditText;

    // EditText para ingresar la fecha de vencimiento de la tarea
    private EditText dueDateEditText;

    // Botón para guardar la tarea
    private Button saveButton;

    // Formato de fecha
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Inicializar el formato de fecha
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Obtener los EditText y el botón
        titleEditText = findViewById(R.id.titleEditText);
        dueDateEditText = findViewById(R.id.dueDateEditText);
        saveButton = findViewById(R.id.saveButton);

        // Asignar un listener al botón para guardar la tarea
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el texto de los EditText
                String title = titleEditText.getText().toString();
                String dueDate = dueDateEditText.getText().toString();

                // Validar que no estén vacíos
                if (title.isEmpty() || dueDate.isEmpty()) {
                    // Mostrar un mensaje de error
                    Toast.makeText(AddTaskActivity.this, "Por favor, ingrese el título y la fecha de vencimiento de la tarea", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Intentar parsear la fecha
                    try {
                        Date date = dateFormat.parse(dueDate);
                        // Crear una nueva tarea con el título y la fecha
                        Task task = new Task(title, date, false);
                        // Crear un intent para devolver el resultado
                        Intent intent = new Intent();
                        // Poner la tarea como un extra
                        intent.putExtra("task", task);
                        // Establecer el resultado como OK
                        setResult(RESULT_OK, intent);
                        // Finalizar la actividad
                        finish();
                    }
                    catch (ParseException e) {
                        // Mostrar un mensaje de error
                        Toast.makeText(AddTaskActivity.this, "Por favor, ingrese una fecha válida en el formato dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
