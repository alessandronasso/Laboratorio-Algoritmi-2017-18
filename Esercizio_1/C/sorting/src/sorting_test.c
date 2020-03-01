#include <assert.h>
#include "sorting.h"
#include "unity.h"
#include "unity_internals.h"

int compare_long_int(void* ptr1, void* ptr2) {
  long int el1 = (long int) ptr1;
  long int el2 = (long int) ptr2;
  if(el1<el2) {
    return -1;
  }
  if (el1 == el2) {
    return 0;
  }
  return 1;
}

//precedence relation used in tests
int compare_int_ptr(void* ptr1, void* ptr2) {
  int i1 = *(int*)ptr1;
  int i2 = *(int*)ptr2;
  if(i1<i2) {
    return -1;
  }
  if(i1 == i2) {
    return 0;
  }
  return 1;
}

int* new_int(int value) {
  int* result = (int*) malloc(sizeof(int));
  *result = value;
  return result;
}


// TESTS
void test_insertion_sort_on_null_array() {
  long int* array = NULL;
  insertion_sort((void**)array, 0, compare_long_int);
  TEST_ASSERT_NULL(array);
}

void test_insertion_sort_on_full_array() {
  assert(sizeof(void*) == sizeof(long));
  long int array[5] = {11,4,1,8,10};
  insertion_sort((void**) array, 5, compare_long_int);
  int i;
  for(i=0; i<4; ++i) {
    TEST_ASSERT_TRUE(array[i] <= array[i+1]);
  }
}

void test_insertion_sort_on_full_ptr_array() {
  int* array[5];
  array[0] = new_int(11);
  array[1] = new_int(4);
  array[2] = new_int(1);
  array[3] = new_int(8);
  array[4] = new_int(10);
  insertion_sort((void**)array, 5, compare_int_ptr);
  int i;
  for(i=0; i<4; ++i) {
    TEST_ASSERT_TRUE(*array[i] <= *array[i+1]);
  }
  for(i=0; i<5 ;++i) {
    free(array[i]);
  }
}

void test_insertion_sort_on_ordered_array(){
  long int array[] = {1,2,3,4,5};
  insertion_sort((void**)array, 5, compare_long_int);
  int i;
  for(i=0;i<4;i++){
    TEST_ASSERT_TRUE(array[i]<=array[i+1]);
  }
}

void test_insertion_sort_on_equal_array(){
  long int array[] = {5,5,5,5,5};
  insertion_sort((void**)array, 5, compare_long_int);
  int i;
  for(i=0;i<4;i++){
    TEST_ASSERT_TRUE(array[i]<=array[i+1]);
  }
}

void test_merge_sort_on_null_array() {
  long int* array = NULL;
  merge_sort((void**)array, 0, 0, compare_long_int);
  TEST_ASSERT_NULL(array);
}

void test_merge_sort_on_full_ptr_array() {

  int* array[5];
  array[0] = new_int(11);
  array[1] = new_int(4);
  array[2] = new_int(1);
  array[3] = new_int(8);
  array[4] = new_int(10);

  merge_sort((void**)array, 0, 4, compare_int_ptr);
  int i;
  for(i=0; i<4; ++i) {
    TEST_ASSERT_TRUE(*array[i] <= *array[i+1]);
  }
  for(i=0; i<5 ;++i) {
    free(array[i]);
  }
}
void test_merge_sort_on_ordered_array(){
  long int array[] = {1,2,3,4,5};
  merge_sort((void**)array, 0, 4, compare_long_int);
  int i;
  for(i=0; i<4; ++i) {
    TEST_ASSERT_TRUE(array[i] <= array[i+1]);
  }  
}
void test_merge_sort_on_inordered_array(){
  long int array[] = {8,7,6,5,4,3,2,1};
  int i;
  merge_sort((void**)array, 0, 7, compare_long_int);  
  for(i=0; i<7; ++i) {
    TEST_ASSERT_TRUE(array[i] <= array[i+1]);
  }  
}

void test_merge_sort_on_equal_array(){
  long int array[] = {5,5,5,5,5};
  merge_sort((void**)array, 0, 4, compare_long_int);
  int i;
  for(i=0; i<4; ++i) {
    TEST_ASSERT_TRUE(array[i] <= array[i+1]);
  }  
}

void test_sum_search_true(){
  int* array[5];
  array[0] = new_int(11);
  array[1] = new_int(4);
  array[2] = new_int(1);
  array[3] = new_int(8);
  array[4] = new_int(10);

  merge_sort((void**)array, 0, 4, compare_int_ptr);
  int status = find_sum((long**)array, 5, 0, 4);
  TEST_ASSERT_TRUE(status == 1);

  for(int i=0; i<5 ;++i) {
    free(array[i]);
  }
}

void test_sum_search_false(){
  int* array[5];
  array[0] = new_int(11);
  array[1] = new_int(4);
  array[2] = new_int(1);
  array[3] = new_int(8);
  array[4] = new_int(10);

  merge_sort((void**)array, 0, 4, compare_int_ptr);
  int status = find_sum((long**)array, 3, 0, 4);
  TEST_ASSERT_TRUE(status == -1);

  for(int i=0; i<5 ;++i) {
    free(array[i]);
  }
}


int main(int argc, char const *argv[]) {
  UNITY_BEGIN();
  RUN_TEST(test_insertion_sort_on_null_array);
  RUN_TEST(test_insertion_sort_on_full_array);
  RUN_TEST(test_insertion_sort_on_full_ptr_array);
  RUN_TEST(test_insertion_sort_on_ordered_array);
  RUN_TEST(test_insertion_sort_on_equal_array);
  RUN_TEST(test_merge_sort_on_null_array); 
  RUN_TEST(test_merge_sort_on_ordered_array);  
  RUN_TEST(test_merge_sort_on_equal_array); 
  RUN_TEST(test_merge_sort_on_inordered_array); 
  RUN_TEST(test_merge_sort_on_full_ptr_array);
  RUN_TEST(test_sum_search_true);
  RUN_TEST(test_sum_search_false);
  UNITY_END();
  return 0;
}
