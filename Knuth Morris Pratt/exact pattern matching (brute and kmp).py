from Bio.Seq import Seq
from Bio import SeqIO
from Bio.SeqRecord import SeqRecord
import time
import sys

# naive brute force exact pattern matching algorithm
def brute_force(pat, seq):
    pattern_size = len(pat)
    sequence_size = len(seq)
    num_match = 0

    for pos_seq in range(sequence_size - pattern_size + 1):
        # current position in pattern
        pos_pat = 0

        while pos_pat < pattern_size:
            if pat[pos_pat] != seq[pos_seq + pos_pat]:
                break
            pos_pat += 1

        if pos_pat == pattern_size:
            num_match += 1
    return num_match


def kmp_preprocessing(pat):
    table = []
    m = len(pat)
    i = 0
    j = -1
    table.insert(0, j)

    while i < m:
        while j > -1 and pat[i] != pat[j]:
            j = table[j]

        i += 1
        j += 1
        if i < m and pat[i] == pat[j]:
            table.insert(i, table[j])
        else:
            table.insert(i, j)

    return table

# Knuth Morris Pratt
def kmp(pat, seq):
    pattern_size = len(pat)
    sequence_size = len(seq)
    table = kmp_preprocessing(pat)
    num_match = 0

    i = 0
    j = 0
    while j < sequence_size - pattern_size + 1:
        if seq[j+i] == pat[i]:
            i += 1
        elif seq[j+i] != pat[i]:
            j = j + i - table[i]
            if table[i] < 0:
                i = 0
            else:
                i = table[i]
        if i == pattern_size:
            num_match += 1
            j = j + i - table[i]
            if table[i] < 0:
                i = 0
            else:
                i = table[i]

    return num_match


if __name__ == "__main__":
    user_input = sys.argv

    filename = user_input[1]
    pattern = user_input[2]
    algorithm_name = "kmp"

    if len(user_input) == 4:
        if user_input[3] == "-b":
            algorithm_name = user_input[3]

    forward_sequence = []
    reverse_sequence = []

    for fastaRec in SeqIO.parse(filename, "fasta"):
        forward_sequence = fastaRec.seq

    for fastaRec in SeqIO.parse(filename, "fasta"):
        reverse_sequence = fastaRec.seq.reverse_complement()

    if algorithm_name == "-b":
        print(f"Searching for pattern: {pattern} in the sequence {filename} using the brute force algorithm...\n\n")

        start_time = time.time()
        print(f"Total number of matches in forward strand = {brute_force(pattern, forward_sequence)}")
        print(f"Total number of matches in reverse complement strand = {brute_force(pattern, reverse_sequence)}")
        end_time = time.time()

        time_spent = (end_time - start_time)
        print(f"Completed the search in {time_spent} seconds")
    else:
        print(f"Searching for pattern: {pattern} in the sequence {filename} using the KMP algorithm...\n\n")

        start_time_kmp = time.time()
        print(f"Total number of matches in forward strand = {kmp(pattern, forward_sequence)}")
        print(f"Total number of matches in reverse complement strand = {kmp(pattern, reverse_sequence)}")
        end_time_kmp = time.time()

        time_spent = (end_time_kmp - start_time_kmp)
        print(f"Completed the search in {time_spent} seconds")






