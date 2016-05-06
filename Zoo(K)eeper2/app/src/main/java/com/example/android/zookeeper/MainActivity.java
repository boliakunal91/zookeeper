package com.example.android.zookeeper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        OnItemClickListener {
    ImageView imageview_large;
    TextView name_desc;
    TextView desc;
    Button button_info_back;
    Button button_back;



    public static final String[] titles = new String[] { "Black Bear",
            "Black Buck", "Bull Musk", "Caribou", "Giant Panda", "Giraffe", "Zebra","Cheetah" };

    public static final Integer[] images = {  R.drawable.black_bear_small,
            R.drawable.blackbuck_antelope_small, R.drawable.bull_musk_ox_small, R.drawable.caribou_small, R.drawable.giant_panda_small1,
            R.drawable.giraffe_small1, R.drawable.grant_zebra_small1, R.drawable.cheetah_small};

    public static final String[] detail_titles = new String[] { "Black Bear",
            "Black Buck", "Bull Musk", "Caribou", "Giant Panda", "Giraffe", "Zebra","Cheetah" };

    public static final String[] detail_descriptions = new String[] {
            "The black bear (Ursus americanus) is a medium-sized bear native to North America. It is the continent's smallest and most widely distributed bear species.",
            "The blackbuck (Antilope cervicapra) is an ungulate species of antelope native to the Indian subcontinent that has been listed as Near Threatened on the IUCN Red List since 2003",
            "The muskox is an Arctic mammal of the family Bovidae, noted for its thick coat",
            "Caribou (North America) refers to any of several North American subspecies, ecotypes, populations, and herds of the species Rangifer tarandus.",
            "The giant panda (Ailuropoda melanoleuca, black and white cat-foot, also known as panda bear or simply panda, is a bear native to south central China.",
            "The giraffe (Giraffa camelopardalis) is an African even-toed ungulate mammal, the tallest living terrestrial animal and the largest ruminant. ",
            "Zebras are several species of African equids (horse family) united by their distinctive black and white striped coats.",
            "The cheetah (Acinonyx jubatus) is a big cat in the subfamily Felinae that inhabits most of Africa and parts of Iran."};

    public static final Integer[] large_images = { R.drawable.black_bear_large,
            R.drawable.blackbuck_antelope_large, R.drawable.bull_musk_ox_large, R.drawable.caribou_large, R.drawable.giant_panda_large,
            R.drawable.giraffe_large, R.drawable.grant_zebra_large, R.drawable.cheetah_large };

    ListView listView;
    List<RowItem> rowItems;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.listView);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        if(position == 7){


        }
            setContentView(R.layout.details);
            final Context context1 = this;
            button_back = (Button) findViewById(R.id.button_back);
            button_back.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(context1, MainActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context1.startActivity(startIntent);
                }
            });
            name_desc = (TextView) this.findViewById(R.id.textview_animal_name_detail);
            desc = (TextView) this.findViewById(R.id.textView_short_desc);
            imageview_large = (ImageView) this.findViewById(R.id.imageView_large);

            name_desc.setText(detail_titles[position]);
            desc.setText(detail_descriptions[position]);
            imageview_large.setImageResource(large_images[position]);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final Context context;
        context=getBaseContext();
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_information) {
            setContentView(R.layout.information);
            TextView callnumber = (TextView) findViewById(R.id.textView_call);
            button_info_back = (Button)findViewById(R.id.button_info_back);
            button_info_back.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(context, MainActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(startIntent);
                }
            });


            callnumber.setOnClickListener(new View.OnClickListener() {



                    @Override
                    public void onClick(View v) {
                    String url = "tel:8888888";
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                    //startActivity(intent);
                        try {
                            startActivity(intent);
                        }catch(SecurityException e)
                        {

                        }
                }
                });
            return true;
        }
        else{
            Uri packageUri = Uri.parse("package:com.example.android.zookeeper");
            Intent uninstallIntent =
                    new Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri);
            startActivity(uninstallIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}


