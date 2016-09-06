import java.net.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author Akash Thakrar
 */
public class YoutubeSearch {

    static String links[];
    static String extra[];
    static String titles[];
    public static void main(String[] args) throws Exception{
        int i,pgno=1,temp;
        String query;
        String search_link="https://www.youtube.com/results?q=";
        Scanner inp = new Scanner(System.in);
        //Building search_link
        System.out.print("Enter your search query : ");
        query=inp.nextLine();
        query=query.trim();
        int len=query.length();
        for(i=0;i<len;i++){
            if((query.charAt(i))==' '){
                while((query.charAt(i+1))==' '){
                    i++;
                }
                search_link=search_link.concat("+");
            }
            else{
                search_link=search_link.concat(String.valueOf(query.charAt(i)));
            }
        }
        getLinks(search_link);
        getTitles();
        //printAll();
                
        while(true){
            System.out.print("press 1 for previous page\n      2 for next page\n      0 to exit : ");
            temp=inp.nextInt();
            if(temp==0)
                break;
            else if(temp==2){
                pgno++;
                //search_link=search_link+"&page="+pgno;
                getLinks(search_link+"&page="+pgno);
                getTitles();
            }
            else if(temp==1){                
                if(pgno==1){
                    System.out.println("Page 1 reached. Previous page not possible.");
                }
                else{
                    pgno--;
                    getLinks(search_link+"&page="+pgno);
                    getTitles();
                }
            }
        }	
    }   
    static public void getLinks (String pageLink) throws Exception{
        URL link = new URL(pageLink);
        BufferedReader in = new BufferedReader(new InputStreamReader(link.openStream()));        
        int b,i;
        String src=null;
        while((b=in.read()) != -1){
                src=src+(char)b;
        }
        /*String src = null;
        int i;
        try {
            File file = new File("C:\\Users\\Akash Thakrar\\Documents\\NetBeansProjects\\YoutubeSearch\\build\\classes\\source.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
            }
            fileReader.close();
            src = stringBuffer.toString();
        }
        catch (IOException e) {
                    e.printStackTrace();
        }*/
        extra=src.split("\" data-context-item-id=\"");
        links = new String[extra.length-1];
        for(i=1;i<extra.length;i++){
            links[i-1] = "https://www.youtube.com/watch?v="+extra[i].substring(0,11);
            System.out.println(links[i-1]);
        }
    }
    static public void getTitles() throws Exception{
        
        ///////////////////////////////////////////
        ////////////////////////////////////////////
        
        String src = null;
        int i;
        /*try {
            File file = new File("C:\\Users\\Akash Thakrar\\Documents\\NetBeansProjects\\YoutubeSearch\\build\\classes\\source.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
            }
            fileReader.close();
            src = stringBuffer.toString();
        }
        catch (IOException e) {
                    e.printStackTrace();
        }
        //extra=src.split("\" data-context-item-id=\"");*/
        titles = new String[extra.length-1];
        //////////////////////////////////////
        //////////////////////////////////////
        String[] extra1 = new String[7];
        Scanner in = new Scanner(System.in);
        for(i=1;i<extra.length;i++){
            extra1 = extra[i].split("title=");
            titles[i-1] = extra1[3].substring(1,extra1[3].indexOf("rel")-2);
            //System.out.println(titles[i-1]);
            //in.nextLine();
        }
    }
    static public void printAll(){
        int i;
        for(i=1;i<extra.length;i++){
            //System.out.println(links[i]);
            //System.out.println(titles[i]);
            System.out.println(titles[i]+"---"+links[i]);
        }
    }
}
