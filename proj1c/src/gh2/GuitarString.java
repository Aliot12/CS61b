package gh2;
// TODO: maybe more imports
import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.apache.commons.collections.Buffer;

import java.util.List;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
    private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int cacapacity = (int)Math.round(SR / frequency);
        buffer = new ArrayDeque61B<>(cacapacity);
        for(int i = 0; i < cacapacity;i++){
            buffer.addFirst(.0);
        }
        // TODO: Initialize the buffer with . You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer with zeros.
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int size = buffer.size();
        while (buffer.size() != 0){
            buffer.removeFirst();
        }
        for(int i = 0; i < size;i++){
            buffer.addFirst(Math.random() - 0.5);
        }
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
       double newDouble = (buffer.removeFirst() + buffer.get(0)) / 2 * DECAY;
        //may error
        buffer.addLast(newDouble);
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        if (!buffer.isEmpty()) return buffer.get(0);
        return 0;
        // TODO: Return the correct thing.
    }
}
    // TODO: Remove all comments that say TODO when you're done.
