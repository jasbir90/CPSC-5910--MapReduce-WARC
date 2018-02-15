 Source Code

1. EmailCountMapper.java: 
The class has a static string for holding email regex expressions.
This class implements the map method. The map method takes archiveReader as the input. Fetches the response from each archive record and takes the body of the response(ignoring the header).
If it finds a regex pattern in the input, it writes the email address and corresponding domain name in the context.
2. EmailCountReducer.java: Implements reduce method. The reduce method takes in the email id, their domains and context as the input variables. It uses a HashSet data structure and calculates the email ids and their counts.
3. EmailCountMapReduce.java: 
Implements the main method. Sets input, output, mapper and reducer classes and sets the input/output file path. It returns true only after getting all the responses from reducer.


Tools
1.WARCFileInputFormat.java: Implementation of FileInputFormat for WARC files.(\cf0 https://github.com/Smerity/cc-warc-
2. WARCFileRecordReader.java: Splits the big WARC file in WARC Archives. \cf2 Returns a single WARC ArchiveReader that can contain numerous individual documents (\cf0 https://github.com/Smerity/cc-warc-examples)\
3.webarchive-commons-jar-with-dependencies.jar: 
Dependency jar for WARC (https://raw.githubusercontent.com/Smerity/cc-warc-examples/master/lib/webarchive-commons-jar-with-dependencies.jar, https://github.com/Smerity/cc-warc-examples/blob/master/lib/webarchive-commons-jar-with-dependencies.jar)\



Information for running the jar on EMR
The classes are in a package. So the class name argument would have to be passed as below:
edu.seattleu.kaurj7.emailCount.MapReduce



Note:I have used a simpler Regex expression which might not check every validation (have commented the actual regex). \
The submission java code has the tweaked regex and it runs with 10 file WARC}
