package com.example.sqlite_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NotesModel> noteList;

    public NotesAdapter(Context context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView textViewNote;
        ImageView imageViewEdit;
        ImageView imageViewDetele;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.textViewNote= (TextView) convertView.findViewById(R.id.textViewNameNote);
            viewHolder.imageViewDetele=(ImageView) convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit=(ImageView) convertView.findViewById(R.id.imageViewEdit);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());
        return convertView;
    }
}
