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

	if [ $WIDTH = "0" ]; then
    	inkscape -z -f $FILE_PATH -e $OUTPUT/drawable-nodpi/$NAME.png -w 1920 -h 1080
    else
    	inkscape -z -f $FILE_PATH -e $OUTPUT/drawable-mdpi/$NAME.png -w $WIDTH -h $HEIGHT
		inkscape -z -f $FILE_PATH -e $OUTPUT/drawable-hdpi/$NAME.png -w $((($WIDTH/2)+$WIDTH)) -h $((($HEIGHT/2)+$HEIGHT))
		inkscape -z -f $FILE_PATH -e $OUTPUT/drawable-xhdpi/$NAME.png -w $(($WIDTH*2)) -h $(($HEIGHT*2))
		inkscape -z -f $FILE_PATH -e $OUTPUT/drawable-xxhdpi/$NAME.png -w $(($WIDTH*3)) -h $(($HEIGHT*3))
		inkscape -z -f $FILE_PATH -e $OUTPUT/drawable-xxxhdpi/$NAME.png -w $(($WIDTH*4)) -h $(($HEIGHT*4))
	fi
}

mkdir -p $OUTPUT/drawable-nodpi
mkdir -p $OUTPUT/drawable-mdpi
mkdir -p $OUTPUT/drawable-hdpi
mkdir -p $OUTPUT/drawable-xhdpi
mkdir -p $OUTPUT/drawable-xxhdpi
mkdir -p $OUTPUT/drawable-xxxhdpi

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