package me.zeeeeeeek.backend.models.tasks;


import java.util.function.Predicate;

/**
 * A task collection represents a group of tasks.
 */
public interface TaskCollection {

    /**
     * Returns the number of tasks in this collection.
     *
     * @return the number of tasks in this collection
     */
    int size();

    /**
     * Adds a new task to the collection
     *
     * @param task the task to be added
     * @return true, if the element has been added, false otherwise
     * @throws NullPointerException if the specified element is null
     */
    boolean add(Task task);

    /**
     * Adds all the task in the specified taskCollection to this collection.
     *
     * @param collection the collection containing all the tasks to be added
     * @return true, if all the elements have been added, false otherwise
     * @throws NullPointerException if the collection is null
     */
    boolean addAll(TaskCollection collection);

    /**
     * Checks if the collection contains the specified element
     *
     * @param task task whose presence is to be tested
     * @return true, if the collection contains the element, false otherwise
     * @throws NullPointerException if the specified element is null
     */
    boolean contains(Task task);

    /**
     * Checks if the collection contains all the elements of the specified collection
     *
     * @param collection collection of elements whose presence is to be tested
     * @return true, if this collection contains all the elements of the specified collection, false otherwise
     * @throws NullPointerException if the collection is null
     */
    boolean containsAll(TaskCollection collection);

    /**
     * Controls if all the contained tasks are completed or not
     *
     * @return true, if all {@link Task#isCompleted()} calls in each contained task returns true, false otherwise
     */
    boolean isCompleted();

    /**
     * Removes all the tasks from this collection.
     */
    void clear();

    /**
     * Removes a single instance of the specified tasks, if its present. More formally
     * removes an element such that is equal to the given task.
     *
     * @return true, if an element was removed, false otherwise
     */
    boolean remove();

    /**
     * Removes all of this collection's task that are contained in the specified collection.
     *
     * @param collection collection of tasks to be removed from this collection
     * @return true, if this collection changed as a result of the call
     * @throws NullPointerException if collection is null
     */
    boolean removeAll(TaskCollection collection);

    /**
     * Removes all the elements of the collection that satisfy the given predicate.
     *
     * @param filter a predicate witch returns true if a task has to be removed
     * @return true, if any element was removed
     * @throws NullPointerException if the filter is null
     */
    boolean removeIf(Predicate<? super Task> filter);

    //Comparison and hashing

    /**
     * Compares the specified object with this collection for equality
     *
     * @param obj the object to be compared for equality with this collection
     * @return true, if the specified object is equal to this collection, false otherwise
     */
    boolean equals(Object obj);


    /**
     * Returns the hash code value for this collection.
     *
     * @return this hashcode value
     */
    int hashCode();


}

