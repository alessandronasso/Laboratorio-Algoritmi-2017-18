#include "sorting.h"
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

void load_array(long** array, int* size, const char* file_name);
int compare_long(void* ptr1, void* ptr2);
long* new_int(long value);

int main(int argc, char const *argv[]) {
  if(argc < 2) {
    printf("Usage: sortingfirstusage <file_name>\n");
    exit(EXIT_FAILURE);
  }
  int size = 0;
  long* array = NULL;
  load_array(&array, &size, argv[1]);
  
  printf("Sorting...\n");
  clock_t start_time = clock();
  
  merge_sort((void**)array, 0, size, compare_long);
  //insertion_sort((void**)array, size, compare_long);
  
  double elapsed_time = (clock() - start_time)/(double)CLOCKS_PER_SEC;
  printf("Completed in %.5f seconds\n", elapsed_time);
  free(array);
  return 0;
}

void load_array(long** array, int* size, const char* file_name) {
  size_t n = 0, lines = 0;
  char *buf = 0;
  int i = 0;
  FILE* fp = fopen(file_name, "r");
  if(fp == NULL){
    fprintf(stderr,"Error: unable to open the file\n");
    exit(EXIT_FAILURE);
  }

  while (getline(&buf, &n, fp) != -1) lines++;
  printf("Total numbers in the file: %zu\n", lines);
  *size = lines-1;

  *array = (long *)malloc(lines*sizeof(long));

  char* buf1 = (char *)malloc(sizeof(long));
  size_t n1 = sizeof(long);
  rewind(fp);

  while (getline(&buf1, &n1, fp) != -1) {
    (*array)[i] = (long)new_int((long)atol(buf1));
    i++;
  }
  free(buf1);
}

long* new_int(long value) {
  long* result = (long*) malloc(sizeof(long));
  *result = value;
  return result;
}

int compare_long(void* ptr1, void* ptr2) {
  long i1 = *(long*)ptr1;
  long i2 = *(long*)ptr2;
  if(i1<i2) return -1;
  if(i1 == i2) return 0;
  return 1;
} 
