package com.example.aarondavid.tutorialapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ListView mainMenu;
        final String[] labelItems = getResources().getStringArray(R.array.mainList);
        String[] subLabelItems = getResources().getStringArray(R.array.mainSubList);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Custom List View
        CustomList adapter = new CustomList(MainActivity.this, labelItems, subLabelItems);
        mainMenu = (ListView) findViewById(R.id.mainMenu);

        mainMenu.setAdapter(adapter);
        mainMenu.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int menuItem = position;
                Intent menuIntent;

                switch (menuItem)
                {
                    case 0: menuIntent = new Intent(MainActivity.this, NewActivity.class);
                            startActivity(menuIntent);
                            break;
                    case 1: menuIntent = new Intent(MainActivity.this, StateChangeActivity.class);
                            startActivity(menuIntent);
                            break;
                    case 2: menuIntent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(menuIntent);
                            break;
                    default: Toast.makeText(MainActivity.this, "This option is not yet available", Toast.LENGTH_SHORT).show();
                }
//                Intent menuIntent = new Intent(MainActivity.this, NewActivity.class);
//                startActivity(menuIntent);
            }
        });

//        Single List View
//        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_listview, labelItems);
//        ListView listView = (ListView) findViewById(R.id.mainMenu);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//            {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//                {
//                    String item = ((TextView)view).getText().toString();
//                    Toast.makeText(getBaseContext(), item, Toast.LENGTH_SHORT).show();
//
//                    Intent menuIntent = new Intent(MainActivity.this, NewActivity.class);
//                    startActivity(menuIntent);
//                }
//            });
    }

}
