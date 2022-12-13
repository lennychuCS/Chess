import java.util.HashMap;

public class board {
    private final int boardSize = 8;
    private char[][] board = new char[boardSize][boardSize];

    private int fullMoveCount;
    private int halfMoveCount;
    private boolean whitesTurn;
    private boolean[] castlesLeft = new boolean[4];
    private String enPassantSquare;
    private static final String startingPos = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public board(){
        this(startingPos);
    }

    public board(String fen){
        String[] splitFen = fen.split(" ");

        //Populate Board
        placesPiecesFromFen(splitFen[0]);

        //Update Current Turn
        whitesTurn = splitFen[1].equals("w");

        //Update Available Castles
        castlesLeft[0] = splitFen[2].contains("K");
        castlesLeft[1] = splitFen[2].contains("Q");
        castlesLeft[2] = splitFen[2].contains("k");
        castlesLeft[3] = splitFen[2].contains("q");

        //Update En Passant
        enPassantSquare = splitFen[3].equals("-") ? null : splitFen[3];

        //Update Move Counts
        halfMoveCount = Integer.parseInt(splitFen[4]);
        fullMoveCount = Integer.parseInt(splitFen[5]);
    }

    private void placesPiecesFromFen(String fen){
        for(int rank = 0; rank < boardSize; rank++){
            for(int file = 0; file < boardSize; file++){
                board[rank][file] = ' ';
            }
        }

        String[] splitFen = fen.split("/");

        for(int rank = 0; rank < boardSize; rank++){
            int file = 0;
            String curString = splitFen[rank];
            while(file < boardSize-1){
                String temp = curString.substring(0,1);
                curString = curString.substring(1);
                if(temp.matches("[0-9]")){
                    file += Integer.parseInt(temp);
                } else {
                    board[rank][file] = temp.charAt(0);
                    file++;
                }
            }
        }
    }

    public char getSquare(int rank, int file){
        if(rank < 0 || rank > boardSize-1 || file < 0 || file > boardSize-1){
            throw new IllegalArgumentException("Out of bounds of Board");
        }

        return board[rank][file];
    }

    public char getCurrentTurn(){
        return whitesTurn ? 'w' : 'b';
    }

    public int getCurrentMoveNumber(){
        return fullMoveCount;
    }

    public int getHalfMoveCount(){
        return halfMoveCount;
    }

    public String getEnPassantSquare(){
        return enPassantSquare;
    }

    public char[][] getBoard(){
        return board;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int rank = 0; rank < boardSize; rank++){
            for(int file = 0; file < boardSize; file++){
                sb.append(board[rank][file]);
                if(file != boardSize-1){
                    sb.append(",");
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
