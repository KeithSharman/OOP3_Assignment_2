package utilities;

import java.io.BufferedReader;
import java.io.FileReader;

public class XMLParser {

    public static void main(String[] args) {

        String filename = args[0];

        MyStack<String> stack = new MyStack<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {

                int i = 0;

                while (i < line.length()) {

                    if (line.charAt(i) == '<') {

                        int end = line.indexOf('>', i);
                        String tag = line.substring(i + 1, end);

                        if (tag.startsWith("/")) {
                            String name = tag.substring(1);

                            if (stack.isEmpty() || !stack.pop().equals(name)) {
                                System.out.println("Error: " + name);
                            }
                        }

                        else if (!tag.endsWith("/")) {
                            stack.push(tag);
                        }

                        i = end;
                    }

                    i++;
                }
            }

            if (!stack.isEmpty()) {
                System.out.println("Error: unclosed tags");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        {     Object tag = ((String) tag).trim();

     boolean isClosing = false;
     if (((String) tag).startsWith("/")) {
         isClosing = true;
         tag = ((String) tag).substring(1);
     }

     if (((String) tag).contains(" ")) {
         tag = ((String) tag).substring(0, ((String) tag).indexOf(" "));
     }

     if (isClosing) {
         tag = "/" + tag;
     }}}}

