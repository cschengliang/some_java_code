package refactoring_guru.builder.example.round;

public class Demo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("Round peg r5 fits round hole r5");
        }
        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg larageSqPeg = new SquarePeg(20);

        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter larageSqPegAdapter = new SquarePegAdapter(larageSqPeg);

        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5");
        }
        if (!hole.fits(larageSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5 ");
        }
    }
}
