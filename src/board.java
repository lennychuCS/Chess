public class board {
    //Constants
    private final int BOARD_SIZE = 8;
    private static final String STARTING_POS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    //Instance Variables
    private chessWindow frame;
    private piece[][] board = new piece[BOARD_SIZE][BOARD_SIZE];

    private int fullMoveCount;
    private int halfMoveCount;
    private boolean whitesTurn;
    private boolean[] castlesLeft = new boolean[4];
    private String enPassantSquare;

    public board(){
        this(STARTING_POS);
    }

    public board(String fen){
        frame = new chessWindow();

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
        for(int rank = 0; rank < BOARD_SIZE; rank++){
            for(int file = 0; file < BOARD_SIZE; file++){
                board[rank][file] = null;
            }
        }

        String[] splitFen = fen.split("/");

        for(int rank = 0; rank < BOARD_SIZE; rank++){
            int file = 0;
            String curString = splitFen[rank];
            while(file < BOARD_SIZE){
                String temp = curString.substring(0,1);
                curString = curString.substring(1);
                if(temp.matches("[0-9]")){
                    file += Integer.parseInt(temp);
                } else {
                    board[rank][file] = new piece(temp.charAt(0));
                    file++;
                }
            }
        }
    }

    public piece getSquare(int rank, int file){
        if(rank < 0 || rank > BOARD_SIZE-1 || file < 0 || file > BOARD_SIZE-1){
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

    public piece[][] getBoard(){
        return board;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int rank = 0; rank < BOARD_SIZE; rank++){
            for(int file = 0; file < BOARD_SIZE; file++){
                if(board[rank][file] != null){
                    sb.append(board[rank][file].toString());
                } else {
                    sb.append(" ");
                } if(file != BOARD_SIZE-1){
                    sb.append(",");
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
