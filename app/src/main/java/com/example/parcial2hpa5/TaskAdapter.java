package com.example.parcial2hpa5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

    // Contexto de la aplicación
    private Context context;

    // ArrayList de tareas
    private ArrayList<Task> tasks;

    // Formato de fecha
    private SimpleDateFormat dateFormat;

    // Constructor de la clase
    public TaskAdapter(@NonNull Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
        this.tasks = tasks;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtener la tarea en la posición indicada
        Task task = tasks.get(position);

        // Si la vista es nula, inflarla con el layout item_task
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        }

        // Obtener los componentes del layout
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView dueDateTextView = convertView.findViewById(R.id.dueDateTextView);
        ImageView statusImageView = convertView.findViewById(R.id.statusImageView);

        // Mostrar los datos de la tarea en los componentes
        titleTextView.setText(task.getTitle());
        dueDateTextView.setText(dateFormat.format(task.getDueDate()));
        statusImageView.setImageResource(task.isCompleted() ? R.drawable.ic_check : R.drawable.ic_cross);

        // Devolver la vista
        return convertView;
    }
}
