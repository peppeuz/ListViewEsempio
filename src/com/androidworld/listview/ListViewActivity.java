package com.androidworld.listview;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.listviewesempio.R;

public class ListViewActivity extends Activity implements OnClickListener {

	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	private EditText nuovoNome;
	private Button tastoAggiungi;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		//Definiamo ListView, EditText e Button dal loro ID
		nuovoNome = (EditText) findViewById(R.id.nuovoNome);
		tastoAggiungi = (Button) findViewById(R.id.bottoneAggiungi);
		mainListView = (ListView) findViewById(R.id.listView);

		//Aggiungiamo l'OnClickListener al tastoAggiungi
		tastoAggiungi.setOnClickListener(this);
		
		//Stabiliamo il nostro Array di autori
		String[] autori = new String[] { "Emanuele Cisotti", "Nicola Ligas",
				"Giuseppe Tripodi", "Roberto Orgiu", "Marco Giannino", "Agostino Caruso", "Lorenzo Quiroli",
				"Lorenzo Delli", "Erika Gherardi", "Nicola Randone",
				"Giorgio Palmieri", "Emanuele Manili", "Francesco Fumelli" };
		//Creiamo un nuovo ArrayList e aggiungiamo tutti gli autori
		final ArrayList<String> listaAutori = new ArrayList<String>();
		listaAutori.addAll(Arrays.asList(autori));
		
		//Creiamo un nuovo ArrayAdapter, necessario per "sistemare" tutti gli elementi all'interno della Listview
		listAdapter = new ArrayAdapter<String>(this, R.layout.row, listaAutori);

		mainListView.setAdapter(listAdapter);
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int posizione, long id) {
				// TODO Auto-generated method stub

				AlertDialog.Builder adb = new AlertDialog.Builder(
						ListViewActivity.this);
				adb.setTitle("Elimina elemento:");
				adb.setMessage("Sei sicuro di voler eliminare " +listaAutori.get(posizione)+ "?");
				final int posizioneDaRimuovere = posizione;
				adb.setNegativeButton("Annulla", null);
				adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						listAdapter.remove(listAdapter.getItem(posizioneDaRimuovere));
					}
				});
				adb.show();
			}

		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String nuovoAutore = nuovoNome.getText().toString();
		listAdapter.add(nuovoAutore);
		nuovoNome.setText("");
	}
}