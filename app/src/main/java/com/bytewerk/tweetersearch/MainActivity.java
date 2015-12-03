package com.bytewerk.tweetersearch;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends ListActivity {

    private final String SEARCHES = "searches";

    private EditText queryEditText;
    private EditText tagEditText;
    private SharedPreferences savedSearches;
    private ArrayList<String> tags;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получение ссылок на EditText
        queryEditText = (EditText)findViewById(R.id.queryEditText);
        tagEditText = (EditText)findViewById(R.id.tagEditText);

        //Получение объекта sharedPrefereces с сохраненными запросами
        savedSearches = getSharedPreferences(SEARCHES,MODE_PRIVATE);

        //Сохрание тэгов в ArrayList и их сортировка
        tags =new ArrayList<String>(savedSearches.getAll().keySet());
        Collections.sort(tags,String.CASE_INSENSITIVE_ORDER);

        //Создание адаптера и его привязка к ListView
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,tags);
        setListAdapter(adapter);

        //регистрация слушателя на нажатие кнопки сохранить
        ImageButton saveButton =
                (ImageButton)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(saveButtonListener);

        //регистрация слушателя на нажатие сохраненного тэга
        getListView().setOnClickListener(itemClickListener);


        //слушатель на изменение и удаление запроса
        getListView().getOnItemLongClickListener(itemLongClickListener);


    }
}
