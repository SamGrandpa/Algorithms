# KMP
Don't know [Knuth-Morris-Pratt algorithm](https://en.wikipedia.org/wiki/Knuth–Morris–Pratt_algorithm).<br>

## Running the file

An example of how to run the file on anaconda prompt:
```python
>python kmp.py Sorangium cellulosum.fasta ATG
```

This example can only run with at least two inputs.

First input: fasta file

Second input: pattern to be searched

Third input (optional): specifies the type of algorithms
blank for kmp
```python
>python kmp.py Sorangium cellulosum.fasta ATG
```
-b for brute-force
```python
>python kmp.py Sorangium cellulosum.fasta ATG -b
```
