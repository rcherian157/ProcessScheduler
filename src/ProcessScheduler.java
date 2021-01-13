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
import java.util.Scanner;

/**
 * Class that represents the data type for the main scheduler for our processes
 * 
 * @author rheacherian
 *
 */
public class ProcessScheduler {
  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private WaitingProcessQueue queue; // this processing unit’s queue

  public static void main(String[] args) {
    userInputProcesser();
  }

  /**
   * Constructor that initializes the queue to empty, and currentTime and numProcessesRun to 0.
   */
  public ProcessScheduler() {
    queue = new WaitingProcessQueue();
    currentTime = 0;
    numProcessesRun = 0;
  }

  /**
   * Method that analyzes user input and conducts the corresponding processes
   */
  private static void userInputProcesser() {
    System.out.println("========== Welcome to the SJF Process Scheduler App ========");
    boolean quit = false;
    String command = ""; // command that user calls
    int burstTime = 0;
    CustomProcess process = null;
    Scanner s = new Scanner(System.in);
    ProcessScheduler p = new ProcessScheduler();
    while (!quit) {
      printInitialMessage();
      command = s.nextLine();
      // if the command is "s burstTime", then a process is scheduled as long as this format is
      // maintained and the burstTime inputted is a non-zero, positive #
      if (command.length() >= 3 && command.substring(0, 2).equals("s ")) {
        try {
          burstTime = Integer.parseInt(command.substring(2));
        } catch (NumberFormatException n) {
          // if the burst time inputted isn't a number, throws a NumberFormatException
          System.out.println("WARNING: burst time MUST be an integer!\n");
          continue;
        }
        try {
          process = new CustomProcess(burstTime);
        } catch (IllegalArgumentException i) {
          // if the burst time isn't a non-zero, + number, throws an IllegalArgumentException
          System.out.println("WARNING: burst time has to be a non-zero, positive number.");
          continue;
        }
        p.scheduleProcess(process);
        System.out.println("Process ID " + process.getProcessId() + " scheduled. Burst Time = "
            + burstTime + "\n");
        // if the command that the user inputted is r, run() method is called
      } else if (command.equals("r")) {
        System.out.println(p.run());
        // if the command that the user inputted is q, then the method just quits
      } else if (command.equals("q")) {
        System.out.println(p.numProcessesRun + " processes run in " + p.currentTime
            + " units of time!\n" + "Thank you for using our scheduler!\n" + "Goodbye!\n");
        quit = true;
      } else {
        // if the command is none of the above, then the command is invalid
        System.out.println("WARNING: Please enter a valid command!\n");
      }
    }
  }

  /**
   * Method that prints the message before the user inputs a command
   */
  private static void printInitialMessage() {
    System.out.println("Enter command: " + "\n" + "[schedule <burstTime>] or [s <burstTime>]" + "\n"
        + "[run] or [r]" + "\n" + "[quit] or [q])");
  }

  /**
   * This method inserts the given process in the WaitingProcessQueue queue.
   * 
   * @param process the process that is to be scheduled
   */
  public void scheduleProcess(CustomProcess process) {
    queue.insert(process);
    numProcessesRun++;
  }

  /**
   * This method runs the ready processes already scheduled in this processScheduler’s queue
   * 
   * @return string that shows all the processes that are already scheduled
   */
  public String run() {
    int processId = 0;
    int burstTime = 0;
    int indexOfParentheses = 0;
    String s = ""; // string that shows all the processes that are already scheduled
    String[] splitQueueStringCustomProcessString;
    String processIdString = "";
    String burstTimeString = "";
    if (numProcessesRun == 1) {
      s += "Starting " + numProcessesRun + " process\n\n";
    } else if (numProcessesRun == 0) {
      return "Starting " + numProcessesRun + " process\n\n" + "Time " + currentTime + " : "
          + "All scheduled processes completed";
    } else {
      s += "Starting " + numProcessesRun + " processes\n\n";
    }

    // splitQueueStrings the queue string so that the String[] splitQueueString array holds a string
    // that represents each
    // CustomProcess that was
    // inserted
    String[] splitQueueString = queue.toString().split("\n");

    // splitQueueStrings the string of each customProcess object into individual characters so that
    // the process
    // ID and burst time of that custom process can be determined
    for (int i = 0; i < splitQueueString.length; i++) {
      splitQueueStringCustomProcessString = splitQueueString[i].split("");
      for (int j = 0; j < splitQueueStringCustomProcessString.length; j++) {
        if (splitQueueStringCustomProcessString[j].equals("(")) {
          indexOfParentheses = j;
        }
      }

      for (int k = 1; k < indexOfParentheses; k++) {
        processIdString += splitQueueStringCustomProcessString[k];
      }
      for (int m = indexOfParentheses + 1; m < splitQueueStringCustomProcessString.length
          - 1; m++) {
        burstTimeString += splitQueueStringCustomProcessString[m];
      }
      processId = Integer.parseInt(processIdString);
      burstTime = Integer.parseInt(burstTimeString);
      s += "Time " + currentTime + " : Process ID " + processId + " Starting. \n";
      currentTime += burstTime;
      s += "Time " + currentTime + " : Process ID " + processId + " Completed.\n";
      processIdString = "";
      burstTimeString = "";
    }

    s += "Time " + currentTime + " : " + "All scheduled processes completed";
    s += "\n";
    //queue is set to empty
    queue = new WaitingProcessQueue();
    //therefore s is returned which represents all the scheduled processes 
    return s;
  }

}
