package org.ieselcaminas.francisco.dialogs;

import android.app.Dialog;
import android.app.AlertDialog.Builder;//Metodo 2
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CharSequence[] items = { "Google", "Apple", "Microsoft" };
    boolean[] itemsChecked = new boolean [items.length];
    CharSequence[] items2 = { "WIFI", "3G/4G", "Bluetooth" };
    boolean[] itemsChecked2 = new boolean [items.length];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(0).show();
            }
        });
        Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(1).show();
            }
        });
        Button b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog progressDialog = createDialog(2);
                new Thread(new Runnable(){
                    public void run(){
                        try {
                            //---simulate doing something lengthy---
                            Thread.sleep(5000);
                            //---dismiss the dialog---
                            progressDialog.dismiss();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private Dialog createDialog(int id) {
        switch (id) {
            case 0:
                return new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("This is a dialog with some simple text...")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(getBaseContext(),
                                                "OK clicked!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Toast.makeText(getBaseContext(),
                                                "Cancel clicked!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        )
                        .setMultiChoiceItems(items, itemsChecked,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which, boolean isChecked) {
                                        Toast.makeText(getBaseContext(),
                                                items[which] + (isChecked ? " checked!":" unchecked!"),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                        ).create();
            case 1:
                Builder builder=  new Builder(this);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("This is a dialog with some simple text...");
                builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(getBaseContext(),
                                "OK clicked!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getBaseContext(),
                                        "Cancel clicked!", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setMultiChoiceItems(items2, itemsChecked2,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which, boolean isChecked) {
                                Toast.makeText(getBaseContext(),
                                        items2[which] + (isChecked ? " checked!":" unchecked!"),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                return builder.create();

            case 2:
                ProgressDialog progressDialog = ProgressDialog.show(
                        MainActivity.this, "Doing something", "Please wait...", true);
                return progressDialog;
        }
        return null;
    }

}
