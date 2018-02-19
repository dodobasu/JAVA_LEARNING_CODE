
///////Motherboard Serial Number
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
///////////system IP Address & Mac Address & system name ////
import java.net.InetAddress;
import java.net.NetworkInterface;

///////////system IP Address
import java.net.Inet4Address;
import java.util.Enumeration;

/////  screen resolution ///////
import java.awt.Dimension;
import java.awt.Toolkit;

/**
* This is a simple program to print Syatem Info
*
* @author	includehelp.com
* @since	27/11/2017
* @reviewer	Prativas Basu
*/
public class SystemInfo {
    
   /////// Mother board serial no /////////////////
    public static String getSystemMotherBoard_SerialNumber(){
        try{
            String OSName=  System.getProperty("os.name");
            if(OSName.contains("Windows")){
                return (getWindowsMotherboard_SerialNumber());
            }
            else{
                return (GetLinuxMotherBoard_serialNumber());
            }
        }
        catch(Exception E){
            System.err.println("System MotherBoard Exp : "+E.getMessage());
            return null;
        }
    }
    
    
    private static String getWindowsMotherboard_SerialNumber() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto",".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs =
            "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
              + "Set colItems = objWMIService.ExecQuery _ \n"
              + "   (\"Select * from Win32_BaseBoard\") \n"
              + "For Each objItem in colItems \n"
              + "    Wscript.Echo objItem.SerialNumber \n"
              + "    exit for  ' do the first cpu only! \n"
              + "Next \n";

            fw.write(vbs);
            fw.close();

            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
               result += line;
            }
            input.close();
        }
        catch(Exception E){
             System.err.println("Windows MotherBoard Exp : "+E.getMessage());
        }
        return result.trim();
    } 
    
    
    /**
     * Method for get Linux Machine MotherBoard Serial Number
     * @return 
     */
    private static String GetLinuxMotherBoard_serialNumber() {
        String command = "dmidecode -s baseboard-serial-number";
        String sNum = null; 
        try {   
            Process SerNumProcess = Runtime.getRuntime().exec(command);
            BufferedReader sNumReader = new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
            sNum = sNumReader.readLine().trim();
            SerNumProcess.waitFor();
            sNumReader.close();
        }
        catch (Exception ex) {
            System.err.println("Linux Motherboard Exp : "+ex.getMessage());
            sNum =null;
        }
        return sNum; 
    }



    //////////////////   get mac address ///////////////////////

     public static String getSystemMac(){
        try{
            String OSName=  System.getProperty("os.name");
            if(OSName.contains("Windows")){
                return (getMAC4Windows());
            }
            else{
                String mac=getMAC4Linux("eth0");
                if(mac==null){
                    mac=getMAC4Linux("eth1");
                    if(mac==null){
                        mac=getMAC4Linux("eth2");
                        if(mac==null){
                            mac=getMAC4Linux("usb0");
                        }
                    }
                }	
                return mac;
            }
        }
        catch(Exception E){
            System.err.println("System Mac Exp : "+E.getMessage());
            return null;
        }
    }
    
   
    private static String getMAC4Linux(String name){
        try {
            NetworkInterface network = NetworkInterface.getByName(name);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++){
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
            }
            return (sb.toString());
        }
        catch (Exception E) {
            System.err.println("System Linux MAC Exp : "+E.getMessage());
            return null;
        } 
    } 
	
   
    private static String getMAC4Windows(){
        try{
            InetAddress      addr     =InetAddress.getLocalHost();
            NetworkInterface network  =NetworkInterface.getByInetAddress(addr);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<mac.length;i++){
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
            }
            
            return sb.toString();
        }
        catch(Exception E){
            System.err.println("System Windows MAC Exp : "+E.getMessage());
            return null;
        }
    }
    
    /////////////   SYSTEM NAME /////////////////
    public static String getSystemName(){
	try{
            String systemName  =InetAddress.getLocalHost().getHostName();
            return systemName;    
        }
	catch(Exception E){
            System.err.println("System Name Exp : "+E.getMessage());
            return null;
	}
    }


    /////////////////////////////  SYSTEM IP ADDRESS /////////////

     public static String getSystemIP(){
        try{
            String sysIP="";
            String OSName=  System.getProperty("os.name");
	    if(OSName.contains("Windows")){
                sysIP   =InetAddress.getLocalHost().getHostAddress();
	    }
	    else{
	    	sysIP=getSystemIP4Linux("eth0");
	    	if(sysIP==null){
                    sysIP=getSystemIP4Linux("eth1");
		    if(sysIP==null){
		  	sysIP=getSystemIP4Linux("eth2");
                        if(sysIP==null){
                            sysIP=getSystemIP4Linux("usb0");
                        }
                    }
	   	}
	    }
	    return sysIP;
	}
	catch(Exception E){
            System.err.println("System IP Exp : "+E.getMessage());
            return null;
	}
    }
    
    /**
     * method for get IP of linux System
     * @param name
     * @return 
     */
    private static String getSystemIP4Linux(String name){
        try{
            String ip="";
            NetworkInterface networkInterface = NetworkInterface.getByName(name);
            Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
            InetAddress currentAddress = inetAddress.nextElement();
            while(inetAddress.hasMoreElements()){
                currentAddress = inetAddress.nextElement();
                if(currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress()){
                    ip = currentAddress.toString();
                    break;
                }
            }
            if(ip.startsWith("/")){
                ip=ip.substring(1);
            }
            return ip;
        } 
        catch (Exception E) {
            System.err.println("System Linux IP Exp : "+E.getMessage());
            return null;
        }
    }


    //////////////// SCREEN RESOLUTION //////////////

        public static void main(String[] args) {
        //////////////  MOTHER BOARD ////////////
        String motherBoard_SerialNumber = getSystemMotherBoard_SerialNumber();
        System.out.println("MotherBoard Serial Number : "+motherBoard_SerialNumber);
        ////////////////  OS NAME ///////////////////// 
        System.out.println("MotherBoard Serial Number : "+ System.getProperty("os.name"));
        ///////////  MAC ADDRESS /////////////
         String macAddress = getSystemMac();
        System.out.println("System Mac Address : "+macAddress);

        /////////////////  MAC ADDRESS ///////////////
        String SystmName = getSystemName();
        System.out.println("Computer Name : "+SystmName);

        //////////////////  IP ADDRESS /////////////////////
        String ipAddress = getSystemIP();
        System.out.println("System IP : "+ipAddress);

        /////////////////// JAVA SCREEN RESOLUTION /////////////////

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        short width  = (short)screenSize.getWidth();
        short height = (short)screenSize.getHeight();

        System.out.println("System Height : "+ height);
        System.out.println("System Width : "+ width);
        //////////////////////// ALL JAVA PROPERTIES //////////////////

        System.out.println("Java Version     : "+System.getProperty("java.version"));
        System.out.println("Java VM Version  : "+System.getProperty("java.vm.version"));
        System.out.println("Java VM Vendor   : "+System.getProperty("java.vm.vendor"));
        System.out.println("Java VM Name     : "+System.getProperty("java.vm.name"));
        System.out.println("Java VM specification Version : "+System.getProperty("java.vm.specification.version"));
        System.out.println("Java VM specification Vendor  : "+System.getProperty("java.vm.specification.vendor"));
        System.out.println("Java VM specification Name    : "+System.getProperty("java.vm.specification.name"));
        System.out.println("Java Runtime specification Version : "+System.getProperty("java.specification.version"));
        System.out.println("Java Runtime specification Vendor  : "+System.getProperty("java.specification.vendor"));
        System.out.println("Java Runtime specification Name    : "+System.getProperty("java.specification.name"));


        
    }
   
}