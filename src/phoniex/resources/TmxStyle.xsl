<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : TmxStyle.xsl
    Created on : 07 July 2010, 09:48
    Author     : Admin
    Description:
        Convert Xliff to TMX
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="xliff">

  <tmx version="1.4">
<xsl:variable name="dataType" select="file/@datatype"/>
<xsl:variable name="sourceLanguage" select="file/@source-language"/>
	<header
        creationtool="Xliff Phoenix"
        creationtoolversion="1.0"
        datatype= "$dataType"
        segtype="sentence"
        adminlang="en-uk"
        srclang="$sourceLanguage"
        o-tmf="Xliff">
	</header>

	<body>
            <xsl:for-each select="//body//trans-unit//alt-trans">
                <xsl:variable name="targetLanguage" select="target/@xml:lang"/>
                <tu>
                    <tuv xml:lang="$sourceLanguage"> +"'"><xsl:value-of select="source"/></tuv>
                    <tuv xml:lang="$targetLanguage"><xsl:value-of select="target"/></tuv>
                    <prop type="match-quality"> <xsl:value-of select="@match-quality"/></prop>
                    <prop type="metadata"> <xsl:value-of select="note"/></prop>

                </tu>
            </xsl:for-each>

	</body>

  </tmx>
  <!--© 2010 UNIVERSITY OF LIMERICK.
  All rights reserved.
  This material may not be reproduced, displayed, modified or distributed without
  the express prior written permission of the copyright holder.
  This research is supported by the Science Foundation Ireland (Grant 07/CE/I1142) as part of the Centre for Next Generation Localisation (www.cngl.ie) at University of Limerick .-->
</xsl:template>
</xsl:stylesheet>
