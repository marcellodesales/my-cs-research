/**
 * User.java
 *
 * @author Created by Omnicore CodeGuide
 */

package br.ufal.rsacripto;

public class User{
	
	private Rsa rsa;
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	//private Locale local;
	
	public User(String id){
		
	}
	
	public User(){
		this.rsa = new Rsa();
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setRsa(Rsa rsa) {
		this.rsa = rsa;
	}
	
	public Rsa getRsa() {
		return rsa;
	}

	public double getPublicKeyN(){
		return this.rsa.getPublicKey_N();
	}
	
	public double getPublicKeyE(){
		return this.rsa.getPublicKey_E();
	}
	
	public double getPrivateKeyD(){
		return this.rsa.getPrivateKey_D();
	}
	
	public void printAll(){
		System.out.println(this.getPublicKeyN());
		System.out.println(this.getPublicKeyE());
		System.out.println(this.getPrivateKeyD());
	}
	
	/**
	 *
	 */
	public static void main(String[] args){
		User user = new User();
		user.printAll();
		
		String origem = "Vocês gostariam de um prato de banana machucada?";
		RsaSender sender = new RsaSender(origem,user.getRsa());
		System.out.println("Cripted: "+sender.getChriptedMessage());
		RsaReceiver receiver = new RsaReceiver(sender.getChriptedMessage(),user.getRsa());
		System.out.println("Original: "+receiver.getOriginalMessage()+"");
	}

}

