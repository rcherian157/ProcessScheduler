//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
// Title: This programs aims to create a simple process scheduler to schedule processes that are
// ready to run, and the process scheduler is based on a minimum heap priority queue, 
// with the CustomProcess objects of lower burst times be given higher priority. 
//
// Files: ProcessSchedulerTester.java, ProcessScheduler.java, WaitingProcessQueue.java,
// CustomProcess.java
// Course: CS 300, Fall 2019
//
// Author: Rhea Cherian
// Email: rcherian@wisc.edu
// Lecturer's Name: Gary Dahl
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class tests whether the insert and removeBest methods of the WaitingProcessQueue work as
 * expected.
 * 
 * @author rheacherian
 *
 */
public class ProcessSchedulerTester {

  public static void main(String[] args) {
  }

  /**
   * Checks the correctness of the insert operation implemented in the WaitingProcessQueue class
   * 
   * @return true if the insert method of the WaitingProcessQueue works as expected
   */
  public static boolean testInsertWaitingProcessQueue() {
    CustomProcess one = new CustomProcess(10);
    CustomProcess two = new CustomProcess(2);
    CustomProcess three = new CustomProcess(5);
    CustomProcess four = new CustomProcess(3);
    CustomProcess five = new CustomProcess(1);
    WaitingProcessQueue c = new WaitingProcessQueue();
    // inserts each new CustomProcess object and makes sure that the root of the min heap is correct
    // by using the method peekBest()
    c.insert(one);
    if (!c.peekBest().equals(one)) {
      return false;
    }
    c.insert(two);
    if (!c.peekBest().equals(two)) {
      return false;
    }

    c.insert(three);
    if (!c.peekBest().equals(two)) {
      return false;
    }

    c.insert(four);
    if (!c.peekBest().equals(two)) {
      return false;
    }

    c.insert(five);
    if (!c.peekBest().equals(five)) {
      return false;
    }
    return true;
  }

  /**
   * Checks the correctness of the removeBest operation implemented in the WaitingProcessQueue class
   * 
   * @return true if the removeBest method of the Waiting Process Queue class works as expected
   */
  public static boolean testRemoveBestWaitingProcessQueue() {
    CustomProcess one = new CustomProcess(10);
    CustomProcess two = new CustomProcess(2);
    CustomProcess three = new CustomProcess(5);
    CustomProcess four = new CustomProcess(3);
    CustomProcess five = new CustomProcess(1);
    WaitingProcessQueue c = new WaitingProcessQueue();
    c.insert(one);
    c.insert(two);
    c.insert(three);
    c.insert(four);
    c.insert(five);
    // inserted 5 CustomProcess objects and then checks whether that the root of the min heap is
    // returned when removeBest is executed
    CustomProcess removed = c.removeBest();
    if (removed.equals(five)) {
      return true;
    }
    return false;
  }
}
