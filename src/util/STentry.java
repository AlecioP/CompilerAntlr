package util;


public class STentry {
 
  private int nl;
  private String type;
  private int offset;
  
  public STentry (int n, int os){
	  	nl = n ;
	  	offset = os ;
	  	} 
   
  public STentry (int n, String t, int os){
	  nl = n ;
	  type = t ;
	  offset = os ;
	  }
  
  public void addType (String t){
	  type = t ;
	  }
  
  public String getType (){
	  return type ;
	  }

  public int getOffset (){
	  return offset ;
	  }
  
  public int getNestinglevel (){
	  return nl ;
	  }
  
}  