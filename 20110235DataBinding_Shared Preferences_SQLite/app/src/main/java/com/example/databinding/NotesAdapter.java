package com.example.databinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class NotesAdapter extends BaseAdapter {

    private SQLite_Example1 context;
    private int layout;
    private List<NotesModel> noteList;

    public NotesAdapter(SQLite_Example1 context, int layout, List<NotesModel> noteList) {
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
    private class ViewHoler{
        TextView textViewNote;
        ImageView imageViewEdit;
        ImageView imageViewDelete;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHoler viewHoler;
        if(convertView == null){
            viewHoler = new ViewHoler();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHoler.textViewNote=(TextView) convertView.findViewById(R.id.textViewNameNote);
            viewHoler.imageViewDelete=(ImageView)convertView.findViewById(R.id.imageViewDelete);
            viewHoler.imageViewEdit = (ImageView) convertView.findViewById(R.id.imageViewEdit);
            convertView.setTag(viewHoler);
        }
        else {
            viewHoler=(ViewHoler) convertView.getTag();
        }
        final NotesModel notes = noteList.get(position);
        viewHoler.textViewNote.setText(notes.getNameNote());

        //bắt sự kiện xóa notes
        viewHoler.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDelete(notes.getNameNote(),notes.getIdNote());
            }
        });
        viewHoler.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Cập nhật " + notes.getNameNote(), Toast.LENGTH_SHORT).show();
                context.DialogCapNhatNote(notes.getNameNote(), notes.getIdNote());
            }
        });
        return convertView;
    }
}
