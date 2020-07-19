package life;

import java.util.Random;
import java.util.Scanner;

class Universe {
    char[][] matrix;
    Scanner scanner;
    int size;
    long seed;
    Random rand;

    //constructor to build universe of n x n size
    public Universe(int size) {
        this.size = size;
        this.matrix = new char[this.size][this.size];
        this.scanner = new Scanner(System.in);
        this.rand = new Random();
        this.initiate();
    }

    //overloaded constructor with optional seed for Random generator
    public Universe(int size, long seed) {
        this(size);
        this.seed = seed;
        this.rand = new Random(this.seed);
        this.initiate();
    }

    //Initiate the initial Random state
    public void initiate() {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                this.matrix[i][j] = this.rand.nextBoolean() ? 'O' : ' ';
            }
        }
    }

    //to print
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                res.append(this.matrix[i][j]);
            }
            res.append("\n");
        }
        return res.toString();
    }

    public void toPrint() {
        System.out.println(this.toString());
    }

    public int getAlive() {
        int count = 0;
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                count = this.matrix[i][j] == 'O' ? count + 1 : count;
            }
        }
        return count;
    }
}
