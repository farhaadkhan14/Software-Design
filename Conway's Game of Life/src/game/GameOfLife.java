/*
Assignment 1
************
To run the Game Of Life with presets, one line 35, set List<Point> currentGeneration equal to
to any of the following:
glider(); pulsar(); beacon(); blinker();

*/

package game;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import java.util.concurrent.TimeUnit;
import static java.util.stream.Collectors.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public interface GameOfLife {
  public enum CellState {
    DEAD, ALIVE
  }

  class gui extends JPanel{
    private int row = 30;
    private int col = 30;
    private JFrame frame = new JFrame();
    private JPanel contentPane = new JPanel();
    private JPanel[][] gridOfCells;
    public gui() throws InterruptedException {

      List<Point> currentGeneration = pulsar();
      setGUI(row,col);

      for(Point index: currentGeneration) {
        gridOfCells[index.x][index.y].setBackground(Color.black);
      }

      while(true){
        contentPane.repaint();
        for(Point index: currentGeneration) {
          gridOfCells[index.x][index.y].setBackground(new JPanel().getBackground());
        }
        currentGeneration= nextGeneration(currentGeneration);
        for(Point index: currentGeneration) {
          gridOfCells[index.x][index.y].setBackground(Color.BLUE);
        }
        TimeUnit.MILLISECONDS.sleep(700);
      }
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(getBackground());
      g.fillRect(0, 0, getWidth(), getHeight());
      g.setColor(Color.RED);
      int coordx = getWidth();
      int coordy = getHeight();
      g.drawLine(coordx, 0, coordx, getHeight());
      g.drawLine(0, coordy, getWidth(), coordy);
    }

    private void setGUI(int numRows, int numCols){
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      contentPane.setBackground(Color.BLACK);
      contentPane.setLayout(new GridLayout(numRows, numCols, 1, 1));
      gridOfCells = new JPanel[numRows][numCols];

      for (int i = 0; i < gridOfCells.length; i++) {
        for (int j = 0; j < gridOfCells[i].length; j++) {
          gridOfCells[i][j] = new JPanel();
        }
      }

      for (int i = 0; i < gridOfCells.length; i++) {
        for (int j = 0; j < gridOfCells[i].length; j++) {
          contentPane.add(gridOfCells[i][j]);
        }
      }

      frame.add(contentPane);
      frame.setSize(700, 700);
      frame.setLocationRelativeTo(null);
      frame.setTitle("GOL");
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private List<Point> glider(){
      return List.of(new Point(5, 5), new Point(6, 6), new Point(7, 4), new Point(7, 5), new Point(7, 6));
    }

    private List<Point> beacon(){
      return List.of(new Point(0,0), new Point(0,1), new Point(1,0), new Point(1,1),
        new Point(2,2), new Point(2,3), new Point(3,2), new Point(3,3));
    }

    private List<Point> blinker(){
      return List.of(new Point(0,1), new Point(1,1), new Point(2,1));
    }

    private List<Point> pulsar(){
      return List.of(new Point(2, 4), new Point(2, 5), new Point(2, 6), new Point(2, 10), new Point(2, 11),
        new Point(2, 12), new Point(4, 2), new Point(4, 7), new Point(4, 9),
        new Point(4, 14), new Point(5, 2), new Point(5, 7), new Point(5, 9),
        new Point(5, 14), new Point(6, 2), new Point(6, 7), new Point(6, 9),
        new Point(6, 14), new Point(7, 4), new Point(7, 5), new Point(7, 6),
        new Point(7, 10), new Point(7, 11), new Point(7, 12), new Point(9, 4),
        new Point(9, 5), new Point(9, 6), new Point(9, 10), new Point(9, 11),
        new Point(9, 12), new Point(10, 2), new Point(10, 7), new Point(10, 9),
        new Point(10, 14), new Point(11, 2), new Point(11, 7), new Point(11, 9),
        new Point(11, 14), new Point(12, 2), new Point(12, 7), new Point(12, 9),
        new Point(12, 14), new Point(14, 4), new Point(14, 5), new Point(14, 6),
        new Point(14, 10), new Point(14, 11), new Point(14, 12));
    }
  }

  public static CellState computeCellState(CellState currentState, int numberOfLiveNeighbors) {
    return numberOfLiveNeighbors == 3 || numberOfLiveNeighbors == 2 &&
      currentState == CellState.ALIVE ? CellState.ALIVE : CellState.DEAD;
  }

  public static List<Point> generateSignalsForPosition(Point position) {
    int x = position.x;
    int y = position.y;

    return List.of(new Point(x - 1,y - 1), new Point(x - 1, y), new Point(x - 1, y + 1),
      new Point(x, y - 1), new Point(x, y + 1),
      new Point(x + 1, y - 1), new Point(x + 1, y), new Point(x + 1, y + 1));
  }

  public static List<Point> generateSignalsForPositions(List<Point> positions) {
    return positions.stream()
      .flatMap(position -> generateSignalsForPosition(position).stream())
      .collect(toList());
  }

  public static Map countSignal(List<Point> positions) {
    Map<Point, Integer> signalCount = new HashMap<Point, Integer>();
    for (Point index : positions) {
      if (signalCount.containsKey(index)) {
        signalCount.put(index, signalCount.get(index) + 1);
      }
      else
        signalCount.put(index, 1);
    }
    return signalCount;
  }

  public static List<Point> nextGeneration(List<Point> positions) {
    List<Point> nextGen = new ArrayList<Point>();
    List<Point> allPossibleNeighbors = generateSignalsForPositions(positions);
    List<Point> allCells = Stream.concat(allPossibleNeighbors.stream(), positions.stream())
      .collect(Collectors.toList());

    int aliveNeighbors;
    for (Point cell : allCells) {
      aliveNeighbors=0;
      List<Point> neighbors = generateSignalsForPosition(cell);
      for(Point neighbor: neighbors) {
        for(Point aliveCell: positions) {
          if(neighbor.equals(aliveCell)) {
            aliveNeighbors++;
          }
        }
      }
      for(Point aliveCell: positions) {
        if (cell.equals(aliveCell)) {
          if(computeCellState(CellState.ALIVE, aliveNeighbors).equals(CellState.ALIVE)){
            nextGen.add(cell);
          }
        }
        else{
          if(computeCellState(CellState.DEAD, aliveNeighbors).equals(CellState.ALIVE)){
            nextGen.add(cell);
          }
        }
      }
    }

    nextGen = nextGen.stream().distinct().collect(Collectors.toList());

    Collections.sort(nextGen, new Comparator<Point>() {
      public int compare(Point p1, Point p2) {
        int result =  Double.compare(p1.getX(), p2.getX());
        if(result ==0){
          result = Double.compare(p1.getY(), p2.getY());
        }
        return result;
      }
    });
    return nextGen;
  }

  public static void main(String[] args) throws InterruptedException {
    gui thing = new gui();
  }
}