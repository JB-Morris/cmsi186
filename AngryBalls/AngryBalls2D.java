import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * AngryBalls2D is a graphics "shell" for an Angry Balls simulation.
 * Feel free to read this code to learn what it does, but don't feel
 * bad if it's too hard to handle at this stage.
 */
public class AngryBalls2D {

    private static class Snapshot {
        public double redBallRadius;
        public double redBallX;
        public double redBallY;
        
        public double blueBallRadius;
        public double blueBallX;
        public double blueBallY;
    }

    private static class Field extends JComponent {

        private boolean showTracks = true;
        private List<Snapshot> snapshots = new ArrayList<Snapshot>();

        public Field(boolean showTracks) {
            super();
            this.showTracks = showTracks;
        }

        public void addSnapshot(Snapshot snapshot) {
            snapshots.add(snapshot);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.translate(0, getHeight());
            g.setColor(Color.WHITE);
            g.fillRect(0, -getHeight(), getWidth(), getHeight());
            if (showTracks) {
                for (Snapshot snapshot: snapshots) {
                    paintSnapshot(g, snapshot);
                }
            } else {
                if (!snapshots.isEmpty()) {
                    paintSnapshot(g, snapshots.get(snapshots.size() - 1));
                }
            }
        }

        private void paintSnapshot(Graphics g, Snapshot snapshot) {
            g.setColor(Color.RED);
            paintBall(g, snapshot.redBallX, snapshot.redBallY, snapshot.redBallRadius);
            g.setColor(Color.BLUE);
            paintBall(g, snapshot.blueBallX, snapshot.blueBallY, snapshot.blueBallRadius);
        }

        private void paintBall(Graphics g, double x, double y, double r) {
            int diameter = (int)(r * 2);
            g.drawOval((int)(x - r), (int)(-y - r), diameter, diameter);            
        }
    
    }

    private static class Updater implements Runnable {
        
        private Field field;
        private Snapshot snapshot;
        
        public Updater(Field field, Snapshot snapshot) {
            this.field = field;
            this.snapshot = snapshot;
        }
        
        @Override
        public void run() {
            field.addSnapshot(snapshot);
        }
    }

    private static final long DEFAULT_GRAIN = 25;

    private static long getGrain(String arg) {
        try {
            return Long.parseLong(arg);
        } catch (NumberFormatException nfe) {
            System.out.println("Unrecognized grain argument; defaulting to 1 second.");
            return DEFAULT_GRAIN;
        }
    }

    public static void main(String[] args) {
        long grain = args.length == 0 ? DEFAULT_GRAIN : getGrain(args[0]);

        JFrame angryFrame = new JFrame("Angry Balls 2D");
        angryFrame.setSize(1280, 720);
        angryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Field field = new Field(args.length > 1);
        angryFrame.setContentPane(field);
        angryFrame.setVisible(true);
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
            boolean snapshotsDone = false;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if ("end".equals(line)) {
                    snapshotsDone = true;
                } else if (snapshotsDone) {
                    System.out.println(line);
                } else {
                    String[] snapshotData = line.split(" ");
                    Snapshot snapshot = new Snapshot();
                    snapshot.redBallRadius = Double.parseDouble(snapshotData[0]);
                    snapshot.redBallX = Double.parseDouble(snapshotData[1]);
                    snapshot.redBallY = Double.parseDouble(snapshotData[2]);
                    snapshot.blueBallRadius = Double.parseDouble(snapshotData[3]);
                    snapshot.blueBallX = Double.parseDouble(snapshotData[4]);
                    snapshot.blueBallY= Double.parseDouble(snapshotData[5]);
                    SwingUtilities.invokeLater(new Updater(field, snapshot));
                    try {
                        Thread.sleep(grain);
                    } catch (InterruptedException ie) {
                        // Non-critical; ignore.
                    }
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

}
