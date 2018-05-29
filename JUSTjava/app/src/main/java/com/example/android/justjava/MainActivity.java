package com.example.android.justjava;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringBufferInputStream;
import java.text.NumberFormat;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int no=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name=(EditText)findViewById(R.id.name_field);
        String value=name.getText().toString();
       // Log.v("MainActivity","Name:"+value);
        display(no);
        displayPrice(no*10,value);
        String pricemessage = afterdisplayPrice(value,no*10);

//      Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("*/*");
//            //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
//            intent.putExtra(Intent.EXTRA_SUBJECT,"Coffe Shop App"+name);
//            //intent.putExtra(Intent.EXTRA_STREAM, attachment);
//        intent.putExtra(Intent.EXTRA_TEXT,pricemessage);
//
//        if (intent.resolveActivity(getPackageManager()) != null) {
//                startActivity(intent);
//            }
        String smsNumber = "7****"; // E164 format without '+' sign
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, pricemessage);
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        if (sendIntent.resolveActivity(getPackageManager()) != null) {
            //Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
            //return;
            startActivity(sendIntent);
        }
        //startActivity(sendIntent);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void increment(View view)
    {

            no = no + 1;
        if(no<0)
        {
            display(no);
            //displayPrice(no*0);
        }
           else {
            display(no);
           // displayPrice(no * 10);
        }

    }
    public void decrement(View view)
    {
        no=no-1;
        if(no<0)
        {
            display(no);
            //displayPrice(no*0);
        }
        else {
            display(no);
            //displayPrice(no * 10);
        }
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+ number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number,String value) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(value+"\nYour Order is set to :-"+NumberFormat.getCurrencyInstance().format(number));
    }
    private String afterdisplayPrice(String name,int price)
    {
        TextView ThanksGiving = (TextView) findViewById(R.id.Thanks_Giving);
        ThanksGiving.setText("Thanks for placing your order,till then Enjoy Music!!!");
        String s=("Hey " + name + "\n your Order is of:- " + price);
        return(s);
    }

}

