package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

import static game.GameOfLife.CellState.*;
import static game.GameOfLife.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GameOfLifeTest{

  @Test
  void cellStaysDeadTests() {
  assertAll(
    () -> assertEquals(DEAD, GameOfLife.computeCellState(DEAD, 0)),
    () -> assertEquals(DEAD, GameOfLife.computeCellState(DEAD, 1)),
    () -> assertEquals(DEAD, GameOfLife.computeCellState(DEAD, 2)),
    () -> assertEquals(DEAD, GameOfLife.computeCellState(DEAD, 5)),
    () -> assertEquals(DEAD, GameOfLife.computeCellState(DEAD, 8))
  );
 }

  @Test
  void liveCellDiesTests(){
    assertAll(
      () -> assertEquals(DEAD, GameOfLife.computeCellState(ALIVE, 1)),
      () -> assertEquals(DEAD, GameOfLife.computeCellState(ALIVE, 4)),
      () -> assertEquals(DEAD, GameOfLife.computeCellState(ALIVE, 8))
    );
  }

  @Test
  void cellStaysAliveTests(){
   assertAll(
     () -> assertEquals(ALIVE, GameOfLife.computeCellState(ALIVE, 2)),
     () -> assertEquals(ALIVE, GameOfLife.computeCellState(ALIVE, 3))
   );
  }

  @Test
  void deadCellResurrects()
  {
   assertAll(
     () -> assertEquals(ALIVE, GameOfLife.computeCellState(DEAD, 3))
   );
  }

  @Test
  void generateSignalForNoPositions(){
   assertEquals(List.of(), GameOfLife.generateSignalsForPositions(List.of()));
  }

  @Test
  void generateSignalForOnePosition() {
   assertEquals(List.of(new Point(2, 2), new Point(2, 3), new Point(2, 4),
     new Point(3, 2), new Point(3, 4),
     new Point(4, 2), new Point(4, 3), new Point(4, 4)),
     GameOfLife.generateSignalsForPosition(new Point(3, 3)));
  }

  @Test
  void generateSignalForAnotherPosition() {
   assertEquals(List.of(new Point(1, 3), new Point(1, 4), new Point(1, 5),
     new Point(2, 3), new Point(2, 5),
     new Point(3, 3), new Point(3, 4), new Point(3, 5)),
     GameOfLife.generateSignalsForPosition(new Point(2, 4)));
  }

  @Test
  void generateSignalForPosition00() {
    assertEquals(List.of(new Point(-1, -1), new Point(-1, 0), new Point(-1, 1),
      new Point(0, -1), new Point(0, 1),
      new Point(1, -1), new Point(1, 0), new Point(1, 1)),
      GameOfLife.generateSignalsForPosition(new Point(0, 0)));
  }

  @Test
  void generateSignalForTwoPositions(){
    assertEquals(List.of(
      new Point(-1, -1), new Point(-1, 0), new Point(-1, 1),
      new Point(0, -1), new Point(0, 1),
      new Point(1, -1), new Point(1, 0), new Point(1, 1),
      new Point(1, 3), new Point(1, 4), new Point(1, 5),
      new Point(2, 3), new Point(2, 5),
      new Point(3, 3), new Point(3, 4), new Point(3, 5)),
      GameOfLife.generateSignalsForPositions(List.of(new Point(0, 0), new Point(2, 4))));
  }

  @Test
  void generateSignalForThreePositions(){
    assertEquals(List.of(
       new Point(-1, -1), new Point(-1, 0), new Point(-1, 1),
       new Point(0, -1), new Point(0, 1),
       new Point(1, -1), new Point(1, 0), new Point(1, 1),
       new Point(1, 3), new Point(1, 4), new Point(1, 5),
       new Point(2, 3), new Point(2, 5),
       new Point(3, 3), new Point(3, 4), new Point(3, 5),
       new Point(2, 2), new Point(2, 3), new Point(2, 4),
       new Point(3, 2), new Point(3, 4),
       new Point(4, 2), new Point(4, 3), new Point(4, 4)),
       GameOfLife.generateSignalsForPositions(List.of(new Point(0, 0), new Point(2, 4), new Point(3, 3))));
  }

  @Test
    void countSignalsForNoPositions(){
      assertEquals(Map.of(),
        countSignal(List.of())
      );
  }

  @Test
    void countSignalsForOnePosition(){
      assertEquals(Map.of(new Point(1, 2), 1),
        countSignal(List.of(new Point(1, 2)))
      );
  }

  @Test
  void countSignalsForTwoSamePositions(){
    assertEquals(Map.of(new Point(1, 2), 2),
      countSignal(List.of(new Point(1, 2), new Point(1, 2)))
    );
  }

  @Test
  void countSignalsForTwoSamePositionsPlusOne(){
    assertEquals(Map.of(new Point(1, 2), 2, new Point(2, 2), 1),
      countSignal(List.of(new Point(1, 2), new Point(2, 2), new Point(1, 2)))
    );
  }

  @Test
  void nextGenerationZeroPositionNoLive(){
    assertEquals(List.of(),
      nextGeneration(List.of())
    );
  }

  @Test
  void nextGenerationOnePositionNoLive(){
    assertEquals(List.of(),
      nextGeneration(List.of(new Point(1, 1)))
    );
  }

  @Test
  void nextGenerationTwoPositionNoLive(){
      assertEquals(List.of(),
        nextGeneration(List.of(new Point(2, 3), new Point(2, 4))));
   }

  @Test
   void nextGenerationThreePositionOneLive(){
     assertEquals(List.of(new Point(2, 1)),
       nextGeneration(List.of(new Point(1, 1), new Point(1, 2), new Point(3, 0))));
   }

   @Test
   void nextGenerationThreePositionFourLive(){
     assertEquals(List.of(new Point(1, 1), new Point(1, 2), new Point(2, 1), new Point(2, 2)),
       nextGeneration(List.of(new Point(1, 1), new Point(1, 2), new Point(2, 2))));
   }

  @Test
  void nextGenerationStillLife(){
    assertEquals(List.of(new Point(1, 1), new Point(1, 2), new Point(2, 1), new Point(2, 2)),
      nextGeneration(List.of(new Point(1, 1), new Point(1, 2), new Point(2, 1), new Point(2, 2))));

  }

  @Test
  void StillLifeBeeHive(){
    assertEquals(List.of(new Point(1, 1), new Point(2, 0), new Point(2, 2), new Point(3, 0), new Point(3, 2), new Point(4, 1)),
      nextGeneration(List.of(new Point(1, 1), new Point(2, 2), new Point(3, 2), new Point(4, 1), new Point(2, 0), new Point(3, 0))));

  }

  @Test
  void oscillatorVerticalBlinker(){
    assertEquals(List.of(new Point(0, 2), new Point(1, 2), new Point(2, 2)),
      nextGeneration(List.of(new Point(1, 1), new Point(1, 2), new Point(1, 3)))
      );

  }

  @Test
  void glider(){
    assertAll(
      () -> assertEquals(List.of(new Point(-1, -2), new Point(-1, 0), new Point(0, -2), new Point(0, -1), new Point(1, -1)),
        nextGeneration(List.of(new Point(0, 0), new Point(-2, -1), new Point(0, -1), new Point(0, -2), new Point(-1, -2)))),
      () -> assertEquals(List.of(new Point(-1, -2), new Point(0, -2), new Point(0, 0), new Point(1, -2), new Point(1, -1)),
        nextGeneration(List.of(new Point(-1, -2), new Point(-1, 0), new Point(0, -2), new Point(0, -1), new Point(1, -1)))),
      () -> assertEquals(List.of(new Point(-1, -1), new Point(0, -3), new Point(0, -2), new Point(1, -2), new Point(1, -1)),
        nextGeneration(List.of(new Point(-1, -2), new Point(0, -2), new Point(0, 0), new Point(1, -2), new Point(1, -1)))),
      () -> assertEquals(List.of(new Point(-1, -2), new Point(0, -3), new Point(1, -3), new Point(1, -2),new Point(1, -1)),
        nextGeneration(List.of(new Point(-1, -1), new Point(0, -3), new Point(0, -2), new Point(1, -2), new Point(1, -1))))
    );
  }


}
