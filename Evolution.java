package life;

import java.io.IOException;

public class Evolution {
    int steps;
    Universe[] generations;

    /*
    //Reprecated as input is now size only
    public Evolution(int steps) {
        this.steps = steps;
        this.generations = new Universe[this.steps + 1];
    }
     */

    public Evolution(int size) {
        this.steps = 10;
        this.generations = new Universe[this.steps + 1];
        this.generations[0] = new Universe(size);
    }

    public Evolution (int size, long seed, int steps) {
        this.steps = steps;
        this.generations = new Universe[steps + 1];
        this.generations[0] = new Universe(size, seed);
    }

    public void evolve(Universe universe) {
        this.generations[0] = universe;
        for (int i = 1; i < steps; i++) {
            this.generations[i] = Evolution.nextStep(this.generations[i - 1]);
        }
    }

    public void evolve() throws InterruptedException {
        if (this.generations[0] == null) {
            throw new IllegalStateException("Initial universe not initiated, suppy Universe parameter");
        } else {
            /*
            {
                System.out.printf("Generation: #%d\n", 0);
                System.out.printf("Alive: %d\n", this.generations[0].getAlive());
                this.generations[0].toPrint();
            }
             */

            for (int i = 1; i <= steps; i++) {
                this.generations[i] = Evolution.nextStep(this.generations[i - 1]);

                try {
                    if (System.getProperty("os.name").contains("Windows"))
                        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                    else
                        Runtime.getRuntime().exec("clear");
                }   catch (IOException | InterruptedException e) {}
                /*
                System.out.printf("Generation: #%d\n", i);
                System.out.printf("Alive: %d\n", this.generations[i].getAlive());
                this.generations[i].toPrint();
                Thread.sleep(100);
                 */
            }
        }
    }

    public void printFinal() {
        this.generations[steps].toPrint();
    }

    public static Universe nextStep(Universe prevUniverse) {
        int n = prevUniverse.size;
        Universe nextUniverse = new Universe(n);
        //TODO: rewrite the logic using array and getSafe coordinate function
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cell = prevUniverse.matrix[i][j] == 'O' ? 1 : 0;
                int no   = prevUniverse.matrix[i - 1 < 0 ? n + i - 1 : i - 1][j] == 'O' ? 1 : 0;;
                int ne   = prevUniverse.matrix[i - 1 < 0 ? n + i - 1 : i - 1][(j + 1) % n] == 'O' ? 1 : 0;;
                int ea   = prevUniverse.matrix[i][(j + 1) % n] == 'O' ? 1 : 0;
                int se   = prevUniverse.matrix[(i + 1) % n][(j + 1) % n] == 'O' ? 1 : 0;;
                int so   = prevUniverse.matrix[(i + 1) % n][j] == 'O' ? 1 : 0;;
                int sw   = prevUniverse.matrix[(i + 1) % n][j - 1 < 0 ? n + j - 1 : j - 1] == 'O' ? 1 : 0;;
                int we   = prevUniverse.matrix[i][j - 1 < 0 ? n + j - 1 : j - 1] == 'O' ? 1 : 0;;
                int nw   = prevUniverse.matrix[i - 1 < 0 ? n + i - 1 : i - 1][j - 1 < 0 ? n + j - 1 : j - 1] == 'O' ? 1 : 0;
                int sumOfAliveNeighbours = no + ne + ea + se + so + sw + we + nw;
                //logic: alive & 2 < sum < 3 => alive ; dead & sum == 3 =? alive
                if (cell == 1) {
                    if (sumOfAliveNeighbours == 2 || sumOfAliveNeighbours == 3) {
                        nextUniverse.matrix[i][j] = 'O';
                    } else {
                        nextUniverse.matrix[i][j] = ' ';
                    }
                } else {
                    if (sumOfAliveNeighbours == 3) {
                        nextUniverse.matrix[i][j] = 'O';
                    } else {
                        nextUniverse.matrix[i][j] = ' ';
                    }
                }
            }
        }
        return nextUniverse;
    }
}
