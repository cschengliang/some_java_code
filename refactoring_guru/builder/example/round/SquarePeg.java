package refactoring_guru.builder.example.round;

public class SquarePeg {
    private final double with;

    public SquarePeg(double with) {
        this.with = with;
    }

    public double getWith() {
        return with;
    }

    public double getSquare() {
        double result;
        result = Math.pow(this.with, 2);
        return result;
    }
}
