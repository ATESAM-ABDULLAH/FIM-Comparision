#!/bin/sh

# Check if correct number of arguments is provided
if [ "$#" -ne 2 ]; then
  echo "Usage: $0 <file> <n>"
  exit 1
fi

# Assign arguments to variables
file=$1
n=$2

# Validate the input
if echo "$n" | grep -qE '^[0-9]+$'; then
  true
else
  echo "Error: <n> must be a positive integer."
  exit 1
fi

if [ ! -f "$file" ]; then
  echo "Error: File '$file' does not exist."
  exit 1
fi

# Keep only the top n lines of the file
head -n "$n" "$file" > temp_file && mv temp_file "$file"

echo "Successfully kept the top $n lines of $file."
