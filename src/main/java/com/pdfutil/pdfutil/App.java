package com.pdfutil.pdfutil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		PDDocument document = null;
		try {
			document = PDDocument.load(new File("./src/main/resources/original.pdf"));
			System.out.println("Number of pages:" + document.getNumberOfPages());

			/******** Splitter start **********/
			Splitter splitter = new Splitter();

			// splitting the pages of a PDF document
			List<PDDocument> Pages = splitter.split(document);

			// Creating an iterator
			Iterator<PDDocument> iterator = Pages.listIterator();

			// Saving each page as an individual document
			int i = 1;
			while (iterator.hasNext()) {
				PDDocument pd = iterator.next();

				/******** Reader start **********/
				PDFTextStripper pdfStripper = new PDFTextStripper();
				String text = pdfStripper.getText(pd);
				FileWriter fw = new FileWriter("./src/main/resources/data/" + i + ".txt");
				fw.write(text);
				fw.close();
				/******** Reader end **********/

				pd.save("./src/main/resources/split/" + i++ + ".pdf");
			}
			/******** Splitter end **********/

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}
}
