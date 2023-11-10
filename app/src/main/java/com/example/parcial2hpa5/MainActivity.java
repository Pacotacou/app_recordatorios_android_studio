package com.example.parcial2hpa5;
/*
PARCIAL 2 HPA IV
INTEGRANTES:
STEVEN CISNEROS
JOHAN ESQUIVEL
MOISÉS RODRÍGUEZ
 */
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ArrayList para almacenar las tareas
    private ArrayList<Task> tasks;

    // Adaptador personalizado para el ListView
    private TaskAdapter adapter;

    // ListView para mostrar las tareas
    private ListView listView;

    // Botón para agregar una nueva tarea
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el ArrayList
        tasks = new ArrayList<>();

        // Inicializar el adaptador
        adapter = new TaskAdapter(this, tasks);

        // Obtener el ListView y asignarle el adaptador
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // Obtener el botón y asignarle un listener
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un intent para iniciar la actividad AddTaskActivity
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                // Iniciar la actividad esperando un resultado
                startActivityForResult(intent, 1);
            }
        });

        // Asignar un listener al ListView para detectar cuando se hace clic en un elemento
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener la tarea en la posición clicada
                Task task = tasks.get(position);
                // Crear un intent para iniciar la actividad EditTaskActivity
                Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                // Pasar la tarea como un extra
                intent.putExtra("task", task);
                intent.putExtra("position",tasks.indexOf(task));
                // Iniciar la actividad esperando un resultado
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Si el resultado es OK
        if (resultCode == RESULT_OK) {
            // Si el código de solicitud es 1, significa que se agregó una nueva tarea
            if (requestCode == 1) {
                // Obtener la tarea desde el intent
                Task task = (Task) data.getSerializableExtra("task");
                // Agregar la tarea al ArrayList
                tasks.add(task);
                // Notificar al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
            }
            // Si el código de solicitud es 2, significa que se editó una tarea existente
            else if (requestCode == 2) {
                try {
                    // Obtener la tarea desde el intent
                    Task task = (Task) data.getSerializableExtra("task");
                    // Obtener la posición de la tarea en el ArrayList
                    Integer position = data.getIntExtra("position",0);
                    // Actualizar la tarea en el ArrayList
                    tasks.set(position, task);
                    // Notificar al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }


            }
        }
    }
}
