#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <time.h>
#include <math.h>

typedef int (*CompFunction)(void*, void*);

/**
 * Sorts the elements in array according to the order induced by the specified
 * function.
 * @param a the array to sort
 * @param first first index of the array
 * @param last last index of the array
 * @param compare function that defines the sorting order
 */
void merge_sort(void** a, int first, int last, CompFunction compare);

/**
 * Transforms two consecutive sorted ranges into a single sorted range. 
 * @param array the array to merge
 * @param low the lowest index of the array 
 * @param centre mid position of the array
 * @param sup the index of the last element of the array
 * @param compare function that defines the sorting order
 */
void merge(void**array,int low, int centre, int sup, CompFunction compare, void** tmp_array);

/**
 * Sort the ArrayList with Insertion Sort
 * @param size: dimension of the array to order
 * @param array: pointer to the array to sort
 * @CompFunction: function compare
 */
void insertion_sort(void** array, int size, CompFunction compare);

/**
 * This function search if in 'array' there are two elements that give 'n' as their sum
 * @param array the array to check
 * @param n number to search as result of the sum of two elements of 'array'
 * @param i first index of the array
 * @param j last index of the array
 * @return 1 if in 'array' there are two elements whose sum is 'n', -1 otherwise
 */
int find_sum(long** array, long n, int i, int j);


/**
 * Swap the content of two elementws of the array
 * @param a: index of the element that i want to swap
 * @param b:  index of the other element that i want to swap
 */
void swap(void** a, void** b);
