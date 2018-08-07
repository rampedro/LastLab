package eecs1022.lastlab;

/**
 * Created by Pedram on 5/24/2018.
 */

public class Game {
    boolean secondpush = false;
    String nextName = "";
    String x = "Player X";
    String o = "Player O";
    int not = 0;
    String msg = "";
    String msg2 = "";
    int matchCount = 0;
    boolean occupied = false;
    boolean error = true;
    String nameX = "";
    String nameO = "";


    String board[][];

    public Game() {
        board = new String[][]{
                {"    .    ", "    .    ", "    .    "},
                {"    .    ", "    .    ", "    .    "},
                {"    .    ", "    .    ", "    .    "}
        };

    }


    public void namesAre(String nameX, String nameO){
        this.nameO = nameO;
        this.nameX = nameX;
    }

    public void setFirstName(String name) {
        if(name.equals("Player X")){
        nextName = nameX;
        }
        if(name.equals("Player O")){
            nextName = nameO;

        }
    }

    public void resetError() {
        msg = "";
    }


    public void setXO(String name, int r, int c) {

        // Initial selection is Player X --------------------------
        if (name.equals(x) && !secondpush) {

            if (board[r - 1][c - 1].equals("    .    ")) {
                resetError();
                msg2="";

                if (not % 2 == 0 && error) {
                    board[r - 1][c - 1] = "    X    ";
                    nextName = nameO;
                    not++;
                } else if(error) {
                    board[r - 1][c - 1] = "    O    ";
                    nextName = nameX;
                    not++;
                }
            } else if(error) {
                msg2 = " Error " + " Slot @ " + "(" + (r) + "," + (c) + ")" + " is Occupied ";
                decMatchCount();

            }
        }


        // Initial selection is Player O --------------------------

        if (name.equals(o) && !secondpush) {
            if (board[r - 1][c - 1].equals("    .    ")) {
                msg2 = "";
                resetError();
                if (not % 2 == 0 && error) {
                    board[r - 1][c - 1] = "    O    ";
                    nextName = nameX;
                    not++;
                } else if(error){
                    board[r - 1][c - 1] = "    X    ";
                    nextName = nameO;
                    not++;
                }
            } else if(error) {
                msg2 = " Error " + " Slot @ " + "(" + (r) + "," + (c) + ")" + " is Occupied ";
                decMatchCount();
            }
        }

    }


    public void incMatchCount() {
        matchCount++;
        if (!msg.equals("")) {
            secondpush = true;

        }
    }

    public void decMatchCount() {
//        if(matchCount == 3){
//            matchCount = 4;
//        }
        matchCount--;
    }


    // This is the method evaluating if there is a winner

    public void gameEval() {



        // Horizental line winners ----------------------

        for (int r = 0; r < board.length && error; r++) {
            for (int c = 0; c < board.length - 2; c++) {
                if (board[r][c].equals("    X    ") && board[r][c + 1].equals(board[r][c + 2]) && board[r][c].equals(board[r][c + 1])) {
                    msg = " the winner is " + nameX;
                    error = false;
                } else if (board[r][c].equals("    O    ") && board[r][c + 1].equals(board[r][c + 2]) && board[r][c].equals(board[r][c + 1])) {
                    msg = " the winner is " + nameO;
                    error = false;
                } else if (matchCount >= 9) {
                    msg = " The game is Tie ";
                    error = false;
                }
            }
        }


        // Vertical line winners ----------------------

        for (int c = 0; c < board.length && error; c++) {
            for (int r = 0; r < board.length - 2; r++) {
                if (board[r][c].equals("    X    ") && board[r + 1][c].equals(board[r + 2][c]) && board[r][c].equals(board[r + 1][c])) {
                    msg = " the winner is " + nameX;
                    error = false;
                } else if (board[r][c].equals("    O    ") && board[r + 1][c].equals(board[r + 2][c]) && board[r][c].equals(board[r + 1][c])) {
                    msg = " the winner is " + nameO;
                    error = false;
                } else if (matchCount >= 9) {
                    msg = " The game is Tie ";
                    error = false;
                }
            }
        }

        // Oblique line winners ----------------------
        int matchCounterX = 0;
        int matchCounterO = 0;

        for (int i = 0; i < board.length && error; i++) {

            if (board[i][i].equals("    X    ") || (board[0][2].equals(board[2][0]) && board[0][2].equals("    X    "))) {
                matchCounterX++;
                if (matchCounterX >= 3) {
                    msg = " the winner is " + nameX;
                    error = false;

                }

            } else if (board[i][i].equals("    O    ") || (board[0][2].equals(board[2][0]) && board[0][2].equals("    O    "))) {
                matchCounterO++;
                if (matchCounterO >= 3) {
                    msg = " the winner is " + nameO;
                    error = false;

                } else if (matchCount >= 9) {
                    msg = " The game is Tie ";
                    error = false;
                }


            }
        }
    }


    public String toString() {
        String s = "";

        if (!error && secondpush) {
            msg2 = " Game is Already Over";
        }

            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board.length; c++) {
                    s += board[r][c];
                }
                s += "\n";
            }
            s += "\n";
            s += " Next Player is " + nextName;

                s += "\n" + msg;

                s += "\n" + msg2;




        return s;

    }
}

