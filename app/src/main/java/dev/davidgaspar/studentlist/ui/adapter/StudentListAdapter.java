package dev.davidgaspar.studentlist.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dev.davidgaspar.studentlist.R;
import dev.davidgaspar.studentlist.model.Student;

public class StudentListAdapter extends BaseAdapter {

    private  final Context context;
    private final ArrayList<Student> studentList = new ArrayList<>();

    public StudentListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Student getItem(int index) {
        return studentList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return studentList.get(index).getId();
    }

    @Override
    public View getView(int index, View child, ViewGroup parent) {
        View view;
        ViewHolder holder;

        if (child == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_student, parent,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = child;
            holder = (ViewHolder) child.getTag();
        }
        loadData(index, holder);

        return view;
    }

    private void loadData(int index, ViewHolder holder) {
        Student student = studentList.get(index);
        holder.name.setText(student.getName());
        holder.phone.setText(student.getPhone());
    }

    public void clear() {
        studentList.clear();
    }

    public void addAll(ArrayList<Student> allStudents) {
        studentList.addAll(allStudents);
    }

    public void refresh(ArrayList<Student> students) {
        clear();
        addAll(students);
        notifyDataSetChanged();
    }

    public void remove(Student student) {
        studentList.remove(student);
        notifyDataSetChanged();
    }

    private static class ViewHolder {

        private final TextView name;
        private final TextView phone;

        ViewHolder(View view) {
            this.name = view.findViewById(R.id.txt_student_name);
            this.phone = view.findViewById(R.id.txt_student_phone);
        }

    }
}