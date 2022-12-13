public class main {

    public static void main(String[] args) {
        board b1 = new board();
        board b2 = new board("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2");

        System.out.println(b1);
        System.out.println(b2);
    }
}
