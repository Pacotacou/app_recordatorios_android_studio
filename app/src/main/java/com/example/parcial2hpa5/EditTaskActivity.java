package com.example.parcial2hpa5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditTaskActivity extends AppCompatActivity {

    // EditText para editar el título de la tarea
    private EditText titleEditText;

    // EditText para editar la fecha de vencimiento de la tarea
    private EditText dueDateEditText;

    // CheckBox para marcar la tarea como completada
    private CheckBox completedCheckBox;

    // Botón para guardar los cambios
    private Button saveButton;

    // Tarea a editar
    private Task task;

    // Formato de fecha
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Inicializar el formato de fecha
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Obtener los EditText, el CheckBox y el botón
        titleEditText = findViewById(R.id.titleEditText);
        dueDateEditText = findViewById(R.id.dueDateEditText);
        completedCheckBox = findViewById(R.id.completedCheckBox);
        saveButton = findViewById(R.id.saveButton);

        // Obtener la tarea desde el intent
        task = (Task) getIntent().getSerializableExtra("task");

        // Mostrar los datos de la tarea en los EditText y el CheckBox
        titleEditText.setText(task.getTitle());
        dueDateEditText.setText(dateFormat.format(task.getDueDate()));
        completedCheckBox.setChecked(task.isCompleted());

        // Asignar un listener al botón para guardar los cambios
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // Obtener el texto de los EditText
                    String title = titleEditText.getText().toString();
                    String dueDate = dueDateEditText.getText().toString();

                    // Validar que no estén vacíos
                    if (title.isEmpty() || dueDate.isEmpty()) {
                        // Mostrar un mensaje de error
                        Toast.makeText(EditTaskActivity.this, "Por favor, ingrese el título y la fecha de vencimiento de la tarea", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        // Intentar parsear la fecha
                        try {
                            Date date = dateFormat.parse(dueDate);
                            // Actualizar los datos de la tarea
                            task.setTitle(title);
                            task.setDueDate(date);
                            task.setCompleted(completedCheckBox.isChecked());
                            // Crear un intent para devolver el resultado
                            Intent intent = new Intent();
                            // Poner la tarea como un extra
                            intent.putExtra("task", task);
                            intent.putExtra("position", getIntent().getIntExtra("position",0));
                            // Establecer el resultado como OK
                            setResult(RESULT_OK, intent);
                            // Finalizar la actividad
                            finish();
                        }
                        catch (ParseException e) {
                            // Mostrar un mensaje de error
                            Toast.makeText(EditTaskActivity.this, "Por favor, ingrese una fecha válida en el formato dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(EditTaskActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
