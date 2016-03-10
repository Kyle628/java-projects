// $Id: jxref.java,v 1.8 2013-10-16 17:10:32-07 - - $
//Michael Simpson -- misimpso@ucsc.edu
//Kyle O'Connor -- kyjoconn@ucsc.edu

import java.io.*;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.*;

class jxref {
  private static final String STDIN_FILENAME = "-";
  private static final String REGEX = "\\w+([-'.:/]\\w+)*";
  private static final Pattern PATTERN = Pattern.compile(REGEX);
  
  private static void xref_file (String filename, Scanner file){  
    listmap map = new listmap();
    for (int linenr = 1; file.hasNextLine(); ++linenr) {
      String line = file.nextLine();
      Matcher match = PATTERN.matcher (line);
      while (match.find()) {
        String word = match.group();
        //Places a word in the list map
        //find is like hasNext, group is like next
        //insert the word in the linked list and associate a line number to the queue
        map.insert(word, linenr);
      }
    }
    for (Entry<String, intqueue> entry: map) {
      out.printf("%s [%s] ", entry.getKey(), entry.getValue().getcount());
      Iterator<Integer> x = entry.getValue().iterator();
        while(x.hasNext()) {
          out.printf("%s ", x.next());
        }
        out.println();
    }
  }
  
  
  // For each filename, open the file, cross reference it,
  // and print.
  private static void xref_filename (String filename) {
    if (filename.equals (STDIN_FILENAME)) {
      xref_file (filename, new Scanner (System.in));
    }else {
      try {
        Scanner file = new Scanner (new File (filename));
        xref_file (filename, file);
        file.close();
      }catch (IOException error) {
        misc.warn (error.getMessage());
      }
    }
  }
  
  // Main function scans arguments to cross reference files.
  public static void main (String[] args) {
    for (Object arg: args) {
      String colonsOnColons = "::::::::::::::::::::::::::::::::";
      out.printf ("%s%n%s%n%s%n", colonsOnColons, arg, colonsOnColons);
    }
    if (args.length == 0) {
      xref_filename (STDIN_FILENAME);
    }else {
      for (String filename: args) {
        xref_filename (filename);
      }
    }
    exit (misc.exit_status);
  }
  
}
