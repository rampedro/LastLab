package eecs1022.lastlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Lab6Activity extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab6);
        game = new Game();
    }

    public String getItem(int id){
        View view = findViewById(id);
        EditText editText = (EditText) view;
        return String.valueOf(editText.getText());
    }

    public void setItem(int id, String newContent){
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContent);
    }

    public String getItemSelected(int id){
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        return String.valueOf(spinner.getSelectedItem());
    }

    public void buttStart(View view){
        String nameX = getItem(R.id.playerX);
        String nameO = getItem(R.id.playerO);
        game = new Game();

        game.namesAre(nameX,nameO);
        String firstName = getItemSelected(R.id.spinFirst);
        game.setFirstName(firstName);
        setItem(R.id.result,game.toString());


    }

    public void buttPlay(View view){
        game.incMatchCount();
        int r = Integer.parseInt(getItemSelected(R.id.spinRow));
        int c = Integer.parseInt(getItemSelected(R.id.spinCol));
        String firstName = getItemSelected(R.id.spinFirst);
        game.setXO(firstName,r,c);
        game.gameEval();
        setItem(R.id.result,game.toString());

    }


}
