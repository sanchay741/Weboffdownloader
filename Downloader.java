package weboffdownloader;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Downloader  {
	

	// GLOBALLY DECLARED VARIABLES
	
	String linuxfolder;
	String wind;
	String cssreplinks;
	String fsub;
	String cssfinalpath;
	String jsfinalpath;
	Element ele;
	 String operSys;
	 String imagepath;
	 String csspath;
	 String jspath;
	 String imgdirectpath;
	 String cssdirectpath;
	 String jsdirectpath;
	 String intdirectpath;
	 String internalnme;
	
	
	
	FileWriter fw = null ;
	
	HashSet<String> inthash = new HashSet<String>();
	
	static String currenturl;
	
	public String path="E://";
	
	public String linuxpath="//runablejar//";
	
	
	
	String internallinks = null;
	
	
	public  BufferedWriter f;
	
	  public enum OS {
          WINDOWS, LINUX, MAC, SOLARIS
      };// Operating systems.

  String os = null;

 
      
      
   // FOLDER CREATION FOR SAVING IMG,CSS,JS FILES ACCORDING TO OPERATING SYSTEM  
  
 
  public void  foldercreate() throws IOException{
	  try {
	  if (os == null) {
           operSys = System.getProperty("os.name").toLowerCase();
          if (operSys.contains("win")) {
              System.out.println("windows");
              
              
              
        
              
          } else if (operSys.contains("nix") || operSys.contains("nux")
                  || operSys.contains("aix")) {
              System.out.println("linux");
          } else if (operSys.contains("mac")) {
             System.out.println("mac");
          } else if (operSys.contains("sunos")) {
             System.out.println("solaris");
          }
         
          
          if(operSys.contains("win"))                        // MAIN FOLDER CREATION FOR WINDOWS ACCORDING TO OPERATING SYSTEM
          {
        	  java.net.URL w = new URL(currenturl);
      		
        		String	w1 = w.getHost();
        		//System.out.println(w1);
        		
        		File folderwind =  new File(path+"//"+w1);
        		// if (folder.exists()) {
        	         if (folderwind.mkdir()) {
        	              System.out.println("Directory is created!");
        	        } else {
        	              System.out.println("Failed to create directory!");
        	           }
        	         wind =folderwind.getAbsolutePath();
      }
          
          
          
          if(operSys.contains("nix") || operSys.contains("nux")
                  || operSys.contains("aix"))                                    // MAIN FOLDER CREATION FOR LINUX ACCORDING TO OPERATING SYSTEM
          {
		
		java.net.URL u = new URL(currenturl);
		
	String	u1 = u.getHost();
	//System.out.println(u1);
	
	File folder =  new File(linuxpath+"//"+u1);
	// if (folder.exists()) {
         if (folder.mkdir()) {
              System.out.println("Directory is created!");
        } else {
              System.out.println("Failed to create directory!");
           }
         linuxfolder =folder.getAbsolutePath();
 	  }
	 	
			System.out.println("success");
		}
  }
	  catch(Exception e) {e.printStackTrace();}
  }
	  
  
  // METHOD FOR DOWNLOADING IMAGES OF HOMEPAGE
  
 public void writingimages() throws IOException, HttpStatusException,NullPointerException{
	
		try{
		
            // CONNECTION TO URL WITH HELP OF JSOUP
	        Document doc = Jsoup.connect(currenturl).ignoreHttpErrors(true).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
	                .referrer("http://www.google.com")  
	                .timeout(10 * 1000).get();
	        
	        
	        if(operSys.contains("win"))                  // SUB FOLDER CREATIION FOR IMAGES ACCORDING TO OPERATINGSYSTEM
	        {
	        
	         imagepath=wind+"//"+"images";
	         
	        }
	        else if(operSys.contains("nix") || operSys.contains("nux")
	                  || operSys.contains("aix"))
	        {
	        
	        imagepath= linuxfolder+"//"+"images";
	        }
	        
	        File imgfile = new File(imagepath);
      	  if (!imgfile.exists()) {
              if (imgfile.mkdir()) {
                   System.out.println("Directory is created!");
             } else {
                   System.out.println("Failed to create directory!");
                }
              
      	  }
      	  String img=imgfile.getAbsolutePath();
	        
      	  Elements media = doc.select("img[src~=(?i)\\.(png|jpe?g|gif|svg|ttf)]");   //SELECTION OF IMAGES
      
	        
	        
	         
	        System.out.println("\n Media"+media.size());                   // IMAGES
	        for(Element imgs : media)
	       try {
	        String abul = imgs.attr("abs:src");
	        
	        int indexname = abul.lastIndexOf("/");                      // FINDING IMAGE NAME WITH EXTENSION IN IMAGE LINKS
	        
	        if (indexname == abul.length())
	        {
	        	abul = abul.substring(1, indexname);
	        }
	        indexname = abul.lastIndexOf("/");
	        
	       
	        String name = abul.substring(indexname, abul.length());
	        
	        int temp = name.lastIndexOf(".");
	        String sub1=name.substring(1, temp);
	        
	       
	        String sub2 = name.substring(temp,temp+4);
	       
	          fsub = sub1+sub2;                                             // JOINING IMAGE NAME WITH EXTENSION
	        System.out.println("IMAGE NAMES :  "+fsub);
	        if(fsub.contains("?"))
     		{
     			int fname = fsub.lastIndexOf("?");
     			fsub=fsub.substring(0,fname);
     		
     			
     			
     		}
	        
	        
	       
	     	URL url1 = new URL(abul);
	    	InputStream in = url1.openStream();
	     	OutputStream out = new BufferedOutputStream(new FileOutputStream(img+"//" + fsub));   // DOWNLOADING IMAGES TO FOLDER
	    	for (int b; (b = in.read()) != -1;) {
				out.write(b);
			
			
			
			}
	    	out.close();
			in.close();
	        }catch(Exception e) {e.printStackTrace();}
	   
		 } catch (NullPointerException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } catch (HttpStatusException e) {
		        e.printStackTrace();
		 }
		catch(Exception e) {e.printStackTrace();}
       
	        
  }  
 
 
 // METHOD FOR DOWNLOADING CSS FILES OF HOMPEPAGE
  
  public void writingcss() throws IOException, MalformedURLException, UnsupportedEncodingException, HttpStatusException,NullPointerException {
	  
	 try { 
		// CONNECTING URL WITH HELP OF JSOUP
		 
	  HashSet<String> hs=new HashSet<String>();
	  Document doc = Jsoup.connect(currenturl).ignoreHttpErrors(true).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
              .referrer("http://www.google.com")              
              .timeout(10 * 1000).get();
	   
	  if(operSys.contains("win"))                                  // SUB FOLDER CREATIOIN FOR CSS FILES ACORDING TO OPERATINGSYSTEM                 
      {
      
       csspath=wind+"//"+"css";
       
      }
      else if(operSys.contains("nix") || operSys.contains("nux")
                || operSys.contains("aix"))
      {
      
      csspath= linuxfolder+"//"+"css";
      }
	  
	  File cssfile = new File(csspath);
  	  if (!cssfile.exists()) {
            if (cssfile.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
  	  }
  	  String cssfolder=cssfile.getAbsolutePath();
	  
  	  
  	  
  	  Elements css = doc.getElementsByTag("link") ;                          // CSS LINKS
      
	  
	  for(Element csslinks : css)
      try{
      
      	
      	hs.add(csslinks.attr("abs:href"));	        
      	}catch(Exception e) {e.printStackTrace();}
     
      
   
	  for(String two: hs)                                                 //CSS FILES CONTENT
     try{
     	if(two.contains(".css"))
     	{
     		int iname = two.lastIndexOf("/");                           // TAKING OUT CSS NAME WITH EXTENSION
     		if(iname==two.length()) {
     			two=two.substring(1, iname);
     		}
     		System.out.println("css links final : "+two);  
     		
     		iname = two.lastIndexOf("/")+1;
     		String nme = two.substring(iname,two.length());
     	//	System.out.println("css final : " +nme);
     		if(nme.contains("?"))
     		{
     			int cname = nme.lastIndexOf("?");                        // CONDITIN FOR REMOVING ? FROM THE FILE NAME
     			nme=nme.substring(0, cname);
     		//	System.out.println("css with ?   "+nme);
     		}
     		
     		
     		//System.out.println("CSS LINKS.."+two);
  	
     		
        		
     		 			
    		URL url1 = new URL(two);
     		java.io.InputStream in1 = url1.openStream();
     		f = new BufferedWriter( new FileWriter(cssfolder+"//" +nme));          // DOWNLOADING CSS TO FOLDER
     		for(int d;(d=in1.read()) !=-1;) {
     			f.write(d);
     			
     		}
     		f.close();
     		in1.close();
     	}
     }catch(Exception e) {e.printStackTrace();}
	 } catch (NullPointerException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (HttpStatusException e) {
	        e.printStackTrace();
           
	 }
	 catch(Exception e) {e.printStackTrace();}
	   
  }
 
  
 
  
  // METHOD FOR DOWNLODING JS OF HOMEPAGE OF URL
  
  public void writingjs() throws IOException, MalformedURLException, HttpStatusException,NullPointerException{
	  
	  try{
		  
		  
	  // CONNECTING URL WITH HELP OF JSOUP
	  HashSet<String> set=new HashSet<String>();
  	
	  Document doc = Jsoup.connect(currenturl).ignoreHttpErrors(true).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
              .referrer("http://www.google.com")  
              .timeout(10 * 1000).get();   
	  
	  if(operSys.contains("win"))                                  // SUB FOLDER CREATIION FOR JS FILES ACCORDING TO OPERATINGSYSTEM 
      {
      
       jspath=wind+"//"+"js";
       
      }
      else if(operSys.contains("nix") || operSys.contains("nux")
                || operSys.contains("aix"))
      {
      
      jspath= linuxfolder+"//"+"js";
      }
	  
	 File jsfile = new File(jspath);
  	  if (!jsfile.exists()) {
            if (jsfile.mkdir()) {
                System.out.println("Directory is created!");
            } else {
               System.out.println("Failed to create directory!");
           }
  	  }
  	  String jsfolder =jsfile.getAbsolutePath();

		
 	Elements scriptElements = doc.getElementsByTag("script");                       // FINDING JS LINKS
 	 
 	
		
		for(Element  ele:scriptElements)
	try	{	
			set.add(ele.attr("abs:src"));
		}catch(Exception e) {e.printStackTrace();}
		
			// System.out.println("JS FILES : "+set); 
			 
			 for(String jscontent : set)
			 try{
				 if(jscontent.contains(".js"))
				 {
					 int jsname = jscontent.lastIndexOf("/");                       // FINDING FILE NAME WITH JS EXTENSION
					 if(jsname==jscontent.length()) {
						 jscontent=jscontent.substring(1, jsname);
					 }
					 jsname=jscontent.lastIndexOf("/")+1;
					 String jquery = jscontent.substring(jsname, jscontent.length());
					 System.out.println("JS file : "+jquery );
					 
					 if(jquery.contains("?"))                                     // CONDITION FOR REMOVING ? FROM FILE NAME
			     		{
			     			int jname = jquery.lastIndexOf("?");
			     			jquery=jquery.substring(0, jname);
			     		//	System.out.println(" js file final : "+jquery);
			     		}
					 if(jquery.contains("#"))
					 {
						 int jshashname=jquery.lastIndexOf("#");                  // CONDITION FOR REMOVING # FROM FILE NAME
						 jquery=jquery.substring(0, jshashname);
						// System.out.println("final JS hash file : "+jquery);
					 }
					 
					 
					 URL url2 = new URL(jscontent);
					 java.io.InputStream IN = url2.openStream();
					 f = new BufferedWriter( new FileWriter(jsfolder+"//" +jquery));  // DOWNLOADING JS TO FOLDER
			     		for(int j;(j=IN.read()) !=-1;) {
			     			f.write(j);
				 }
			     		f.close();
			     		IN.close();
			 
				 }
			 }catch(Exception e) {e.printStackTrace();}
			 
	  } catch (NullPointerException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (HttpStatusException e) {
	        e.printStackTrace();
			 
	  }catch(Exception e) {e.printStackTrace();}
	 
	  
	  
  }
  
 
 // METHOD FOR DOWNLOADING HOME PAGE (INDEX)  WITH REPLACEMENT OF IMAGES,CSS,JS LINKS ACCORDING TO OUR DOWNLOADED FILES PATHS

public void replacement() throws IOException, HttpStatusException,NullPointerException{
	  
	  try{
	  
	
		  
		  if(operSys.contains("win"))
	      {
	      
			  imgdirectpath = wind+"\\images\\";                                 // FOLDER NAME FOR REPLACING LINKS
	       
	      }
	      else if(operSys.contains("nix") || operSys.contains("nux")
	                || operSys.contains("aix"))
	      {
	      
	    	  imgdirectpath = linuxfolder+"//images//";
	      }
	     
	  
	  Document doc = Jsoup.connect(currenturl).ignoreHttpErrors(true).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
              .referrer("http://www.google.com")  
              .timeout(10 * 1000).get();
	 
	  
	  
	  // REPLACEMENT OF IMAGE LINKS
	  Elements media = doc.select("img[src~=(?i)\\.(png|jpe?g|gif|svg|ttf)]");          //IMAGE SELECTION
	  Elements css = doc.getElementsByTag("link") ;
	  
	  HashSet<String> hset =new HashSet<String>();
	  
	  for(Element imgs : media)
	     try   {
	        String abul = imgs.attr("abs:src");
	        
	      //  System.out.println("absolute img : "+abul);
	        
	        int indexname = abul.lastIndexOf("/");                                    // FINDING IMAGE LINKS FOR REPLACEMENT
	        
	        if (indexname == abul.length())
	        {
	        	abul = abul.substring(1, indexname);
	        }
	        indexname = abul.lastIndexOf("/");
	        
	       
	        String name = abul.substring(indexname, abul.length());
	        
	        int temp = name.lastIndexOf(".");
	        String sub1=name.substring(1, temp);
	        
	       
	        String sub2 = name.substring(temp,temp+4);
	       
	          fsub = sub1+sub2;
	        //System.out.println("IMAGE NAMES :  "+fsub);
	        if(fsub.contains("?"))
    		{
    			int fname = fsub.lastIndexOf("?");
    			fsub=fsub.substring(0,fname);
    		//	System.out.println("final img file : "+fsub);
    			
    			
    		}
	        String imagefinalpath = imgdirectpath+fsub;                         // FINAL PATH FOR REPLACEMENT OF LINKS BY ADDING FOLDERPATH AND FILENAME
	       
	       hset.add(imgs.attr("src")) ;
	    
	        
	       for(String rep : hset)
	       {
	    	   if (rep.contains(imgs.attr("src")))
	    			   {
	    		         System.out.println(imgs.attr("src", imagefinalpath));   // REPLACEMENT OF IMAGE LINKS BY OUR PATH
	    			   }
	    	   String html=doc.html();
		       
	    	   
		        
		        
		        fw = new FileWriter(wind+"//"+"Index.html");               // WRITING REPLACED LINKS TO HTML FILE
		        fw.append(html.toString());
		        f = new BufferedWriter(fw);
		        f.write(html);
		        f.newLine();
		        
	       }
	       
	        
	        
	        f.close();
	          
	  }catch(IOException ex) {ex.printStackTrace();}
	 
	

	  if(operSys.contains("win"))
      {
      
		  cssdirectpath = wind+"\\css\\";                                   // FOLDER NAME FOR REPLACING LINKS
       
      }
      else if(operSys.contains("nix") || operSys.contains("nux")
                || operSys.contains("aix"))
      {
      
    	  cssdirectpath = linuxfolder+"//css//";
      }

	                                                                      
	  
	// REPLACEMENT OF CSS LINKS
	  
	  for(Element csslinks : css)                                             // CSS LINKS
	      try{
	      
	      	
	     String cssrep= csslinks.attr("abs:href")    ;    
	      	
	     //System.out.println("hashset"+hs);
	      
	    
	     	if(cssrep.contains(".css"))                                       // FINDING CSS NAMES FOR REPLACEMENT
	     	{
	     		int iname = cssrep.lastIndexOf("/");
	     		if(iname==cssrep.length()) {
	     			cssrep=cssrep.substring(1, iname);
	     		}
	     		//System.out.println("css links final : "+two);  
	     		
	     		iname = cssrep.lastIndexOf("/")+1;
	     		String nme = cssrep.substring(iname,cssrep.length());
	     		//System.out.println("css final : " +nme);
	     		if(nme.contains("?"))
	     		{
	     			int cname = nme.lastIndexOf("?");
	     			nme=nme.substring(0, cname);
	     			//System.out.println("css with ?   "+nme);
	     		}
	     		
	     		cssfinalpath = cssdirectpath+nme;                         // FINAL  PATH FOR REPLACEMENT WITH LINKS BY ADDING FOLDERPATH AND CSS NAME
	     	
	     	
	     	
	     	csslinks.attr("abs:href");
	     	System.out.println(csslinks.attr("href", cssfinalpath));     // REPLACEMENT  OF CSS LINKS WITH  FOLER PATH
	     	
	     	String html=doc.html();
	       // System.out.println(html);
	        
	        
	        fw = new FileWriter(wind+"//"+"Index.html");                  // WRITING REPLACED CSS LINKS TO HTML FILE
	        fw.append(html.toString());
	        f = new BufferedWriter(fw);
	        f.write(html);
	        f.newLine();
	        
	        
	        f.close();
	     	}
	    

	      }catch(Exception e) {e.printStackTrace();}  		     
	   
	     
	    
	  
	  
	  
	  if(operSys.contains("win"))
      {
      
		  jsdirectpath =  wind+"\\js\\";
       
      }
      else if(operSys.contains("nix") || operSys.contains("nux")
                || operSys.contains("aix"))
      {
      
    	  jsdirectpath =  linuxfolder+"//js//";
      }

	  
	  
	  Elements scriptElements = doc.getElementsByTag("script");                            // SELECTION OF JS LINKS
		  
	    
	     	for(Element  ele:scriptElements)
	 		try	{	
	 				String jscontent = ele.attr("abs:src");
	 				
	 			 if(jscontent.contains(".js"))
	 					 {
	 				 int jsname = jscontent.lastIndexOf("/");                           // FINDING JS FILE NAME IN  LINKS           
					 if(jsname==jscontent.length()) {
						 jscontent=jscontent.substring(1, jsname);
					 }
					 jsname=jscontent.lastIndexOf("/")+1;
					 String jquery = jscontent.substring(jsname, jscontent.length());
					// System.out.println("JS file : "+jquery );
					 
					 if(jquery.contains("?"))
			     		{
			     			int jname = jquery.lastIndexOf("?");
			     			jquery=jquery.substring(0, jname);
			     		//	System.out.println(" js file final : "+jquery);
			     		}
					 if(jquery.contains("#"))
					 {
						 int jshashname=jquery.lastIndexOf("#");
						 jquery=jquery.substring(0, jshashname);
						// System.out.println("final JS hash file : "+jquery);
					 }
					  jsfinalpath = jsdirectpath+jquery;                            // FINAL PATH FOR REPLACEMENT BY ADDING FOLDER PATH AND JS FILE NAME
					  ele.attr("abs:Src");
			 		 System.out.println(ele.attr("src", jsfinalpath));          // REPLACEMENT OF JS LINKS WITH OUR PATH
			 			
			 			String html=doc.html();
				      //  System.out.println(html);
				        
				        
				        fw = new FileWriter(wind+"//"+"Index.html");                 // WRITING REPLACED LINKS TO HTML FILE
				        
				        f = new BufferedWriter(fw);
				        f.write(html);
				        f.newLine();
				        
				        
				        f.close();
			 			
	 					 }
	 			 
	 		
	 				 
	 		}catch(Exception e) {e.printStackTrace();}
	     	
	     	 // LINKING INDEX PAGE TO SUB INTERNAL PAGES
 			 
			  if(operSys.contains("win"))
		      {
		      
				  intdirectpath =  wind+"\\";
		       
		      }
		      else if(operSys.contains("nix") || operSys.contains("nux")
		                || operSys.contains("aix"))
		      {
		      
		    	  intdirectpath =  linuxfolder+"//";
		      }
			 Elements link = doc.getElementsByTag("a") ;
			
			 
			 for(Element intlinking : link)                                             // INTERNAL LINKING
			      {
			      
			      	
			     String inthyperlink=intlinking.attr("abs:href") ;
			     
			     
			  
			      
			
			 java.net.URL url = new URL(currenturl);
			
			String hostname = url.getHost();
			
			
				
				if(inthyperlink.contains(hostname))
				{
					int ilinking = inthyperlink.lastIndexOf("/");
		     		if(ilinking==inthyperlink.length()-1) {
		     			inthyperlink=inthyperlink.substring(1, ilinking);
		     		}
		     		 
		     		
		     		ilinking = inthyperlink.lastIndexOf("/")+1;
		     		 internalnme = inthyperlink.substring(ilinking,inthyperlink.length());
		     		
		     		
				}
				String internaldirectpath = intdirectpath+internalnme+".html";
	     		//System.out.println(internaldirectpath);
				intlinking.attr("abs:href") ;
				
				System.out.println(intlinking.attr("href", internaldirectpath));
				   
				String html=doc.html();
			       // System.out.println(html);
			        
			        
			        fw = new FileWriter(wind+"//"+"Index.html");                  // WRITING REPLACED INTERNAL PAGE LINKS TO HTML FILE
			        fw.append(html.toString());
			        f = new BufferedWriter(fw);
			        f.write(html);
			        f.newLine();
			        
			        
			        f.close();
			
			      }   
	 				
}catch(IOException eu) {eu.printStackTrace();}
}
 
  
  // METHOD FOR FINDING INTERNL LINKS OF URL 
  
  public void internallink() throws IOException, HttpStatusException,NullPointerException {
		
		
	  HashSet<String> hash = new HashSet<String>();
	  
	  
	 

	
		try {
			//String url = "https://www.travelportland.com/";
			
		    Document doc = Jsoup.connect(currenturl).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
		               .referrer("http://www.google.com").ignoreHttpErrors(true)
	                .timeout(10 * 1000).get();
		    Elements elements = doc.select("a");               // SELECTION OF ALL HYPERLINKS
		 
		    for(Element element : elements){
		   
		       // System.out.println(element.absUrl("href"));
		        hash.add(element.absUrl("href"));
				//list.add(element.absUrl("href")); //<-------HERE*****
				
		    }
		    
		    java.net.URL u = new URL(currenturl);               // FINDING HOSTNAME OF URL FOR SELECTING INTERNAL LINKS AMONG ALL HYPERLINKS 
		    
		    String host = u.getHost();
		    
		    for(String s:hash) {
		    	
		    	if(s.contains(host))
				{
		    		//System.out.println("Main links...");
					System.out.println("Internal Links : "+s);
					internallinks=s;
					inthash.add(internallinks);                   // ADDING INTERNAL LINKS TO HASHSET FOR FURTHER USE
					
				}
		    			
		    	
		    }
		 } catch (NullPointerException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } catch (HttpStatusException e) {
		        e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
		}
  
  
  // METHOD FOR DOWNLODING IMAGES OF INTERNAL PAGES
  public void writinginternalimages() throws IOException, HttpStatusException,NullPointerException{
		
		try{
		
          for (String internal: inthash)
          {
        	  
            System.out.println("Internal loop links : "+internal);   // CONNECTING INTERNAL LINK WITH HELP OF URL
	        
            Document doc = Jsoup.connect(internal).ignoreHttpErrors(true).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")  
	                .timeout(10 * 1000).get();
	       
	        
            if(operSys.contains("win"))                                           // CHECKING IF IMAGES FOLDER EXIST OR NOT
	        {
	        
	         imagepath=wind+"//"+"images";
	         
	        }
	        else if(operSys.contains("nix") || operSys.contains("nux")
	                  || operSys.contains("aix"))
	        {
	        
	        imagepath= linuxfolder+"//"+"images";
	        }
	        
	        File imgfile = new File(imagepath);
    	  if (!imgfile.exists()) {
              if (imgfile.mkdir()) {
                  System.out.println("Directory is created!");
              } else {
                  System.out.println("Failed to create directory!");
              }
    	  }
    	  
    	  String intimg = imgfile.getAbsolutePath();
    	  
	       
    	  Elements media = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");          // SELECTION OF IMAGES LINKS
     
    	  System.out.println("\n Media"+media.size());                 
	        for(Element imgs : media)
	        {
	        	try {
	        String abul = imgs.attr("abs:src");
	        
	     //   System.out.println("absolute img : "+abul);
	        
	        int indexname = abul.lastIndexOf("/");                                  // FINDING INTERNAL IMAGES NAMES
	        
	        if (indexname == abul.length())
	        {
	        	abul = abul.substring(1, indexname);
	        }
	        indexname = abul.lastIndexOf("/")+1;
	        
	      
	        String name = abul.substring(indexname, abul.length());
	        
	     
	        
	        int temp = name.lastIndexOf(".");
	        String sub1=name.substring(0, temp);
	        
	    
	        
	       
	        String sub2 = name.substring(temp,temp+4);
	        
	      
	       
	        String fstring = sub1+sub2;
	        System.out.println("IMAGE NAMES :  "+fstring);
	        if(fstring.contains("?"))                                           // CONDITIION FOR REMOVING ? FROM IMAGE NAMES
   		{
   			int fname = fstring.lastIndexOf("?");
   			fstring=fstring.substring(0,fname);
   			
   			
   			
   		}
	       
	     	URL url1 = new URL(abul);
	    	InputStream in = url1.openStream();
	     	OutputStream out = new BufferedOutputStream(new FileOutputStream(intimg+"//"  + fstring));  // DOWNLOADING IMAGES TO FOLDER
	    	for (int b; (b = in.read()) != -1;) {
				out.write(b);
			
			
			
			}
	    	out.close();
			in.close();
	        }
	        catch(Exception e) {e.printStackTrace();}
          }
		 }
		 } catch (NullPointerException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } catch (HttpStatusException e) {
		        e.printStackTrace();
		}catch(Exception e) {e.printStackTrace();}	        
}  
 
  
  
  
  
  // METHOD FOR DOWNLOADING CSS OF INTERNAL PAGES
  
  public void writinginternalcss() throws IOException, MalformedURLException, UnsupportedEncodingException, HttpStatusException,NullPointerException {
	  
	  try{
	  HashSet<String> hs=new HashSet<String>();
	  
	  for(String internalcss : inthash)             // CONNECTING INTERNAL LINK WITH JSOUP
	  {
	  Document doc = Jsoup.connect(internalcss).ignoreHttpErrors(true).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
              .referrer("http://www.google.com")  
              .timeout(10 * 1000).get();
	  
	  if(operSys.contains("win"))                                     // CHECKING CSS FOLDER EXIST OR NOT
      {
      
       csspath=wind+"//"+"css";
       
      }
      else if(operSys.contains("nix") || operSys.contains("nux")
                || operSys.contains("aix"))
      {
      
      csspath= linuxfolder+"//"+"css";
      }
	  
	  File cssfile = new File(csspath);
  	  if (!cssfile.exists()) {
            if (cssfile.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
  	  }
  	  String intcss = cssfile.getAbsolutePath();
	  Elements css = doc.getElementsByTag("link") ;                          // SELECTION OF CSS LINKS
      for(Element csslinks : css)
      {
      
      	
      	hs.add(csslinks.attr("abs:href"));	        
      	}
     //System.out.println("hashset"+hs);
      
     for(String two: hs)                              
     {
     	if(two.contains(".css"))
     	{
     		int iname = two.lastIndexOf("/");                                // FINDING CSS FILE NAMES 
     		if(iname==two.length()) {
     			two=two.substring(1, iname);
     		}
     		
     		iname = two.lastIndexOf("/")+1;
     		String nme = two.substring(iname,two.length());
     		System.out.println("css file : " +nme);
     		if(nme.contains("?"))
     		{
     			int cname = nme.lastIndexOf("?");                         // CONDITION FOR REMOVING ? FROM FILE NAMES
     			nme=nme.substring(0, cname);
     	
     		}
     		
    	  		 			
    		URL url1 = new URL(two);
     		java.io.InputStream in1 = url1.openStream();
     		f = new BufferedWriter( new FileWriter(intcss+"//" +nme));          // DOWNLOADING CSS FILES TO FOLDER
     		for(int d;(d=in1.read()) !=-1;) {
     			f.write(d);
     			
     		}
     		f.close();
     		in1.close();
     	}
     }
     }    
	  } catch (NullPointerException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (HttpStatusException e) {
	        e.printStackTrace();
           
	  }catch(Exception e) {e.printStackTrace();}
	 	  	  
  } 
  
  
  
  // METHOD FOR DOWNLOADING JS FILES OF INTERNAL PAGES
  
  public void writinginternaljs() throws IOException, MalformedURLException, HttpStatusException,NullPointerException {
	  
	 
	  
	  HashSet<String> set=new HashSet<String>();
	  
	  for(String internaljs : inthash)                                // CONNECTING INTERNAL LINKS WITH JSOUP
	  {
		  System.out.println("Internal js links : " +internaljs);
  	
	  Document doc = Jsoup.connect(internaljs).ignoreHttpErrors(true).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
              .referrer("http://www.google.com")  
              .timeout(10*1000).get();                                               
	  
	  if(operSys.contains("win"))                                     // CHECKING JS FOLDDER EXIST OR NOT
      {
      
       jspath=wind+"//"+"js";
       
      }
      else if(operSys.contains("nix") || operSys.contains("nux")
                || operSys.contains("aix"))
      {
      
      jspath= linuxfolder+"//"+"js";
      }
	  File jsfile = new File(jspath);
  	  if (!jsfile.exists()) {
            if (jsfile.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
  	  }
  	  String intjs = jsfile.getAbsolutePath();
  	  
  	  
  	  try{
  	 
 	Elements scriptElements = doc.getElementsByTag("script");                  // SELECTING JS LINKS
 	 
 	
		
		for(Element  ele:scriptElements)
		{	
			set.add(ele.attr("abs:src"));
		}
		
			// System.out.println("JS FILES : "+set); 
			 
			 for(String jscontent : set)
			 {
				 if(jscontent.contains(".js"))
				 {
					 int jsname = jscontent.lastIndexOf("/");                    // FINDING  JS FILE NAMES 
					 if(jsname==jscontent.length()) {
						 jscontent=jscontent.substring(1, jsname);
					 }
					 jsname=jscontent.lastIndexOf("/")+1;
					 String jquery = jscontent.substring(jsname, jscontent.length());
					 System.out.println("JS FILES : "+jquery );
					 
					 if(jquery.contains("?"))                                   // CONDITION FOR REMOVING   ? FROM FILE NAMES
			     		{
			     			int jname = jquery.lastIndexOf("?");
			     			jquery=jquery.substring(0, jname);
			     		
			     		}
					 if(jquery.contains("#"))
					 {
						 int jhashname=jquery.lastIndexOf("#");               // CONDITION FOR REMOVING # FROM FILE NAMES
						 jquery=jquery.substring(0, jhashname);
						
					 }
					 
					 URL url2 = new URL(jscontent);
					 java.io.InputStream IN = url2.openStream();
					 f = new BufferedWriter( new FileWriter(intjs+"//" +jquery));  // DOWNLOADING JS FILES TO FOLDER
			     		for(int j;(j=IN.read()) !=-1;) {
			     			f.write(j);
				 }
			     		f.close();
			     		IN.close();
			 
				 }
			 
			 }
			 
  	 } catch (NullPointerException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } catch (HttpStatusException e) {
         e.printStackTrace();
  	  }catch(Exception e) {e.printStackTrace();}
  	 
        	
        }
	          
	  
  }
  
  
  
  
  // METHOD FOR DOWNLOADING INTERNAL PAGES  WITH REPLACEMENT OF IMAGES,CSS,JS LINKS ACCORDING TO OUR DOWNLOADED FILES PATHS
  
  public void internalreplacement() throws IOException, MalformedURLException, HttpStatusException,NullPointerException{
	  
	
	// FOR INTERNAL PAGE NAMES
	  
	  if(operSys.contains("win"))
      {
      
		  imgdirectpath = wind+"\\images\\";
       
      }
      else if(operSys.contains("nix") || operSys.contains("nux")
                || operSys.contains("aix"))
      {
      
    	  imgdirectpath = linuxfolder+"//images//";
      }
	     
	  for(String internalink : inthash)
	  {
		  Document doc = Jsoup.connect(internalink).ignoreHttpErrors(true).ignoreContentType(true).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
	              .referrer("http://www.google.com")  
	              .timeout(10*1000).get(); 
		  
		  
		  int internalname = internalink.lastIndexOf("/");       // FINDING INTERNAL PAGE NAMES FOR DOWNLOAD OF PAGES
			
		  if(internalname== internalink.length()-1) {
			  internalink = internalink.substring(1, internalname);
		  }
		 // System.out.println("last index"+internalname);
		 // System.out.println("length of string"+internalink.length());
		  internalname  = internalink.lastIndexOf("/")+1;
		  String finalname = internalink.substring(internalname, internalink.length());
		  	
			
	         System.out.println("Internal Page Names : "+finalname);
	         
	         // FOR IMAGES REPLACEMENT  
		  
		 
		  
		  Elements media = doc.select("img[src~=(?i)\\.(png|jpe?g|gif|svg|ttf)]");          // SELECTING IMAGES
		
		  
		  HashSet<String> hset =new HashSet<String>();
		  
		  for(Element imgs : media)
		     try   {
		        String abul = imgs.attr("abs:src");
		        
		      //  System.out.println("absolute img : "+abul);
		        
		        int indexname = abul.lastIndexOf("/");                        // FINDING IMAGE NAME FOR REPLACEMENT
		        
		        if (indexname == abul.length())
		        {
		        	abul = abul.substring(1, indexname);
		        }
		        indexname = abul.lastIndexOf("/");
		        
		       
		        String name = abul.substring(indexname, abul.length());
		        
		        int temp = name.lastIndexOf(".");
		        String sub1=name.substring(1, temp);
		        
		       
		        String sub2 = name.substring(temp,temp+4);
		       
		          fsub = sub1+sub2;
		      
		        if(fsub.contains("?"))
	    		{
	    			int fname = fsub.lastIndexOf("?");
	    			fsub=fsub.substring(0,fname);
	    		
	    			
	    			
	    		}
		        String imagefinalpath = imgdirectpath+fsub;                       // FINAL PATH FOR REPLACEMENT BY ADDING FOLDER PATH AND IMAGE NAMES
		      
		       hset.add(imgs.attr("src")) ;
		     
		        
		       for(String rep : hset)
		       {
		    	   if (rep.contains(imgs.attr("src")))
		    			   {
		    		        System.out.println(imgs.attr("src", imagefinalpath));         // REPLACING IMAGE LINKS WITH OUR FINAL PATH
		    			   }
		    	   String html=doc.html();
			     
		    	   
			        
			        
			        fw = new FileWriter(wind+"//"+finalname+".html");                  // WRITING OF REPLACED LINKS TO HTML FILE
			        fw.append(html.toString());
			        f = new BufferedWriter(fw);
			        f.write(html);
			        f.newLine();
			        
		       }
		       
		         f.close();
		               
		          
		  }catch(IOException ex) {ex.printStackTrace();}
		 
		
		  // FOR CSS REPLACEMENT
		  
		  
		  if(operSys.contains("win"))
	      {
	      
			  cssdirectpath = wind+"\\css\\";
	       
	      }
	      else if(operSys.contains("nix") || operSys.contains("nux")
	                || operSys.contains("aix"))
	      {
	      
	    	  cssdirectpath = linuxfolder+"//css//";
	      }

		  
		  Elements css = doc.getElementsByTag("link") ;                         // SECTION OF CSS LINKS
		  for(Element csslinks : css)
		      try{
		      
		      	
		     String cssrep= csslinks.attr("abs:href")    ;    
		      	
		     //System.out.println("hashset"+hs);
		      
		    
		     	if(cssrep.contains(".css"))
		     	{
		     		int iname = cssrep.lastIndexOf("/");                       // FINDING CSS FILE NAMES
		     		if(iname==cssrep.length()) {
		     			cssrep=cssrep.substring(1, iname);
		     		}
		     		
		     		
		     		iname = cssrep.lastIndexOf("/")+1;
		     		String nme = cssrep.substring(iname,cssrep.length());
		     		
		     		if(nme.contains("?"))
		     		{
		     			int cname = nme.lastIndexOf("?");
		     			nme=nme.substring(0, cname);
		     			
		     		}
		     		
		     		cssfinalpath = cssdirectpath+nme;                       // FINAL PATH FOR REPLACMENT OF LINKS BY ADDING FOLDER PATH AND FILE NAMES
		     	
		     	
		     	
		     	csslinks.attr("abs:href");
		     	System.out.println(csslinks.attr("href", cssfinalpath));     // REPLCEMENT OF CSS LINKS WITH OUR FINAL PATH
		     	
		     	String html=doc.html();
		       // System.out.println(html);
		        
		        
		        fw = new FileWriter(wind+"//"+finalname+".html");                    // WRITING REPLACED LINKS TO HTML FILE
		        fw.append(html.toString());
		        f = new BufferedWriter(fw);
		        f.write(html);
		        f.newLine();
		        
		        
		        f.close();
		     	}
		    

		      }catch(Exception e) {e.printStackTrace();}  
		  
		
		  // FOR JS REPLACMENT
		  
		  if(operSys.contains("win"))
	      {
	      
			  jsdirectpath =  wind+"\\js\\";
	       
	      }
	      else if(operSys.contains("nix") || operSys.contains("nux")
	                || operSys.contains("aix"))
	      {
	      
	    	  jsdirectpath =  linuxfolder+"//js//";
	      }
		  
		  
		  Elements scriptElements = doc.getElementsByTag("script");                            // FINDING JS LINKS
		  
		    
	     	for(Element  ele:scriptElements)
	 		try	{	
	 				String jscontent = ele.attr("abs:src");
	 				
	 			 if(jscontent.contains(".js"))
	 					 {
	 				 int jsname = jscontent.lastIndexOf("/");                               // FINDING JS FILE NAMES
					 if(jsname==jscontent.length()) {
						 jscontent=jscontent.substring(1, jsname);
					 }
					 jsname=jscontent.lastIndexOf("/")+1;
					 String jquery = jscontent.substring(jsname, jscontent.length());
					// System.out.println("JS file : "+jquery );
					 
					 if(jquery.contains("?"))
			     		{
			     			int jname = jquery.lastIndexOf("?");
			     			jquery=jquery.substring(0, jname);
			     		//	System.out.println(" js file final : "+jquery);
			     		}
					 if(jquery.contains("#"))
					 {
						 int jshashname=jquery.lastIndexOf("#");
						 jquery=jquery.substring(0, jshashname);
						// System.out.println("final JS hash file : "+jquery);
					 }
					  jsfinalpath = jsdirectpath+jquery;                               // FINAL PATH FOR REPLACEMENT BY ADDING FOLDER PATH AND FILE NAME
					  ele.attr("abs:Src");
			 			 System.out.println(ele.attr("src", jsfinalpath));        // REPLACEMENT OF JS LINKS WITH FINAL PATH
			 			
			 			String html=doc.html();
				      //  System.out.println(html);
				        
				        
				        fw = new FileWriter(wind+"//"+finalname+".html");                   // WRITING REPLACED LINKS TO HTML FILE
				        
				        f = new BufferedWriter(fw);
				        f.write(html);
				        f.newLine();
				        
				        
				        f.close();
			 			
	 					 }
	 			 
	 			 // LINKING INTERNAL PAGE TO INTERNAL PAGES
	 			 
				  if(operSys.contains("win"))
			      {
			      
					  intdirectpath =  wind+"\\";
			       
			      }
			      else if(operSys.contains("nix") || operSys.contains("nux")
			                || operSys.contains("aix"))
			      {
			      
			    	  intdirectpath =  linuxfolder+"//";
			      }
				 Elements link = doc.getElementsByTag("a") ;
				
				 
				 for(Element intlinking : link)                                             // INTERNAL LINKING
				      {
				      
				      	
				     String inthyperlink=intlinking.attr("abs:href") ;
				     
				     
				  
				      
				
				 java.net.URL url = new URL(currenturl);
				
				String hostname = url.getHost();
				
				
					
					if(inthyperlink.contains(hostname))
					{
						int ilinking = inthyperlink.lastIndexOf("/");
			     		if(ilinking==inthyperlink.length()-1) {
			     			inthyperlink=inthyperlink.substring(1, ilinking);
			     		}
			     		 
			     		
			     		ilinking = inthyperlink.lastIndexOf("/")+1;
			     		 internalnme = inthyperlink.substring(ilinking,inthyperlink.length());
			     		String internaldirectpath = intdirectpath+internalnme+".html";
			     		
			     		//System.out.println(internaldirectpath);
						intlinking.attr("abs:href") ;
						
						System.out.println(intlinking.attr("href", internaldirectpath));
						   
						String html=doc.html();
					       // System.out.println(html);
					        
					        
					        fw = new FileWriter(wind+"//"+finalname+".html");                  // WRITING REPLACED INTERNAL PAGE LINKS TO HTML FILE
					        fw.append(html.toString());
					        f = new BufferedWriter(fw);
					        f.write(html);
					        f.newLine();
					        
					        
					        f.close();
					
			     		
					}
					
		     		
				      }   
	 			
	 			
	 				
	 				 
	 		}catch(Exception e) {e.printStackTrace();}
	 				
	  
	  }
	  }
  

	
	public static void main(String[] args) throws IOException {
		
		 	Downloader dr = new Downloader();
		 	
		 	String os=null;
		 	String operSys= null;
		 	
		 	 if (os == null) {
		           operSys = System.getProperty("os.name").toLowerCase();
		          if (operSys.contains("win")) {
		            //  System.out.println("windows");
		              
		          }
		                            
		          } else if (operSys.contains("nix") || operSys.contains("nux")
		                  || operSys.contains("aix")) {
		             // System.out.println("linux");
		          }
		
		
		//BufferedReader bufferread = new BufferedReader(new FileReader("//runablejar//listurl.txt//"));
		 	 if(operSys.contains("win")){
		BufferedReader bufferread = new BufferedReader(new FileReader("C://Users//Sanchay//Desktop//listurl.txt//"));

		while ((currenturl = bufferread.readLine()) != null) {
		  System.out.println(currenturl);
		  if(!(currenturl.startsWith("http://") || currenturl.startsWith("https://")))
		  {
			  currenturl= "http://" + currenturl;
		 }
		  System.out.println("proper url : "+currenturl);
		try {
			dr.foldercreate();
			
			
			dr.writingimages();
			dr.writingcss();
			dr.writingjs();
			dr.replacement();
			dr.internallink();
			dr.writinginternalimages();
		    dr.writinginternalcss();
			dr.writinginternaljs();
			dr.internalreplacement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		bufferread.close();
		
	}
		 	 if(operSys.contains("nix") || operSys.contains("nux")
	                  || operSys.contains("aix")) {
		 		BufferedReader bufferread = new BufferedReader(new FileReader("//runablejar//listurl.txt//"));

		 		while ((currenturl = bufferread.readLine()) != null) {
		 		  System.out.println(currenturl);
		 		 if(!(currenturl.startsWith("http://") || currenturl.startsWith("https://")))
		 		  {
		 			  currenturl= "http://" + currenturl;
		 		  }
		 		  System.out.println("proper url : "+currenturl);
		 		try {
		 			dr.foldercreate();
		 			dr.writingimages();
		 			dr.writingcss();
		 			dr.writingjs();
		 			dr.replacement();
		 			dr.internallink();
		 		    dr.writinginternalimages();
		 		    dr.writinginternalcss();
		 			dr.writinginternaljs();
		 			dr.internalreplacement();
		 		} catch (IOException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		 		}
		 		bufferread.close();
		 		
		 	}
	}
} 	 
		 
		 	 
		
	


