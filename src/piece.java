public class piece {
    // TODO Not Implemented Yet
    // Will have general class and then each piece will extend this.
    private char type;

    public piece(){
        this('P');
    }

    public piece(char t){
        type = t;
    }

    public String toString(){
        return ""+type;
    }
}
