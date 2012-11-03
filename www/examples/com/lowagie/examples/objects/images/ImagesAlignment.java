/*
 * $Id: ImagesAlignment.java 3393 2008-05-16 21:33:55Z xlv $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:
 * http://itextdocs.lowagie.com/tutorial/
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */
package com.lowagie.examples.objects.images;

import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Testing Image alignment.
 */
public class ImagesAlignment {

	/**
	 * Generates a PDF with Images that are aligned.
	 * @param args no arguments needed
	 */
	public static void main(java.lang.String[] args) {
		System.out.println("Image alignment");
        // step 1: creation of a document-object
        Document document = new Document();
        try {
            // step 2:
            // we create a writer that listens to the document
            // and directs a PDF-stream to a file
            PdfWriter.getInstance(document, new FileOutputStream("imagesAlignment.pdf"));
            
            // step 3: we open the document
            document.open();
            
            // step 4: content
            Image gif = Image.getInstance("vonnegut.gif");
            gif.setAlignment(Image.RIGHT | Image.TEXTWRAP);
            Image jpeg = Image.getInstance("otsoe.jpg");
            jpeg.setAlignment(Image.MIDDLE);
            Image png = Image.getInstance("hitchcock.png");
            png.setAlignment(Image.LEFT | Image.UNDERLYING);
            
            for (int i = 0; i < 100; i++) {
                document.add(new Phrase("Who is this? "));
            }
            document.add(gif);
            for (int i = 0; i < 100; i++) {
                document.add(new Phrase("Who is this? "));
            }
            document.add(Chunk.NEWLINE);
            document.add(jpeg);
            for (int i = 0; i < 100; i++) {
                document.add(new Phrase("Who is this? "));
            }
            document.add(png);
            for (int i = 0; i < 100; i++) {
                document.add(new Phrase("Who is this? "));
            }
            document.add(gif);
            for (int i = 0; i < 100; i++) {
                document.add(new Phrase("Who is this? "));
            }
            document.add(Chunk.NEWLINE);
            document.add(jpeg);
            for (int i = 0; i < 100; i++) {
                document.add(new Phrase("Who is this? "));
            }
            document.add(png);
            for (int i = 0; i < 100; i++) {
                document.add(new Phrase("Who is this? "));
            }
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        // step 5: we close the document
        document.close();
	}
} 
