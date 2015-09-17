package rongfeng.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddNewActivity extends AppCompatActivity implements View.OnClickListener{

    Button addBt;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main);
        addBt =(Button)findViewById(R.id.add);
        editText=(EditText)findViewById(R.id.edit);
        addBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add:
                ToDo todo=new ToDo();
                todo.setTitle(editText.getText().toString());
                ToDoDao.getInstance().addNewTodo(getContentResolver(),todo,StatusFlag.ADD);
                finish();
                break;
            default:

        }

    }
}
