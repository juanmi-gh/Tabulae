package com.haya.tabulae.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.haya.tabulae.R;
import com.haya.tabulae.models.Item;
import com.haya.tabulae.models.Market;

public class ItemDetailActivity extends Activity {

	private Item item;
	private EditText editName;
	private EditText editNotas;
	private Spinner marketSelector;
	private TextView textPrice;

	private ArrayList<Market> markets;
	private ArrayAdapter<Market> adapterSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		getActionBar().setTitle("Details");
		setBackground(android.R.color.holo_purple);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setLogo(R.drawable.ic_arrow_back);

		editName = (EditText) findViewById(R.id.editItemName);
		editNotas = (EditText) findViewById(R.id.editItemNotes);
		marketSelector = (Spinner) findViewById(R.id.spinnerMarket);
		textPrice = (TextView) findViewById(R.id.textPrice);

		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_save) {
			saveItem();
		} else if ( id == android.R.id.home) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	private void init() {

		long idItem = getIntent().getExtras().getLong("idItem");

		item = new Select().from(Item.class).where("id = ?", idItem).executeSingle();

		if ( item == null ) {
			Toast.makeText(this, "Ups.. something went wrong :_(", Toast.LENGTH_LONG).show();
			finish();
			return;
		}

		editName.setText(item.getName());

		if ( item.getNotes() != null ) {
			editNotas.setText(item.getNotes());
		}

//		markets = new Select().from(table)
//		adapterSpinner = new ArrayAdapter<Market>(this, android.R.layout.simple_spinner_dropdown_item, markets);
//		marketSelector.setAdapter(adapterSpinner);	

		int price = (int) (Math.random() * 100);
		textPrice.setText(price + "�");
	}

	private void saveItem() {
		
		finish();
	}

	@SuppressWarnings("deprecation")
	private void setBackground(int background) {
		
		Drawable draw;
        if(android.os.Build.VERSION.SDK_INT >= 21){
        	draw = this.getResources().getDrawable(background, this.getTheme());
        	this.getActionBar().setBackgroundDrawable(draw);
        } else {
        	draw = this.getResources().getDrawable(background);
        	this.getActionBar().setBackgroundDrawable(draw);
        }
	}

}