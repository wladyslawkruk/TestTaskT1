# Test_task_for_T1


App controller listening at: http://localhost:8080/api/frequency





## Basic interactions

1) http://localhost:8080/api/frequency POST

Example:
By providing the "sdshjdfsdhf" as plain text,
the following response expected:

{
"s": 3,
"d": 3,
"f": 2,
"h": 2,
"j": 1
}

404 response expected in case of empty input string/incorrect format string (only latin alphabetic symbols allowed)





