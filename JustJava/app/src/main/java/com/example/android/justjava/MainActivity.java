package com.example.android.justjava;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int pricePerCoffee = 5;
    int costOfChocolate = 2;
    int costOfWhippedCream = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasChocolate    whether customer wants chocolate
     * @param hasWhippedCream whether customer wants whipped cream
     * @return total price
     */
    private int calculatePrice(boolean hasChocolate, boolean hasWhippedCream) {
        int customCoffee = pricePerCoffee;
        if (hasChocolate) {
            customCoffee = customCoffee + costOfChocolate;
        }
        if (hasWhippedCream) {
            customCoffee = customCoffee + costOfWhippedCream;
        }
        return quantity * customCoffee;
    }

    /**
     * Creates order summary
     *
     * @param price           total order price
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether the user wants chocolate or not
     * @return order summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String customerName) {
        String summary = getString(R.string.order_summary_name, customerName);
        summary += "\nAdd whipped cream? " + addWhippedCream;
        summary += "\nAdd chocolate? " + addChocolate;
        summary += "\nQuantity: " + quantity;
        summary += "\nTotal: $" + price;
        summary += "\n" + getString(R.string.thank_you);
        return summary;
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            // run toast notification
            Toast.makeText(
                    this,
                    "You cannot have less then 1 coffee",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(
                    this,
                    "You cannot have more then 100 coffees",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.name_field);
        String customerName = name.getText().toString();

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        int price = calculatePrice(hasChocolate, hasWhippedCream);
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, customerName);

        displayMessage(message);

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:me@example.com"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + customerName);
//        intent.putExtra(Intent.EXTRA_TEXT, message);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
    }
}