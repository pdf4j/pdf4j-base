/**
 * $Id: RtfFontTableParser.java,v 1.5 2006/09/15 23:37:37 xlv Exp $
 * $Name:  $
 *
 * Copyright 2006 by Mark Hall
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the License.
 *
 * The Original Code is 'iText, a free JAVA-PDF library'.
 *
 * The Initial Developer of the Original Code is Bruno Lowagie. Portions created by
 * the Initial Developer are Copyright (C) 1999-2006 by Bruno Lowagie.
 * All Rights Reserved.
 * Co-Developer of the code is Paulo Soares. Portions created by the Co-Developer
 * are Copyright (C) 2000-2006 by Paulo Soares. All Rights Reserved.
 *
 * Contributor(s): all the names of the contributors are added in the source code
 * where applicable.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * LGPL license (the ?GNU LIBRARY GENERAL PUBLIC LICENSE?), in which case the
 * provisions of LGPL are applicable instead of those above.  If you wish to
 * allow use of your version of this file only under the terms of the LGPL
 * License and not to allow others to use your version of this file under
 * the MPL, indicate your decision by deleting the provisions above and
 * replace them with the notice and other provisions required by the LGPL.
 * If you do not delete the provisions above, a recipient may use your version
 * of this file under either the MPL or the GNU LIBRARY GENERAL PUBLIC LICENSE.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the MPL as stated above or under the terms of the GNU
 * Library General Public License as published by the Free Software Foundation;
 * either version 2 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Library general Public License for more
 * details.
 *
 * If you didn't download this code from the following link, you should check if
 * you aren't using an obsolete version:
 * http://www.lowagie.com/iText/
 */
package com.lowagie.text.rtf.direct;

/**
 * The RtfFontTableParser handles the events generated by the
 * RtfTokeniser while the RTF font table is parsed.
 * 
 * @version $Revision: 1.5 $
 * @author Mark Hall (mhall@edu.uni-klu.ac.at)
 */
public class RtfFontTableParser {
	/**
	 * The RtfImportHeader to add font mappings to.
	 */
	private RtfImportHeader importHeader = null;
	/**
	 * The number of the font being parsed.
	 */
	private String fontNr = "";
	/**
	 * The name of the font being parsed.
	 */
	private String fontName = "";
	
	/**
	 * Constructs a new RtfFontTableParser.
	 * 
	 * @param importHeader The RtfImportHeader to add font mappings to.
	 */
	public RtfFontTableParser(RtfImportHeader importHeader) {
		this.importHeader = importHeader;
		this.fontNr = "";
		this.fontName = "";
	}

	/**
	 * Handles closing of a group during parsing. If the group nesting level
	 * is 3 then an individual font definition was closed and thus if the
	 * font number and font name are set, they can be added to the mappings.
	 * 
	 * @param groupLevel The current group nesting level.
	 */
	public void handleCloseGroup(int groupLevel) {
		if(groupLevel == 3 && !this.fontNr.equals("") && !this.fontName.equals("")) {
			this.importHeader.importFont(this.fontNr, this.fontName);
			this.fontNr = "";
			this.fontName = "";
		}
	}

	/**
	 * Handles the font number control word. Only relevant if the
	 * group nesting level is 3.
	 * 
	 * @param ctrlWord The control word to handle.
	 * @param groupLevel The current group nesting level.
	 */
	public void handleCtrlWord(String ctrlWord, int groupLevel) {
		if(RtfColorTableParser.stringMatches(ctrlWord, "\\f") && groupLevel == 3) {
			this.fontNr = ctrlWord.substring(2);
		}
	}
	
	/**
	 * Handles the font name. The font name must be ended by a
	 * semicolon and must be at group nesting level 3.
	 * 
	 * @param text The font name to handle.
	 * @param groupLevel The current group nesting level.
	 */
	public void handleText(String text, int groupLevel) {
		if(text.indexOf(';') >= 0 && groupLevel == 3) {
			this.fontName = text.substring(0, text.indexOf(';'));
		}
	}
}
