
# lista dei target da eseguire execf = firtusage, execs = secondusage
all: tests execf

# compila (grazie alla dipendenza) e esegue i test
tests: bin/sorting_test
	bin/sorting_test

#  compila (grazie alla dipendenza) e esegue
execf: bin/sorting
	bin/sorting csv/Integers.csv

execs: bin/sorting
	bin/sorting csv/Integers.csv csv/sums.txt
# Flags per il compilatore -- su gcc -Weverything non esiste
#    sostituire con -Wall -Wpedantic
CFLAGS = -g -Wall -Wpedantic -Wno-padded -Werror

# Lista degli header files
INCLUDES = src/*.h

# tutti i target vanno ricompilati se cambiano gli header o
# questo Makefile
COMMON_DEPS = $(INCLUDES) Makefile

# regola per compilare un .c in un .o
# viene applicata ogni volta che una dipendenza richiede di compilare un file in
#    `build` e con suffisso .o. Il simbolo % effettua pattern matching con quanto
#    compreso tra 'build/' e '.o', la stringa in questione viene quindi usata sul lato
#    destro della regola per indicare il file da compilare.
# Le variabili $< e $@ sono gestite automaticamente da `make` esse corrispondono:
#   $@ : l'intero target della regola (in questo caso: build/<nomefile>.o)
#   $< : la prima dipendenza della regola (in questo caso: src/<nomefile>.c)
build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@

#sortingsecondusage #sortingfirstusage
bin/sorting: build/sorting.o build/sortingfirstusage.o $(COMMON_DEPS)
	$(CC) -o bin/sorting build/sorting.o build/sortingfirstusage.o

bin/sorting_test: build/sorting_test.o build/sorting.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/sorting_test  build/sorting_test.o build/sorting.o build/unity.o

clean:
	rm -f build/* bin/*
