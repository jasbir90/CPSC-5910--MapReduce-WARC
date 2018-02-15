package edu.seattleu.kaurj7.emailCount;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.archive.io.ArchiveReader;
import org.archive.io.ArchiveRecord;

public class EmailCountMapper
		extends Mapper<Text, ArchiveReader, Text, Text> {
	private Text outputKey = new Text();
	// Regular expression for verifying email
	//private static final String EMAIL_REGEX = "([_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))";
	private static final String EMAIL_REGEX = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}";


	private Pattern patternTag;
	private Matcher matcherTag;

	public void map(Text key, ArchiveReader value, Context context)
			throws IOException, InterruptedException {
		patternTag = Pattern.compile(EMAIL_REGEX);
		for (ArchiveRecord r : value) {
			URI uri;
			try {
				// do something if there is a proper response
				if (r.getHeader().getMimetype()
						.equals("application/http; msgtype=response")) {
					// get bytes of the response
					byte[] rawData = IOUtils.toByteArray(r, r.available());
					// change type to string
					String content = new String(rawData);
					String header = content.substring(0,content.indexOf("\r\n\r\n"));
					if (header.contains("Content-Type: text/html")) {
						uri = new URI(r.getHeader().getUrl());
						// fetch the input from the response payload
						String input = content.substring(content.indexOf("\r\n\r\n") + 4);
						matcherTag = patternTag.matcher(input);
						//write the email and domain to the context for every pattern match in input
						//key is email id and output is the domain
						while (matcherTag.find()) {
							String email = matcherTag.group(0);
							Text domain = new Text(uri.getHost());
							outputKey.set(email);
							context.write(outputKey, domain);
						}
					}
				}
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
