package com.erank.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     /*
    DESCRIPTION OF VARIABLES

    TAG ---------> Used for log debugging
    PLAYER_X ----> Variable to identify player turn
    TURN_COUNT --> Variable to keep count of number of turns
    b00 - b22 ---> Button for each 3x3 box
    tvInfo ------> Information regarding game status
    boardStatus -> 2-d array to identify board status
        -1 means, No one has played on that box yet
        0 means, Player 0 has played on that box
        1 means, Player X has played on that box
    */

    /*
    DESCRIPTION OF METHODS

    onClick --------> Called whenever any box or reset button is clicked.
        This method places either X or 0 according to player turn
        and updates other information

    checkWinner ----> This method checks the board status and identifies the winner
        It checks for all the 3 rows, 3 columns and 2 diagonals

    resetBoard -----> This method is called whenever reset button is pressed.
        updates everything to initial values of game

    enableAllBoxes -> This method can either enable or disable all boxes

    setInfo --------> This method updates the string in TextView

    result ---------> This method works whenever a player wins or game draws

    initializeBoardStatus -> Method which set the value of integer array to -1

    */

    private final static String TAG = MainActivity.class.getSimpleName();

    public static final  String PLAYER_1_EXTRA = "PLAYER_1";
    public static final String PLAYER_2_EXTRA = "PLAYER_2";

    private final IntentFilter intentFilter = new IntentFilter();

    private String p1Name;
    private String p2Name;

    boolean PLAYER_X = true;

    int TURN_COUNT = 0;

//    buttons
    private Button b00,b01,b02;
    private Button b10,b11,b12;
    private Button b20,b21,b22;

    Button bReset;

    TextView tvInfo;

//    logical matrix
    int[][] boardStatus = new int[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b00 = findViewById(R.id.b00);
        b01 = findViewById(R.id.b01);
        b02 = findViewById(R.id.b02);

        b10 = findViewById(R.id.b10);
        b11 = findViewById(R.id.b11);
        b12 = findViewById(R.id.b12);

        b20 = findViewById(R.id.b20);
        b21 = findViewById(R.id.b21);
        b22 = findViewById(R.id.b22);

        bReset = findViewById(R.id.bReset);
        tvInfo = findViewById(R.id.tvInfo);

        bReset.setOnClickListener(this);

        b00.setOnClickListener(this);
        b01.setOnClickListener(this);
        b02.setOnClickListener(this);

        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);

        b20.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);

        initializeBoardStatus();

        Intent intent = getIntent();

        p1Name = intent.getStringExtra(PLAYER_1_EXTRA)+"(X)";
        p2Name = intent.getStringExtra(PLAYER_2_EXTRA)+"(O)";


        // Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "Inside onClick");

        boolean resetButtonPressed = false;

        switch (view.getId()){
            case R.id.b00:
                if(PLAYER_X){
                    b00.setText("X");
                    boardStatus[0][0] = 1;
                }
                else{
                    b00.setText("O");
                    boardStatus[0][0] = 0;
                }
                b00.setEnabled(false);
                break;

            case R.id.b01:
                if(PLAYER_X){
                    b01.setText("X");
                    boardStatus[0][1] = 1;
                }
                else{
                    b01.setText("O");
                    boardStatus[0][1] = 0;
                }
                b01.setEnabled(false);
                break;

            case R.id.b02:
                if(PLAYER_X){
                    b02.setText("X");
                    boardStatus[0][2] = 1;
                }
                else{
                    b02.setText("O");
                    boardStatus[0][2] = 0;
                }
                b02.setEnabled(false);
                break;

            case R.id.b10:
                if(PLAYER_X){
                    b10.setText("X");
                    boardStatus[1][0] = 1;
                }
                else{
                    b10.setText("O");
                    boardStatus[1][0] = 0;
                }
                b10.setEnabled(false);
                break;

            case R.id.b11:
                if(PLAYER_X){
                    b11.setText("X");
                    boardStatus[1][1] = 1;
                }
                else{
                    b11.setText("O");
                    boardStatus[1][1] = 0;
                }
                b11.setEnabled(false);
                break;

            case R.id.b12:
                if(PLAYER_X){
                    b12.setText("X");
                    boardStatus[1][2] = 1;
                }
                else{
                    b12.setText("O");
                    boardStatus[1][2] = 0;
                }
                b12.setEnabled(false);
                break;

            case R.id.b20:
                if(PLAYER_X){
                    b20.setText("X");
                    boardStatus[2][0] = 1;
                }
                else{
                    b20.setText("O");
                    boardStatus[2][0] = 0;
                }
                b20.setEnabled(false);
                break;

            case R.id.b21:
                if(PLAYER_X){
                    b21.setText("X");
                    boardStatus[2][1] = 1;
                }
                else{
                    b21.setText("O");
                    boardStatus[2][1] = 0;
                }
                b21.setEnabled(false);
                break;

            case R.id.b22:
                if(PLAYER_X){
                    b22.setText("X");
                    boardStatus[2][2] = 1;
                }
                else{
                    b22.setText("O");
                    boardStatus[2][2] = 0;
                }
                b22.setEnabled(false);
                break;

            case R.id.bReset:
                resetButtonPressed = true;
                break;

            default:
                break;

        }

        if(resetButtonPressed){
            resetBoard();
        }
        else{
            TURN_COUNT ++;
            PLAYER_X = !PLAYER_X;

            if(PLAYER_X){
                setInfo("Player X turn");
            }
            else {
                setInfo("Player 0 turn");
            }

            if(TURN_COUNT==9){
                result("Game Draw");
            }

            checkWinner();
        }
    }

    private void checkWinner(){

        Log.d(TAG, "Inside checkWinner");

        //Horizontal --- rows
        for(int i=0; i<3; i++){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if (boardStatus[i][0]==1){
                    result(p1Name+" winner\n" + (i+1)+" row");
                    break;
                }
                else if (boardStatus[i][0]==0) {
                    result(p2Name+" winner\n" + (i+1)+" row");
                    break;
                }
            }
        }

        //Vertical --- columns
        for(int i=0; i<3; i++){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if (boardStatus[0][i]==1){
                    result(p1Name+" winner\n" + (i+1)+" column");
                    break;
                }
                else if (boardStatus[0][i]==0) {
                    result(p2Name+" winner\n" + (i+1)+" column");
                    break;
                }
            }
        }

        //First diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if (boardStatus[0][0]==1){
                result(p1Name+" winner\nFirst Diagonal");
            }
            else if (boardStatus[0][0]==0) {
                result(p2Name+" winner\nFirst Diagonal");
            }
        }

        //Second diagonal
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if (boardStatus[0][2]==1){

                result(p1Name + " winner\nSecond Diagonal");
            }
            else if (boardStatus[0][2]==0) {
                result("Player 0 winner\nSecond Diagonal");
            }
        }
    }

    private void enableAllBoxes(boolean value){
        Log.d(TAG, "Inside enableAllBoxes");
        b00.setEnabled(value);
        b01.setEnabled(value);
        b02.setEnabled(value);

        b10.setEnabled(value);
        b11.setEnabled(value);
        b12.setEnabled(value);

        b20.setEnabled(value);
        b21.setEnabled(value);
        b22.setEnabled(value);
    }

    private void result(String winner){
        Log.d(TAG, "Inside result");

        setInfo(winner);
        enableAllBoxes(false);
    }

    private void resetBoard(){
        Log.d(TAG, "Inside resetBoard");
        b00.setText("");
        b01.setText("");
        b02.setText("");

        b10.setText("");
        b11.setText("");
        b12.setText("");

        b20.setText("");
        b21.setText("");
        b22.setText("");

        enableAllBoxes(true);

        PLAYER_X = true;
        TURN_COUNT = 0;

        initializeBoardStatus();

        setInfo("Start Again!!!");

        Toast.makeText(this,"Board Reset",Toast.LENGTH_SHORT).show();
    }

    private void setInfo(String text){
        tvInfo.setText(text);
    }

    private void initializeBoardStatus(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                boardStatus[i][j] = -1;
            }
        }

    }
}
