#!/bin/bash

OUTPUT="./app/src/main/res"

process_file()
{
	FILE_PATH=$1
	FILE_NAME=$(basename ${FILE_PATH%.*})
	IFS='-' read -ra array <<< "$FILE_NAME"

	NAME="${array[0]}"
	WIDTH="${array[1]}"
	HEIGHT="${array[2]}"

    inkscape -z -f $FILE_PATH -e $OUTPUT/drawable-nodpi/$NAME.png -w $WIDTH -h $HEIGHT
}

mkdir -p $OUTPUT/drawable-nodpi
rm $OUTPUT/drawable-nodpi/*.png

list_files()
{
    for entry in "$1"/*
    do
        if [ -d "$entry" ]; then
            list_files "$entry"
        elif [ -f "$entry" ]; then
            process_file $entry
        fi
    done
}

list_files ./resources