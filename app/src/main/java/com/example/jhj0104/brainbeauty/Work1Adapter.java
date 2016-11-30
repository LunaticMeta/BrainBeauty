package com.example.jhj0104.brainbeauty;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhj0104 on 2016-11-24.
 */


public class Work1Adapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> todoList;

    public Work1Adapter(Context context, int resource, ArrayList<String> object) {
        super(context, resource, object);
        this.context = context;
        this.todoList = object;
    }

    public View getView(final int position, final View convertView, ViewGroup parent){
        final Work work1 = new Work(todoList.get(position));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_work1,null);



        TextView list_title = (TextView) view.findViewById(R.id.work_text1);
        list_title.setText("$" + String.valueOf(work1.getTitle()));

        //display trimmed excerpt for description
        int descriptionLength = work1.getTitle().length();
        if(descriptionLength >= 30){
            String descriptionTrim = work1.getTitle().substring(0, 30) + "...";
            list_title.setText(descriptionTrim);
        }else{
            list_title.setText(work1.getTitle());
        }

        //list item's button
//        final Button button1 = (Button) view.findViewById(R.id.check_image);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = ((Activity) context).getIntent();
//                S_data data = (S_data) intent.getSerializableExtra("Date");
//                final String myDate = valueOf(data.Date);
//
////                final int position = getPositionForView(view);
//                if (position != ListView.INVALID_POSITION) {
//                    //DO THE STUFF YOU WANT TO DO WITH THE position
////                    Toast.makeText(getContext(), Integer.toString(position) + "번 아이템 선택.", Toast.LENGTH_SHORT).show();
//
//                    DBHelper dbHelper = new DBHelper(getContext(),"DO_LIST_DB",1);
//                    String bucket = todoList.get(position);
//                    dbHelper.update_DL_FLAG(myDate.toString(),bucket,"true");
//
//                    data = new S_data(myDate, "");
//                    intent = new Intent(getContext(), S_main.class);
//                    intent.putExtra("Date",data);
//                    getContext().startActivity(intent);
//                    ((Activity)context).finish();
//                }
//            }
//
//
//        });


        return view;
    }

}
