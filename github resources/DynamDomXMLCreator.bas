Attribute VB_Name = "Module1"
Private Function CreateDOM()
    Dim dom
    Set dom = New DOMDocument60
    dom.async = False
    dom.validateOnParse = False
    dom.resolveExternals = False
    dom.preserveWhiteSpace = True
    Set CreateDOM = dom
End Function




' Private Sub XMLMaker()





' End Sub
Sub XML()


    ' Instantiate an object at compile time.
    Dim dom As New DOMDocument60
    dom.async = False
    dom.resolveExternals = False
    dom.validateOnParse = False
    dom.preserveWhiteSpace = True
    ' dom.LoadXML "<a>B</a>"

    ' Display the content of both objects.
    

   


Dim node, attr

Dim colour, editedColour As String

    On Error GoTo ErrorHandler

    ' Set dom = CreateDOM

    ' Create a processing instruction targeted for xml.
    Set node = dom.createProcessingInstruction("xml", "version='1.0'")
    dom.appendChild node
    Set node = Nothing

    ' Create a comment for the document.
    Set node = dom.createComment("sample xml file created using XML DOM object.")
    dom.appendChild node
    Set node = Nothing

    ' Create the root element.
    Dim root, collection, information
    
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
    
    Set root = dom.createElement("slideshow")

    collection.appendChild root

    
    ' Insert a newline + tab.
    root.appendChild dom.createTextNode(vbNewLine + vbTab)
    ' Create and add more nodes to the root element just created.
    ' Create a text element.
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
    information.Text = "DynamicDom"
    node.appendChild information
    Set information = Nothing
    
    Set information = dom.createElement("comment")
    information.Text = "A crazy superhero saves the day"
    node.appendChild information
    Set information = Nothing
    
    Set information = dom.createElement("width")
    information.Text = "720"
    node.appendChild information
    Set information = Nothing
    
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
    
     Set information = dom.createElement("backgroundcolour")
    
            colour = Hex(ActivePresentation.Slides(2).Background.Fill.ForeColor.RGB)
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
    information.Text = "Times New Roman"
    node.appendChild information
    Set information = Nothing
    
    Set information = dom.createElement("fontsize")
    information.Text = "24"
    node.appendChild information
    Set information = Nothing
    
    Set information = dom.createElement("fontcolour")
    information.Text = "#000000"
    node.appendChild information
    Set information = Nothing
    
    Set information = dom.createElement("linecolour")
    information.Text = "#000000"
    node.appendChild information
    Set information = Nothing
    
    Set information = dom.createElement("fillcolour")
    information.Text = "#000000"
    node.appendChild information
    Set information = Nothing
    
    
    ' Add text node to the root element.
    root.appendChild node
    Set node = Nothing
      ' Add a newline plus tab.
    root.appendChild dom.createTextNode(vbNewLine + vbTab)

    ' Create an element to hold a CDATA section.
    ' Set node = dom.createElement("node2")
    ' Set cd = dom.createCDATASection("<some mark-up text>")
    ' node.appendChild cd
    ' Set cd = Nothing
    ' dom.DocumentElement.appendChild node
    ' Set node = Nothing
      ' Add a newline plus tab.
    ' root.appendChild dom.createTextNode(vbNewLine + vbTab)
    
    Dim SlideToCheck As Slide
    Dim ShapeIndex As Integer
    
    
    ' Visit each slide
    For Each SlideToCheck In ActivePresentation.Slides
        
        Set Slide = dom.createElement("slide")
        root.appendChild dom.createTextNode(vbNewLine + vbTab)
        
        Set attr = dom.createAttribute("id")
        attr.Value = SlideToCheck.SlideNumber - 1
        Slide.setAttributeNode attr
        Set attr = Nothing
        
        Set attr = dom.createAttribute("duration")
        attr.Value = 60
        Slide.setAttributeNode attr
        Set attr = Nothing
        
        If ActivePresentation.Slides.Count = SlideToCheck.SlideNumber Then
        
        
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
        
        
        ' On each slide, count down through the shapes
        For ShapeIndex = SlideToCheck.Shapes.Count To 1 Step -1
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            If SlideToCheck.Shapes(ShapeIndex).Type = msoChart Then
                ' Create an element of image
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
                
                Set attr = dom.createAttribute("playtime")
                attr.Value = 1
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
                
                
                
             If SlideToCheck.Shapes(ShapeIndex).Type = msoAutoShape Then
                ' Create an element of image
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
                
                If Not SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address = "" Then
                
                Set attr = dom.createAttribute("branch")
                attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address) - 1
                node.setAttributeNode attr
                Set attr = Nothing
                
                End If
                
                Slide.appendChild node
                
                Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
                Set node = Nothing
            
            End If
                
                
                
                
                
                
                If SlideToCheck.Shapes(ShapeIndex).Type = msoTextBox Then
                
                
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
                    
                    
                    
                    
                    
                    Set attr = dom.createAttribute("fontcolour")
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
                    
                    If TextBodyCount > 0 Then
                        
                        Dim Y, X As Integer
                        Dim LowestIndex As Integer
                        Dim SameLine, ReachedEnd As Boolean
                        Y = 10000
                        X = 10000
                        ReachedEnd = False
                        
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
                        
                    
                        For K = 1 To TextBodyCount Step 1
                            
                            
                            
                                            
                           ' Create an element of textbody
                           Set TextBody = dom.createElement("textbody")
                           
                           Set TextString = dom.createElement("textstring")
                           TextString.Text = SlideToCheck.Shapes(SortedTextIndexes(K)).TextFrame.TextRange
                           ' + "\n"
                           TextBody.appendChild TextString
                           TextBody.appendChild dom.createTextNode(vbNewLine + vbTab)
                           Set TextString = Nothing
                          
                           
                           
                           
                           Dim charRange As TextRange
                           Dim Branch As String
                           
                           With SlideToCheck.Shapes(SortedTextIndexes(K))
                                Set charRange = .TextFrame.TextRange.Characters(1)
                                Branch = charRange.ActionSettings(ppMouseClick).Hyperlink.Address
                           End With

                           If Not Branch = "" Then
                                Set attr = dom.createAttribute("branch")
                                attr.Value = CInt(Branch) - 1
                                TextBody.setAttributeNode attr
                                Set attr = Nothing
                            End If
                            
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
                           
                    End If
                                        
                                        
                                       
                    
                    
                    Slide.appendChild node
                    Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
                    Set node = Nothing
                    
                End If
            
            End If
            
            
            
            
            
            
            
            
            If SlideToCheck.Shapes(ShapeIndex).Type = msoFreeform Then
                ' Create an element of image
                Set node = dom.createElement("shape")
                
                
                Set attr = dom.createAttribute("fillcolour")
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
                
                
                
                
                Set attr = dom.createAttribute("linecolour")
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
                
                If Not SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address = "" Then
                
                Set attr = dom.createAttribute("branch")
                attr.Value = CInt(SlideToCheck.Shapes(ShapeIndex).ActionSettings(ppMouseClick).Hyperlink.Address) - 1
                node.setAttributeNode attr
                Set attr = Nothing
                
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
                
                
                
                ' attr.Value = SlideToCheck.Shapes(ShapeIndex).Nodes.Item(1).Points(1, 1)
                
                Slide.appendChild node
                Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
                Set node = Nothing
            
            End If
            
            
            
             If SlideToCheck.Shapes(ShapeIndex).Type = msoTable Then
                ' Create an element of image
                Set node = dom.createElement("audio")
                Set attr = dom.createAttribute("urlname")
                attr.Value = SlideToCheck.Shapes(ShapeIndex).Table.Cell(1, 1).Shape.TextFrame.TextRange.Text
                node.setAttributeNode attr
                
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
                
                Set attr = dom.createAttribute("loop")
                attr.Value = "false"
                node.setAttributeNode attr
                Set attr = Nothing
                
                Slide.appendChild node
                Slide.appendChild dom.createTextNode(vbNewLine + vbTab)
                Set node = Nothing
                
            End If
            
            Next
            
            ' Add text node to the root element.
            root.appendChild Slide
    
            root.appendChild dom.createTextNode(vbNewLine + vbTab)
            Set Slide = Nothing
            
        
        Next
        

    ' Create a document fragment to be added to image.
    ' Set frag = dom.createDocumentFragment
    
        ' Add a newline + tab + tab.
    ' frag.appendChild dom.createTextNode(vbNewLine + vbTab + vbTab)
    ' frag.appendChild dom.createElement("subNode1")
    
       ' Add a newline + tab + tab.
    ' frag.appendChild dom.createTextNode(vbNewLine + vbTab + vbTab)
    ' frag.appendChild dom.createElement("subNode2")
       ' Add a newline + tab + tab.
    ' frag.appendChild dom.createTextNode(vbNewLine + vbTab + vbTab)
    ' frag.appendChild dom.createElement("subNode3")
       ' Add a newline + tab.
    ' frag.appendChild dom.createTextNode(vbNewLine + vbTab)
    ' node.appendChild frag
    ' Set frag = Nothing

    
       ' Add a newline.
    root.appendChild dom.createTextNode(vbNewLine)
    Set node = Nothing

    MsgBox "dom: " + dom.XML
    

    ' Save the XML document to a file.
    dom.Save "dynamDom.xml"
    Set root = Nothing
    Set dom = Nothing
    Exit Sub

ErrorHandler:
    MsgBox Err.Description


End Sub

