package life;

import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        EvolutionPerpetual evolutionPerpetual = new EvolutionPerpetual(size);

        /*
        Evolution evolution = new Evolution(size);
        try {
            evolution.evolve();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        EventQueue.invokeLater(() -> {
            var gameOfLife = new GameOfLife();
            gameOfLife.universe = evolution.generations[0];
            Thread t2 = new Thread(() -> {
                for (int i = 1; i <= evolution.steps; i++) {

                    try {
                        gameOfLife.evolve(evolution.generations[i], i);
                        Thread.currentThread().sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            t2.start();

        });
         */

        //EventQueue.invokeLater(() -> {
            var gameOfLife = new GameOfLife();
            gameOfLife.universe = evolutionPerpetual.universe;

            int counter = 0;
            while (true) {
                try {

                    if (gameOfLife.isReset()) {
                        evolutionPerpetual = new EvolutionPerpetual(size);
                        gameOfLife.universe = evolutionPerpetual.universe;
                        counter = 0;
                        gameOfLife.reset();
                    }

                    if (gameOfLife.isPaused()) {
                        try {
                            Thread.sleep(100L);
                            continue;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }



                    counter++;
                    evolutionPerpetual.evolve();
                    gameOfLife.universe = evolutionPerpetual.universe;
                    gameOfLife.evolve(counter);
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            /*
            Thread t2 = new Thread(() -> {
                int counter = 0;
                while (true) {
                    try {
                        if(gameOfLife.isPaused) {
                            try {
                                Thread.sleep(100L);
                                continue;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        counter++;
                        evolutionPerpetual.evolve();
                        gameOfLife.universe = evolutionPerpetual.universe;
                        gameOfLife.evolve(counter);
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            t2.start();
             */

        //});
        /*
        class sub extends Thread {
            @Override
            public void run() {
                var gameOfLife2 = new GameOfLife();
                gameOfLife2.universe = universe;
                int counter = 0;
                while (true) {
                    counter++;
                    universe = Evolution.nextStep(universe);
                    gameOfLife2.evolve(universe, counter);
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
         */
    }
}
