package sujj.pdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class ReportConvert {
	public static String username ="泰尔用户001";
	public static String phonenumber="1372197321";
	public static String useraddress="西山居 缥缈峰 001";
	
	public static String devicename="CR00000x0001";
	public static String softwareVersion ="v001";
	public static String hardwareVersion= "v002";
	public static String hardwareType = "RS0001";
	public static boolean convertTextToPDF(File file) throws Exception {
		 final String FONT = "resources/fonts/FreeSans.ttf";
	    BufferedReader br = null;
	    final String testid = "ff8080815f902995015f943e53050003";
	    try {

	        Document pdfDoc = new Document(PageSize.A4);
	        String output_file = file.getName().replace(".txt", ".pdf");
	        if(!output_file.contains("."))
	        	output_file = output_file+".pdf";
	        	
	        System.out.println("## writing to: " + output_file);
	        PdfWriter writer = PdfWriter.getInstance(pdfDoc, new FileOutputStream(output_file));
	        writer.setPdfVersion(PdfWriter.VERSION_1_7);
	        BaseFont bf = BaseFont.createFont("C:\\fonts\\simfang.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        
	        Font f = new Font(bf, 12);
	        Font titleF = new Font(bf, 11, Font.BOLD, BaseColor.BLACK);
	        pdfDoc.open();
	        //Font f = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        //BaseFont bf1 = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 
	          
	       // Font myfont = new Font(bf1,12);
	        BaseFont kt = BaseFont.createFont("c:\\fonts\\simka.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        BaseFont bs = BaseFont.createFont("c:\\fonts\\yahei.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        //set the watermark
            Phrase watermark = new Phrase("泰尔实验室", new Font(bs, 60, Font.NORMAL, BaseColor.PINK));
            
            pdfDoc.addTitle("tr technology company");
            
            PdfContentByte canvas = writer.getDirectContentUnder();
            PdfGState state = new PdfGState();
            state.setFillOpacity(0.2f);
            canvas.setGState(state);
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 250, 200, 40); 
            ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 250, 600, 40);
	        pdfDoc.add(new Paragraph("\n"));
	        byte[] buffer = new byte[1024];
	        if (file.exists()) {
	        	
	            br = new BufferedReader(new FileReader(file));
	            String strLine;
	            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file),
	                    "UTF8"));
	            Paragraph testtitleP= 
	            new Paragraph("测试编号: "+testid.toUpperCase()+"\n\n",new Font(bf, 10, Font.BOLD, BaseColor.BLACK));
	            testtitleP.setAlignment(Element.ALIGN_RIGHT);
	            pdfDoc.add(testtitleP);
	            while ((strLine = in.readLine()) != null) {
	            	System.out.println("这一行:"+strLine);;
	                Paragraph para = new Paragraph(strLine + "\n", f);
	                para.setAlignment(Element.ALIGN_JUSTIFIED);
	                pdfDoc.add(para);
	            }
	            
	            Paragraph userinfoP =new Paragraph("用户信息: \n\n",new Font(bf, 12, Font.BOLD, BaseColor.BLACK));
	            userinfoP.setAlignment(Element.ALIGN_LEFT);
	            pdfDoc.add(userinfoP);
	            
	     

	            //
	            PdfPTable userTable = new PdfPTable(3);
	           
	            PdfPCell cell = new PdfPCell(new Phrase("用户姓名",titleF));
	            userTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase("电话", titleF));
	          	userTable.addCell(cell);
	           
	            cell = new PdfPCell(new Phrase("地址", titleF));
	            userTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase(username, f));
	            userTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase(phonenumber, f));
	            userTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase(useraddress, f));
	            userTable.addCell(cell);
	            
	            userTable.setHorizontalAlignment(Element.ALIGN_LEFT);
	            pdfDoc.add(userTable);

	            
	            Paragraph devinfoP =new Paragraph("硬件信息: \n\n",new Font(bf, 12, Font.BOLD, BaseColor.BLACK));
	            devinfoP.setAlignment(Element.ALIGN_LEFT);
	            pdfDoc.add(devinfoP);
	            
	            PdfPTable devTable = new PdfPTable(4);
	            cell = new PdfPCell(new Phrase("设备名称",titleF));
	            devTable.addCell(cell);
	            cell = new PdfPCell(new Phrase("硬件型号",titleF));
	            devTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase("软件版本",titleF));
	            devTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase("硬件版本",titleF));
	            devTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase(devicename, f));
	            devTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase(hardwareType, f));
	            devTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase(softwareVersion, f));
	            devTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase(hardwareVersion, f));
	            devTable.addCell(cell);
	            
	            devTable.setHorizontalAlignment(Element.ALIGN_LEFT);
	            pdfDoc.add(devTable);
	            
	            
	        } else {
	            System.out.println("no such file exists!");
	            return false;
	        }
	        pdfDoc.close();
	    }

	    catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) 
	            br.close();
	    }

	    return true;
	}
	
	public static void main(String[]args)
	{
		File f = new File("C:\\Agent-0.0.1-SNAPSHOT-package\\reportSync\\Up_Resultfile");
		
		try {
			convertTextToPDF(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
