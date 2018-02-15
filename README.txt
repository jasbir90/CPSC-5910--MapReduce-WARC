{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf820
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;\red0\green0\blue0;}
{\*\expandedcolortbl;;\csgenericrgb\c0\c0\c0;}
\margl1440\margr1440\vieww28600\viewh15380\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\b\fs24 \cf0 Source Code\
\
1. EmailCountMapper.java: 
\b0 The class has a static string for holding email regex expressions.\
This class implements the map method. The map method takes archiveReader as the input. Fetches the response from each archive record and takes the body of the response(ignoring the header). \
If it finds a regex pattern in the input, it writes the email address and corresponding domain name in the context.\
\

\b 2. EmailCountReducer.java: I
\b0 mplements reduce method. The reduce method takes in the email id, their domains and context as the input variables. It uses a HashSet data structure and calculates the email ids and their counts.\

\b \
3. EmailCountMapReduce.java: 
\b0 Implements the main method. Sets input, output, mapper and reducer classes and sets the input/output file path. It returns true only after getting all the responses from reducer.\
\

\b \
\
Tools\
\
1.WARCFileInputFormat.java: 
\b0 \cf2 Implementation of FileInputFormat for WARC files.(\cf0 https://github.com/Smerity/cc-warc-examples)\

\b \
2. WARCFileRecordReader.java: 
\b0 Splits the big WARC file in WARC Archives. \cf2 Returns a single WARC ArchiveReader that can contain numerous individual documents (\cf0 https://github.com/Smerity/cc-warc-examples)\
\cf2 \

\b 3.webarchive-commons-jar-with-dependencies.jar: 
\b0 Dependency jar for WARC (https://raw.githubusercontent.com/Smerity/cc-warc-examples/master/lib/webarchive-commons-jar-with-dependencies.jar, https://github.com/Smerity/cc-warc-examples/blob/master/lib/webarchive-commons-jar-with-dependencies.jar)\
\
\
\

\b Information for running the jar on EMR
\b0 \
I have my classes in a package. So the class name argument would have to be passed as below:\
\pard\pardeftab720\ri0\partightenfactor0
\cf0 edu.seattleu.kaurj7.emailCount.MapReduce\
\
\
\

\b Note:
\b0 \cf2 \
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardeftab720\pardirnatural\partightenfactor0
\cf2 I have used a simpler Regex expression which might not check every validation (have commented the actual regex). \
The submission java code has the tweaked regex and it runs with 10 file WARC}