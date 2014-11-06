/*
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
package es.javiergomez.tutorial.java.generics;

/**
 * @author Javier
 *
 */
public class GenericMethod {

    // Generic static method
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
               p1.getValue().equals(p2.getValue());
    }
    
    
    
	public class Pair<K, V> {
	
	    private K key;
	    private V value;
	
	    // Generic constructor
	    public Pair(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }
	
	    // Generic methods
	    public void setKey(K key) { this.key = key; }
	    public void setValue(V value) { this.value = value; }
	    public K getKey()   { return key; }
	    public V getValue() { return value; }
	}
    

    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GenericMethod myClass = new GenericMethod();	
		
		Pair<Integer, String> p1 = myClass.new Pair<>(1, "apple");
		Pair<Integer, String> p2 = myClass.new Pair<>(2, "pear");
		
		boolean same = GenericMethod.<Integer, String>compare(p1, p2);
		System.out.println(new Boolean(same).toString());

		
		Pair<String, Boolean> p3 = myClass.new Pair<>("apple", true);
		Pair<String, Boolean> p4 = myClass.new Pair<>("apple", true);
		
		boolean same2 = GenericMethod.<String, Boolean>compare(p3, p4);
		System.out.println(new Boolean(same2).toString());	
	
	}

}
