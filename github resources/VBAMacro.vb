' ------------------------------------------------------------------------------------
' Authors- Sam Pick, Josh Lant 05-06/2014.

Private Function CreateDOM()
    Dim dom
    Set dom = New DOMDocument60
    dom.async = False
    dom.validateOnParse = False
    dom.resolveExternals = False
    dom.preserveWhiteSpace = True
    Set CreateDOM = dom
End Function

Sub XML()

' Instantiate an object at compile time.
Dim dom As New DOMDocument60
dom.async = False
dom.resolveExternals = False
dom.validateOnParse = False
dom.preserveWhiteSpace = True
   

' Declare variable for child node and attribute 
Dim node, attr

Dim colour, editedColour As String

On Error GoTo ErrorHandler

' Create a processing instruction targeted for xml.
Set node = dom.createProcessingInstruction("xml", "version='1.0'")
dom.appendChild node
Set node = Nothing

' Create a comment for the document.
Set node = dom.createComment("sample xml file created using XML DOM object.")
dom.appendChild node
Set node = Nothing

' Create the root element.
Dim root, information, collection

Set collection = dom.createElement("collection")

Set attr = dom.createAttribute("xmlns")
attr.Value = "http://www-users.york.ac.uk/~rjm529/schema.xsd"
collection.setAttributeNode attr
Set attr = Nothing

Set attr = dom.createAttribute("xmlns:xs")
attr.Value = "http://www.w3.org/2001/XMLSchema"
collection.setAttributeNode attr
Set attr = Nothing

dom.appendChild collection

collection.appendChild dom.createTextNode(vbNewLine + vbTab)

' ------------------------------------------------------------------------------------------------------
' Begin the parsing of every slide within the presentation.
   
Dim SlideToCheck As Slide
Dim ShapeIndex As Integer
Dim slideCounter As Integer
Dim slideNumberOffset As Integer
Dim chapterBranch As String

slideCounter = 0
slideNumberOffset = 1

' Visit each slide in the presentation
For Each SlideToCheck In ActivePresentation.Slides

	' ------------------------------------------------------------------------------------------------------
	' Create a new element slideshow element, this represents each chapter within the book.
	' At another slideshow element and then continue to add slides
	If SlideToCheck.NotesPage.Shapes.Placeholders(2).TextFrame.TextRange.Text = "NEW CHAPTER" Then
	
		slideNumberOffset = slideNumberOffset + slideCounter + 1
		slideCounter = 0
		
		Set root = Nothing
		Set node = Nothing
		
		' Create a new element slideshow, this represents each chapter within the book.
		Set root = dom.createElement("slideshow")

		collection.appendChild root
		root.appendChild dom.createTextNode(vbNewLine + vbTab)
		
		' Create and add more nodes to the slideshow element just created.
		
		Set node = dom.createElement("documentinfo")
		
		Set information = dom.createElement("author")
		information.Text = "D6 Digital"
		node.appendChild information
		Set information = Nothing
		
		Set information = dom.createElement("version")
		information.Text = "2.1"
		node.appendChild information
		Set information = Nothing
		
		Set information = dom.createElement("title")
		information.Text = SlideToCheck.Shapes(1).TextFrame.TextRange.Text
		node.appendChild information
		Set information = Nothing
		
		Set information = dom.createElement("comment")
		information.Text = SlideToCheck.Shapes(1).AlternativeText
		node.appendChild information
		Set information = Nothing
		
		' Set default width to 720 <width of slideshow in powerpoint>
		Set information = dom.createElement("width")
		information.Text = "720"
		node.appendChild information
		Set information = Nothing
		
		' Set default height to 540 <height of slideshow in powerpoint>
		Set information = dom.createElement("height")
		information.Text = "540"
		node.appendChild information
		Set information = Nothing
		
		' Add text node to the root element.
		root.appendChild node
		
		Set node = Nothing
		
		' Add a newline plus tab.
		root.appendChild dom.createTextNode(vbNewLine + vbTab)
		
		Set node = dom.createElement("defaults")
		
		 Set information = dom.createElement("backgroundcolor")
				' If the colour does not contain all R, G and B values, replace missing values with 0's.
				colour = Hex(SlideToCheck.Background.Fill.ForeColor.RGB)
				editedColour = colour
				If Len(colour) < 6 Then
					For Index = 1 To 6 - Len(colour) Step 1
					editedColour = "0" + editedColour  
					Next      
				End If
		
		information.Text = "#" + Mid(editedColour, 5, 2) + Mid(editedColour, 3, 2) + Mid(editedColour, 1, 2)
		node.appendChild information
		Set information = Nothing
		
		Set information = dom.createElement("font")
		information.Text = SlideToCheck.Shapes(1).TextFrame.TextRange.Font.Name
		node.appendChild information
		Set information = Nothing
		
		Set information = dom.createElement("fontsize")
		information.Text = SlideToCheck.Shapes(1).TextFrame.TextRange.Font.Size
		node.appendChild information
		Set information = Nothing
		
		Set information = dom.createElement("fontcolor")
			' If the colour does not contain all R, G and B values, replace missing values with 0's.
			colour = Hex(SlideToCheck.Shapes(1).TextFrame.TextRange.Font.Color.RGB)
			editedColour = colour
			If Len(colour) < 6 Then
				For Index = 1 To 6 - Len(colour) Step 1
					editedColour = "0" + editedColour                    
				Next
			End If
		
		information.Text = "#" + Mid(editedColour, 5, 2) + Mid(editedColour, 3, 2) + Mid(editedColour, 1, 2)
		node.appendChild information
		Set information = Nothing
		
		Set information = dom.createElement("linecolor")
			' If the colour does not contain all R, G and B values, replace missing values with 0's.
			colour = Hex(SlideToCheck.Shapes(1).Line.ForeColor)
			editedColour = colour
			If Len(colour) < 6 Then
				For Index = 1 To 6 - Len(colour) Step 1
					editedColour = "0" + editedColour
				Next
			End If
		
		information.Text = "#" + Mid(editedColour, 5, 2) + Mid(editedColour, 3, 2) + Mid(editedColour, 1, 2)
		node.appendChild information
		Set information = Nothing
		
		Set information = dom.createElement("fillcolor")
			' If the colour does not contain all R, G and B values, replace missing values with 0's.
			colour = Hex(SlideToCheck.Shapes(1).Fill.ForeColor)
			editedColour = colour
			If Len(colour) < 6 Then
				For Index = 1 To 6 - Len(colour) Step 1
					editedColour = "0" + editedColour
				Next
			End If
		
		information.Text = "#" + Mid(editedColour, 5, 2) + Mid(editedColour, 3, 2) + Mid(editedColour, 1, 2)
		node.appendChild information
		Set information = Nothing

		' Add text node to the root element.
		root.appendChild node
		Set node = Nothing
		  ' Add a newline plus tab.
		root.appendChild dom.createTextNode(vbNewLine + vbTab)
	
	' ------------------------------------------------------------------------------------------------------
	' If the new slide does not represent a new chapter, then add a new standard slide.
	Else
		
		Set Slide = dom.createElement("slide")
		root.appendChild dom.createTextNode(vbNewLine + vbTab)
		
		' add the attributes of the slide.
		Set attr = dom.createAttribute("id")
		attr.Value = SlideToCheck.SlideNumber - slideNumberOffset
		Slide.setAttributeNode attr
		Set attr = Nothing
		
		Set attr = dom.createAttribute("duration")
		attr.Value = 60
		Slide.setAttributeNode attr
		Set attr = Nothing
		
		Set attr = dom.createAttribute("descriptor")
		attr.Value = SlideToCheck.NotesPage.Shapes.Placeholders(2).TextFrame.TextRange.Text
		Slide.setAttributeNode attr
		Set attr = Nothing
		
		' If this is the last slide in the presentation or chapter, make the lastslide attribute true. Else set it false.
		If ActivePresentation.Slides.Count = SlideToCheck.SlideNumber Then
			Set attr = dom.createAttribute("lastSlide")
			attr.Value = "true"
			Slide.setAttributeNode attr
			Set attr = Nothing
		Else
			If ActivePresentation.Slides(SlideToCheck.SlideNumber + 1).NotesPage.Shapes.Placeholders(2).TextFrame.TextRange.Text = "NEW CHAPTER" Then
				Set attr = dom.createAttribute("lastSlide")
				attr.Value = "true"
				Slide.setAttributeNode attr
				Set attr = Nothing
			Else
				Set attr = dom.createAttribute("lastSlide")
				attr.Value = "false"
				Slide.setAttributeNode attr
				Set attr = Nothing  
			End If
		End If
		
		Set attr = dom.createAttribute("backgroundcolor")
			' If the colour does not contain all R, G and B values, replace missing values with 0's.
			colour = Hex(SlideToCheck.Background.Fill.ForeColor.RGB)
			editedColour = colour
			If Len(colour) < 6 Then
				For Index = 1 To 6 - Len(colour) Step 1
					editedColour = "0" + editedColour
				Next
			End If
		attr.Value = "#" + Mid(editedColour, 5, 2) + Mid(editedColour, 3, 2) + Mid(editedColour, 1, 2)
		Slide.setAttributeNode attr
		Set attr = Nothing
		
		
		
		
		' On each slide, count down through every element which is placed on the slide.
		For ShapeIndex = SlideToCheck.Shapes.Count To 1 Step -1

			' ------------------------------------------------------------------------------------------------------
			' Add a new video element for every chart found on the presentation.
			
			If SlideToCheck.Shapes(ShapeIndex).Type = msoChart Then

				Set node = dom.createElement("video")
				Set attr = dom.createAttribute("urlname")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).Chart.Title
				node.setAttributeNode attr
				
				Set attr = dom.createAttribute("xstart")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Left)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("ystart")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Top)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("width")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Width)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("height")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Height)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("layer")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).ZOrderPosition
				node.setAttributeNode attr
				Set attr = Nothing
				
				' If the start time is a too big a number, then it must be set to zero. Else set it
				' to the advance time given on action settings of the chart.
				If SlideToCheck.Shapes(ShapeIndex).AnimationSettings.AdvanceTime > 20000 Then
						Set attr = dom.createAttribute("starttime")
						attr.Value = "0"
						node.setAttributeNode attr
						Set attr = Nothing
				Else
						Set attr = dom.createAttribute("starttime")
						attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).AnimationSettings.AdvanceTime)
						node.setAttributeNode attr
						Set attr = Nothing
				End If
			   
				
				Set attr = dom.createAttribute("duration")
				attr.Value = 0
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("loop")
				attr.Value = "false"
				node.setAttributeNode attr
				Set attr = Nothing
				
				Slide.appendChild node
				
				Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
				Set node = Nothing
				
			End If
				
				
			' ------------------------------------------------------------------------------------------------------
			' Add a new image element for every autoshape found on the presentation.
			' Image URL found within text of the autoshape.
			
			If SlideToCheck.Shapes(ShapeIndex).Type = msoAutoShape Then
				Set node = dom.createElement("image")
				
				Set attr = dom.createAttribute("urlname")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).TextFrame.TextRange
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("xstart")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Left)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("ystart")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Top)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("width")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Width)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("height")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Height)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("layer")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).ZOrderPosition
				node.setAttributeNode attr
				Set attr = Nothing
				
				'If an animation setting is found for the shape then add the duration child element.
				If Not SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)) Is Nothing Then
					' If the duration is incredibly large then set it to -1 <value used for no duration in Java>.
					If CInt(SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)).Timing.Duration) > 20000 Then
						Set attr = dom.createAttribute("duration")
						attr.Value = "-1"
						node.setAttributeNode attr
						Set attr = Nothing
					Else
						Set attr = dom.createAttribute("duration")
						attr.Value = CInt(SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)).Timing.Duration)
						node.setAttributeNode attr
						Set attr = Nothing
					End If
				End If
					
				' If the start time is incredibly large then set it to 0.	
				If SlideToCheck.Shapes(ShapeIndex).AnimationSettings.AdvanceTime > 20000 Then
					Set attr = dom.createAttribute("starttime")
					attr.Value = "0"
					node.setAttributeNode attr
					Set attr = Nothing
				Else
					Set attr = dom.createAttribute("starttime")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).AnimationSettings.AdvanceTime)
					node.setAttributeNode attr
					Set attr = Nothing
				End If
				
				' If the chapter branching is found in alternative text then set it and the page branching.
				If Not SlideToCheck.Shapes(ShapeIndex).AlternativeText = "" Then
							
					Set attr = dom.createAttribute("chapterbranch")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).AlternativeText) - 1
					node.setAttributeNode attr
					Set attr = Nothing
					
					If Not SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address = "" Then
						Set attr = dom.createAttribute("branch")
						attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address) - 1
						node.setAttributeNode attr
						Set attr = Nothing
					Else
						Set attr = dom.createAttribute("branch")
						attr.Value = 0
						node.setAttributeNode attr
						Set attr = Nothing
					End If
				' If the page branching is found in hyperlink address then set it to the branch.	
				Else
					If Not SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address = "" Then
						Set attr = dom.createAttribute("branch")
						attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address) - slideNumberOffset
						node.setAttributeNode attr
						Set attr = Nothing
					End If    
				End If
				
				' Add the image child element to the slide element.
				Slide.appendChild node
				
				Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
				Set node = Nothing
			
			End If

			' ------------------------------------------------------------------------------------------------------
			' Add a new text element for every text box found on the presentation.
			If SlideToCheck.Shapes(ShapeIndex).Type = msoTextBox Then
				
				' If the text box has no text in, then it must be a containing text box, for others to be placed in.
				If SlideToCheck.Shapes(ShapeIndex).TextFrame.TextRange = "" Then
				
					' Create an element of text
					Set node = dom.createElement("text")
					
					Set attr = dom.createAttribute("xstart")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Left)
					node.setAttributeNode attr
					Set attr = Nothing
					
					Set attr = dom.createAttribute("ystart")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Top)
					node.setAttributeNode attr
					Set attr = Nothing
					
					Set attr = dom.createAttribute("xend")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Left) + CInt(SlideToCheck.Shapes(ShapeIndex).Width)
					node.setAttributeNode attr
					Set attr = Nothing
					
					Set attr = dom.createAttribute("yend")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Top) + CInt(SlideToCheck.Shapes(ShapeIndex).Height)
					node.setAttributeNode attr
					Set attr = Nothing
					
					Set attr = dom.createAttribute("layer")
					attr.Value = SlideToCheck.Shapes(ShapeIndex).ZOrderPosition
					node.setAttributeNode attr
					Set attr = Nothing
					
					' If the duration is incredibly large then set it to -1 <value used by Java for default duration>.	
					If Not SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)) Is Nothing Then
						If CInt(SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)).Timing.Duration) > 20000 Then
							Set attr = dom.createAttribute("duration")
							attr.Value = "-1"
							node.setAttributeNode attr
							Set attr = Nothing
						Else
							Set attr = dom.createAttribute("duration")
							attr.Value = CInt(SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)).Timing.Duration)
							node.setAttributeNode attr
							Set attr = Nothing
						End If
					End If
					
					' If the start time is incredibly large then set it to 0.	
					If SlideToCheck.Shapes(ShapeIndex).AnimationSettings.AdvanceTime > 20000 Then
						Set attr = dom.createAttribute("starttime")
						attr.Value = "0"
						node.setAttributeNode attr
						Set attr = Nothing
					Else
						Set attr = dom.createAttribute("starttime")
						attr.Value = SlideToCheck.Shapes(ShapeIndex).AnimationSettings.AdvanceTime
						node.setAttributeNode attr
						Set attr = Nothing
					End If

					Set attr = dom.createAttribute("font")
					attr.Value = SlideToCheck.Shapes(ShapeIndex).TextFrame.TextRange.Font.Name
					node.setAttributeNode attr
					Set attr = Nothing
					
					' If the colour does not contain all R, G and B values, replace missing values with 0's.
					Set attr = dom.createAttribute("fontcolor")
					attr.Value = "#" + Hex(SlideToCheck.Shapes(ShapeIndex).TextFrame.TextRange.Font.Color.RGB)
					colour = Hex(SlideToCheck.Shapes(ShapeIndex).TextFrame.TextRange.Font.Color.RGB)
					editedColour = colour
					
					If Len(colour) < 6 Then
						For Index = 1 To 6 - Len(colour) Step 1
							editedColour = "0" + editedColour
						Next   
					End If
					
					attr.Value = "#" + Mid(editedColour, 5, 2) + Mid(editedColour, 3, 2) + Mid(editedColour, 1, 2)
					node.setAttributeNode attr
					Set attr = Nothing
					
					
					Set attr = dom.createAttribute("fontsize")
					attr.Value = SlideToCheck.Shapes(ShapeIndex).TextFrame.TextRange.Font.Size
					node.setAttributeNode attr
					Set attr = Nothing
					
					'------------------------------------------------------------------------------------------------
					' This section of code adds a new text body child element to the text element. This is done
					' in such a way that the program checks each text box individually, and retrieves the x and 
					' y coordinates, width and height in order to make sure that text is added correctly to its
					' corresponding blank 'containing' text box. It then ensures that these are added in the correct
					' order to the XML to be certain that each body of text will be placed in the right position
					' on screen
					
					Dim TextIndex, TopTextIndex As Integer
					Dim LeftCoord As Integer
					Dim LowCoord As Integer
					Dim TextBody, TextString
					Dim TopCoords(500), LeftCoords(500) As Variant
					Dim TextIndexes(500) As Variant
					Dim SortedTextIndexes(500) As Variant
					Dim TextBodyCount As Integer
					
					TextBodyCount = 0
					
					LeftCoord = CInt(SlideToCheck.Shapes(ShapeIndex).Left)
					LowCoord = CInt(SlideToCheck.Shapes(ShapeIndex).Top)
					
					' First confirm that the text box found is actually within the containing text box.
					For TextIndex = SlideToCheck.Shapes.Count To 1 Step -1
						If SlideToCheck.Shapes(TextIndex).Type = msoTextBox Then
							If SlideToCheck.Shapes(TextIndex).TextFrame.TextRange <> "" Then
								If (CInt(SlideToCheck.Shapes(ShapeIndex).Left) <= CInt(SlideToCheck.Shapes(TextIndex).Left)) Then
									If (CInt(SlideToCheck.Shapes(ShapeIndex).Top) <= CInt(SlideToCheck.Shapes(TextIndex).Top)) Then
										If CInt(SlideToCheck.Shapes(ShapeIndex).Left) + CInt(SlideToCheck.Shapes(ShapeIndex).Width) >= CInt(SlideToCheck.Shapes(TextIndex).Left) + CInt(SlideToCheck.Shapes(TextIndex).Width) Then
											If CInt(SlideToCheck.Shapes(ShapeIndex).Top) + CInt(SlideToCheck.Shapes(ShapeIndex).Height) >= CInt(SlideToCheck.Shapes(TextIndex).Top) + CInt(SlideToCheck.Shapes(TextIndex).Height) Then
												TextBodyCount = TextBodyCount + 1
												TextIndexes(TextBodyCount) = TextIndex
												TopCoords(TextBodyCount) = CInt(SlideToCheck.Shapes(TextIndex).Top)
												LeftCoords(TextBodyCount) = CInt(SlideToCheck.Shapes(TextIndex).Left)
											End If
										End If
									End If
								End If
							End If
						End If
					Next
					
					' Loop through each of the text bodies if one or more is found.
					If TextBodyCount > 0 Then
						Dim Y, X As Integer
						Dim LowestIndex As Integer
						Dim SameLine, ReachedEnd As Boolean
						Y = 10000
						X = 10000
						ReachedEnd = False
						
						' For each containing text body, confirm that if it is the lowest X and lowest Y value, then 
						' it should be the next to be added to the xml. Each iteration of the loop should remove that
						' text box from the number that need to be added to the text element, so the next lowest X-Y can
						' be found.
						For I = 1 To TextBodyCount Step 1
							For E = 1 To TextBodyCount Step 1
								If (TopCoords(E) < Y And TopCoords(E) >= LowCoord) Then
									Y = TopCoords(E)
									LowestIndex = E
								End If
							Next
								
							SameLine = False
							
							For G = 1 To TextBodyCount Step 1
								If (TopCoords(G) = Y And LeftCoords(G) >= LeftCoord And LeftCoords(G) < X) Then
									SameLine = True
									X = LeftCoords(G)
									LowestIndex = G
								End If
							Next
								
							If Not I = 1 Then
								If Not TextIndexes(LowestIndex) = SortedTextIndexes(I - 1) Then
									SortedTextIndexes(I) = TextIndexes(LowestIndex)
								Else
									If Not I = TextBodyCount Then
										I = I - 1
									Else
										If ReachedEnd = True Then
										SortedTextIndexes(I) = TextIndexes(LowestIndex)
										Else
										I = I - 1
										ReachedEnd = True
										End If
									End If
								End If
							Else
								SortedTextIndexes(I) = TextIndexes(LowestIndex)
							End If
							
							If Not SameLine Then
								LeftCoord = CInt(SlideToCheck.Shapes(ShapeIndex).Left)
								LowCoord = Y + 1
							Else
								LeftCoord = X + 1
							End If
							Y = 10000
							X = 10000
								  
						Next

						' Create each of the text body elements and text string elements that are required.
						For K = 1 To TextBodyCount Step 1                 
						   ' Create an element of textbody
						   Set TextBody = dom.createElement("textbody")
						   
						   Set TextString = dom.createElement("textstring")
						   TextString.Text = SlideToCheck.Shapes(SortedTextIndexes(K)).TextFrame.TextRange
						   TextBody.appendChild TextString
						   TextBody.appendChild dom.createTextNode(vbNewLine + vbTab)
						   Set TextString = Nothing
				   
						   Dim charRange As TextRange
						   Dim branch As String
						   
						   With SlideToCheck.Shapes(SortedTextIndexes(K))
								Set charRange = .TextFrame.TextRange.Characters(1)
								branch = charRange.ActionSettings(ppMouseClick).Hyperlink.Address
								chapterBranch = .AlternativeText
						   End With

							' If there is no chapter branch, set to -1, same with branch.
							If Not chapterBranch = "" Then
								Set attr = dom.createAttribute("chapterbranch")
								attr.Value = CInt(chapterBranch) - 1
								TextBody.setAttributeNode attr
								Set attr = Nothing
								
								If Not branch = "" Then
									Set attr = dom.createAttribute("branch")
									attr.Value = CInt(branch) - 1
									TextBody.setAttributeNode attr
									Set attr = Nothing
								Else
									Set attr = dom.createAttribute("branch")
									attr.Value = 0
									TextBody.setAttributeNode attr
									Set attr = Nothing   
								End If
							Else
								If Not branch = "" Then
									Set attr = dom.createAttribute("branch")
									attr.Value = CInt(branch) - slideNumberOffset
									TextBody.setAttributeNode attr
									Set attr = Nothing
								End If 
							End If
							
							' Set font parameters.
							If charRange.Font.Italic = True Then
								Set attr = dom.createAttribute("italic")
								attr.Value = "true"
								TextBody.setAttributeNode attr
								Set attr = Nothing
							Else
								Set attr = dom.createAttribute("italic")
								attr.Value = "false"
								TextBody.setAttributeNode attr
								Set attr = Nothing
							End If
							
							If charRange.Font.Bold = True Then
								Set attr = dom.createAttribute("bold")
								attr.Value = "true"
								TextBody.setAttributeNode attr
								Set attr = Nothing
							Else
								Set attr = dom.createAttribute("bold")
								attr.Value = "false"
								TextBody.setAttributeNode attr
								Set attr = Nothing
							End If
						   
							If charRange.Font.Underline = True Then
								Set attr = dom.createAttribute("underlined")
								attr.Value = "true"
								TextBody.setAttributeNode attr
								Set attr = Nothing 
							Else
								Set attr = dom.createAttribute("underlined")
								attr.Value = "false"
								TextBody.setAttributeNode attr
								Set attr = Nothing   
							End If

						   node.appendChild TextBody
						   node.appendChild dom.createTextNode(vbNewLine + vbTab)
						   Set TextBody = Nothing
						   
						   Next
						   ' End of section for adding text body child element to the text element. Move to next text box
						   '------------------------------------------------------------------------------------------------
					End If
					
					Slide.appendChild node
					Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
					Set node = Nothing   
				End If
			End If
			
			'------------------------------------------------------------------------------------------------
			' If we have a freeform shape on the slide, then add a new shape element to the XML. 
			If SlideToCheck.Shapes(ShapeIndex).Type = msoFreeform Then
				Set node = dom.createElement("shape")
				
				' If the fill colour does not contain all R, G and B values, replace missing values with 0's.
				Set attr = dom.createAttribute("fillcolor")
				colour = Hex(SlideToCheck.Shapes(ShapeIndex).Fill.ForeColor.RGB)
				editedColour = colour
				
				If Len(colour) < 6 Then
					For Index = 1 To 6 - Len(colour) Step 1
						editedColour = "0" + editedColour
					Next
				End If
				
				attr.Value = "#" + Mid(editedColour, 5, 2) + Mid(editedColour, 3, 2) + Mid(editedColour, 1, 2)
				node.setAttributeNode attr
				Set attr = Nothing
				
				' If the line colour does not contain all R, G and B values, replace missing values with 0's.
				Set attr = dom.createAttribute("linecolor")
				colour = Hex(SlideToCheck.Shapes(ShapeIndex).Line.ForeColor.RGB)
				editedColour = colour
				
				If Len(colour) < 6 Then
					For Index = 1 To 6 - Len(colour) Step 1
						editedColour = "0" + editedColour
					Next
				End If
				
				attr.Value = "#" + Mid(editedColour, 5, 2) + Mid(editedColour, 3, 2) + Mid(editedColour, 1, 2)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("layer")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).ZOrderPosition
				node.setAttributeNode attr
				Set attr = Nothing
				
				' If the duration time is incredibly large then set it to -1 <value used by Java for default duration>	
				If Not SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)) Is Nothing Then
					If CInt(SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)).Timing.Duration) > 20000 Then
						Set attr = dom.createAttribute("duration")
						attr.Value = "-1"
						node.setAttributeNode attr
						Set attr = Nothing
					Else
						Set attr = dom.createAttribute("duration")
						attr.Value = CInt(SlideToCheck.TimeLine.MainSequence.FindFirstAnimationFor(SlideToCheck.Shapes(ShapeIndex)).Timing.Duration)
						node.setAttributeNode attr
						Set attr = Nothing
					End If
				End If
				
				' If the start time is incredibly large then set it to 0.				
				If SlideToCheck.Shapes(ShapeIndex).AnimationSettings.AdvanceTime > 20000 Then
					Set attr = dom.createAttribute("starttime")
					attr.Value = "0"
					node.setAttributeNode attr
					Set attr = Nothing
				Else
					Set attr = dom.createAttribute("starttime")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).AnimationSettings.AdvanceTime)
					node.setAttributeNode attr
					Set attr = Nothing
				End If
				
				' If there is no chapter branch, set to -1, same with branch.
				If Not SlideToCheck.Shapes(ShapeIndex).AlternativeText = "" Then
					Set attr = dom.createAttribute("chapterbranch")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).AlternativeText) - 1
					node.setAttributeNode attr
					Set attr = Nothing
					
					If Not SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address = "" Then
						Set attr = dom.createAttribute("branch")
						attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address) - 1
						node.setAttributeNode attr
						Set attr = Nothing
					Else
						Set attr = dom.createAttribute("branch")
						attr.Value = 0
						node.setAttributeNode attr
						Set attr = Nothing
					End If
				Else
					If Not SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address = "" Then
						Set attr = dom.createAttribute("branch")
						attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address) - slideNumberOffset
						node.setAttributeNode attr
						Set attr = Nothing
					End If
				End If

				Set attr = dom.createAttribute("totalpoints")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).Nodes.Count
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("totalpoints")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).Nodes.Count
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("width")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Width)
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("height")
				attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Height)
				node.setAttributeNode attr
				Set attr = Nothing
				
				' Add each individual point child element to the shape.
				Dim PointIndex As Integer
				Dim Point
					
				For PointIndex = 1 To SlideToCheck.Shapes(ShapeIndex).Nodes.Count Step 1
					Set Point = dom.createElement("point")
					
					Set attr = dom.createAttribute("num")
					attr.Value = PointIndex - 1
					Point.setAttributeNode attr
					Set attr = Nothing
					
					Set attr = dom.createAttribute("x")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Nodes.Item(PointIndex).Points(1, 1))
					Point.setAttributeNode attr
					Set attr = Nothing
					
					Set attr = dom.createAttribute("y")
					attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).Nodes.Item(PointIndex).Points(1, 2))
					Point.setAttributeNode attr
					Set attr = Nothing
				
					node.appendChild Point
					node.appendChild dom.createTextNode(vbNewLine + vbTab)
					Set Point = Nothing
				Next
				
				Slide.appendChild node
				Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
				Set node = Nothing
			End If

			'------------------------------------------------------------------------------------------------
			' If we have a table on the slide, then add a new sound element to the XML. 
			If SlideToCheck.Shapes(ShapeIndex).Type = msoTable Then
				Set node = dom.createElement("audio")
				Set attr = dom.createAttribute("urlname")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).Table.Cell(1, 1).Shape.TextFrame.TextRange.Text
				node.setAttributeNode attr
				
				Set attr = dom.createAttribute("playtime")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).Table.Cell(1, 2).Shape.TextFrame.TextRange.Text
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("starttime")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).Table.Cell(1, 3).Shape.TextFrame.TextRange.Text
				node.setAttributeNode attr
				Set attr = Nothing
				
				Set attr = dom.createAttribute("duration")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).Table.Cell(1, 4).Shape.TextFrame.TextRange.Text
				node.setAttributeNode attr
				Set attr = Nothing

				Set attr = dom.createAttribute("loop")
				attr.Value = SlideToCheck.Shapes(ShapeIndex).Table.Cell(1, 5).Shape.TextFrame.TextRange.Text
				node.setAttributeNode attr
				Set attr = Nothing
				
				Slide.appendChild node
				Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
				Set node = Nothing 
			End If
		' Completed one element. Move on to the next.
		Next
		
		root.appendChild Slide
		slideCounter = slideCounter + 1
		root.appendChild dom.createTextNode(vbNewLine + vbTab)
		Set Slide = Nothing  
	End If
Next
' Completed one slide. Move on to the next.
'------------------------------------------------------------------------------------------------
root.appendChild dom.createTextNode(vbNewLine)
Set node = Nothing

MsgBox "dom: " + dom.XML
' Save the XML document to a file.
dom.Save "EclipsePhase.xml"
Set root = Nothing
Set dom = Nothing

Exit Sub

ErrorHandler:
MsgBox Err.Description

End Sub





