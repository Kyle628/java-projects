// $Id: intqueue.java,v 1.4 2013-10-16 17:10:32-07 - - $
//Michael Simpson -- misimpso@ucsc.edu
//Kyle O'Connor -- kyjoconn@ucsc.edu

import java.util.Iterator;
import java.util.NoSuchElementException;

class intqueue implements Iterable<Integer> {

   private class node {
      int linenr;
      node link;
   }
   private int count = 0;
   private node front = null;
   private node rear = null;

   public void insert (int number) {
      //Similar to listmap.insert, create node whenever a word is processed through listmap.insert. 
      //Nodes would store the line number the word was found on passed from listmap.insert. 
      //Total word count could be found through number of nodes.
      //misc.trace(count);
     node temp = new node();
     temp.linenr = number;
     if(front == null) {
      temp.link = null;
      front = temp;
      rear = temp;
     } else if (front != null) {
       rear.link = temp;
       rear = temp;
     }      
      ++count;
      
      //count number of nodes and incriment the count variable
      //FIXME
   }

   public boolean empty() {
      return count == 0;
   }
   
   public int getcount() {
      return count;
   }

   public Iterator<Integer> iterator() {
      return new iterator();
   }

   private class iterator implements Iterator<Integer> {
      node curr = front;

      public boolean hasNext() {
         return curr != null;
      }

      public Integer next() {
         if (curr == null) throw new NoSuchElementException();
         Integer next = curr.linenr;
         curr = curr.link;
         return next;
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

}
