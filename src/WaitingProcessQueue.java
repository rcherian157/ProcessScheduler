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
 * Class that represents the priority queue that is used to schedule processes based on minimum
 * burstTime priority
 * 
 * @author rheacherian
 *
 */
public class WaitingProcessQueue implements WaitingQueueADT<CustomProcess> {

  private static final int INITIAL_CAPACITY = 20; // the initial capacity of this
  // waiting process queue
  private CustomProcess[] data; // min heap-array storing the CustomProcesses
  // inserted in this WaitingProcessQueue.
  // data is an oversize array
  private int size; // number of CustomProcesses stored in this WaitingProcessQueue


  public static void main(String[] args) {

  }

  /**
   * Constructor that initializes the min heap array data that stores the inserted CustomProcesses
   * and the size of the queue
   */
  public WaitingProcessQueue() {
    data = new CustomProcess[INITIAL_CAPACITY];
    size = 0;
  }

  @Override
  /**
   * Method that inserts a new CustomProcess object to the min heap priority queue
   * 
   * @param newObject - a CustomProcess object that is to be added to the priority queue
   */
  public void insert(CustomProcess newObject) {
    // creates an oversize array that expands with more elemtns
    CustomProcess[] largeData = new CustomProcess[size * 2];
    if (size == data.length) {
      for (int i = 0; i < data.length; i++) {
        largeData[i] = data[i];
      }
      data = largeData;
    }
    // new object is inserted into the min heap
    data[size] = newObject;
    size++;
    // bubble up
    minHeapPercolateUp(size - 1);
  }

  @Override
  /**
   * Removes the best i.e the root of the min heap and returns the removed item
   * 
   * @return the root of the min heap that is removed
   */
  public CustomProcess removeBest() {
    CustomProcess removedItem = data[0];
    if (!this.isEmpty()) {
      data[0] = data[size - 1];
      size--;
      minHeapPercolateDown(size - 1);
    }
    return removedItem;
  }

  @Override
  /**
   * Method that returns the root of the min heap
   * 
   * @return the root of the min heap
   */
  public CustomProcess peekBest() {
    if (this.isEmpty()) {
      return null;
    } else {
      return data[0];
    }

  }

  @Override
  /**
   * Method that returns the size of the priority queue
   * 
   * @return size of priority queue
   */
  public int size() {
    return size;
  }

  @Override
  /**
   * Method that returns true/false if the queue is empty or not
   * 
   * @return true if queue is empty, false otherwise
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Method that bubbles up a child by swapping with the parent / root until it reaches its
   * appropriate position within the heap
   * 
   * @param index - the index of the child that is being compared to the root
   */
  private void minHeapPercolateUp(int index) {
    int parentIndex;
    CustomProcess temp;
    parentIndex = (index - 1) / 2;
    if (data[index] != null && data[parentIndex] != null) {
      if (data[parentIndex].compareTo(data[index]) > 0) {
        // swap if node at parentIndex is greater than the node at index
        temp = data[parentIndex];
        data[parentIndex] = data[index];
        data[index] = temp;
        minHeapPercolateUp(parentIndex);
      }
    }
  }

  /**
   * Method that bubbles down a child by swapping it with children that are larger until it reaches
   * its appropriate position within the heap
   * 
   * @param index - the index of the child that is being compared to its parent and other children
   */
  private void minHeapPercolateDown(int index) {
    int smallerChildIndex;
    CustomProcess temp;
    smallerChildIndex = (index * 2) + 1;
    if (data[(index * 2) + 2] != null && data[(index * 2) + 1] != null) {
      if (data[(index * 2) + 2].compareTo(data[(index * 2) + 1]) < 0) {
        smallerChildIndex = (index * 2) + 2;
        if (data[index].compareTo(data[smallerChildIndex]) > 0) {
          // swap if node at index is greater than the node at the smallestChild's index
          temp = data[smallerChildIndex];
          data[smallerChildIndex] = data[index];
          data[index] = temp;
          minHeapPercolateDown(smallerChildIndex);
        }
      }
    }
  }

  /**
   * Returns a string that represents the items of the queue
   * 
   * @return string that represents the items of the queue
   */
  @Override
  public String toString() {
    if (size() == 0) {
      return " ";
    }

    String s = "";
    for (int i = 0; i < size(); i++) {
      if (data[i] != null) {
        s += data[i];
        s += "\n";
      }
    }
    return s;
  }



}
