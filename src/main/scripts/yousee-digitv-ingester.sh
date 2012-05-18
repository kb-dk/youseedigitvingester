#!/bin/bash

# Get input parameters from Taverna
CONFIGFILE=$1
FILENAME=$2
STARTTIME=$3
STOPTIME=$4
CHANNELID=$5   # SB-version of channel ID

# Output goes to stdout


source $CONFIGFILE

if [ -z "$CONFIGFILE" ]; then
	echo "Error: Parameter CONFIGFILE is empty!" >&2
	exit 666
fi
if [ -z "$FILENAME" ]; then
	echo "Error: Parameter FILENAME is empty!" >&2
	exit 666
fi
if [ -z "$STARTTIME" ]; then
	echo "Error: Parameter STARTTIME is empty!" >&2
	exit 666
fi
if [ -z "$STOPTIME" ]; then
	echo "Error: Parameter STOPTIME is empty!" >&2
	exit 666
fi
if [ -z "$CHANNELID" ]; then
	echo "Error: Parameter CHANNELID is empty!" >&2
	exit 666
fi


### Call Java-code here...
ID_OF_INSERTED_FILE="xyz123"
ERRORS=0


if [ -z "$ERRORS" ]; then
	# No errors, so insert into DigiTV database succeeded
	echo '{'
	echo "   \"idOfInsertedFile\" : $ID_OF_INSERTED_FILE"
	echo '}'
	exit 0
else
	echo 'YouSee-DigiTV-ingester failed.' >&2
	exit 13
fi

