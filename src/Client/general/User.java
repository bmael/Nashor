/**
 * 
 */
package general;

/**
 * @author bmael
 *
 */
public class User {

	private String name;
	
	public User(){}
	
	public User(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
