package util;


public class STentryTypes {
 
  private int nl;
  private String type;
  private int offset;
  
  public STentryTypes (int n, int os){
	  	nl = n ;
	  	offset = os ;
	  	} 
   
  public STentryTypes (int n, String t, int os){
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