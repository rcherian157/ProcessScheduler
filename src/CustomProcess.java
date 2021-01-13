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
 * Class that represents the custom processes and their burst times that are to be scheduled by the
 * process scheduler
 * 
 * @author rheacherian
 *
 */
public class CustomProcess implements Comparable<CustomProcess> {

  private static int nextProcessId = 1; // stores the id to be assigned to the next process
  // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution

  /**
   * Constructor that initializes the burst time and processId of the CustomProcess object
   * 
   * @param burstTime amount of time the process takes to complete
   */
  public CustomProcess(int burstTime) {
    if (burstTime == 0 || burstTime < 0) {
      throw new IllegalArgumentException("The burst time inputted cannot be 0 or less than 0.");
    }

    PROCESS_ID = nextProcessId;
    this.burstTime = burstTime;
    nextProcessId++;
  }

  /**
   * Returns the processId of CustomProcess object
   * 
   * @return processId of CustomProcess object
   */
  public int getProcessId() {
    return PROCESS_ID;
  }

  /**
   * Returns the burstTime of CustomProcess object
   * 
   * @return burstTime of CustomProcess object
   */
  public int getBurstTime() {
    return burstTime;
  }

  @Override
  /**
   * Compares two CustomProcess objects
   * 
   * @return +1 if this object is greater than other, -1 if this object is lesser than other, and 1
   *         if the objects are the same
   */
  public int compareTo(CustomProcess other) {
    if (other.getBurstTime() == this.getBurstTime()) {
      if (other.getProcessId() == this.getProcessId()) {
        return 0; // if the objects have the same burst time and process Id, they are the same
      } else {
        if (this.getProcessId() < other.getProcessId()) { // if they have they same burstTime, but
                                                          // their processID is lesser than other,
                                                          // then it is lesser than the other.
          return -1;
        } else {
          return 1;
        }
      }
    } else {
      if (this.getBurstTime() < other.getBurstTime()) { // if this has a greater burst time, then
                                                        // its greater
        return -1;
      } else {
        return 1;
      }
    }
  }

  /**
   * Returns a String representation of this CustomProcess
   * 
   * @return a string representation of this CustomProcess
   */
  @Override
  public String toString() {
    return "p" + this.PROCESS_ID + "(" + this.burstTime + ")";
  }



}
