package ro.pub.cs.systems.eim.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest02MainActivity extends AppCompatActivity {

    private Button serverStartButton;
    private Button clientConnectButton;
    private EditText portEditText;
    private EditText currencyEditText;
    private TextView resultTextView;

    private ServerThread serverThread;
    private ClientThread clientThread;

    private StartButtonClickListener connectButtonClickListener = new StartButtonClickListener();

    private class StartButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {

            serverThread = new ServerThread(5000);
            if (serverThread.getServerSocket() == null) {
                Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();
        }

    }

    private CurrencyButtonClickListener initalizeButtonClickListener = new CurrencyButtonClickListener();
    private class CurrencyButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String clientPort = portEditText.getText().toString();
            if (clientPort == null || clientPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Client connection parameters should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (serverThread == null || !serverThread.isAlive()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }
            String currency = currencyEditText.getText().toString();
            if (currency == null || currency.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Parameters from client (city / information type) should be filled", Toast.LENGTH_SHORT).show();
                return;
            }

            resultTextView.setText(Constants.EMPTY_STRING);

            clientThread = new ClientThread(Integer.parseInt(clientPort), currency, resultTextView);
            clientThread.start();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);

        serverStartButton = (Button)findViewById(R.id.server_start_button);
        clientConnectButton = (Button)findViewById(R.id.client_connect_button);
        portEditText = (EditText)findViewById(R.id.port_edit_text);
        currencyEditText = (EditText)findViewById(R.id.currency_edit_text);
        resultTextView = (TextView)findViewById(R.id.result_text_view);

        serverStartButton.setOnClickListener(connectButtonClickListener);
        clientConnectButton.setOnClickListener(initalizeButtonClickListener );
    }
}